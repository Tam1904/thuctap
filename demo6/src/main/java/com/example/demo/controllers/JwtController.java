package com.example.demo.controllers;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.service.JwtUserDetailsService;
import com.example.demo.service.UserService;
import com.example.demo.service.UserTokenService;
import com.example.demo.utils.JwtRequestModel;
import com.example.demo.utils.JwtResponseModel;
import com.example.demo.utils.ResponseData;
import com.example.demo.utils.TokenManager;
import com.sun.net.httpserver.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/",produces = "application/json")
@CrossOrigin
public class JwtController {

    private final JwtUserDetailsService jwtUserDetailsService;

    private final AuthenticationManager authenticationManager;

    private final TokenManager tokenManager;

    private final UserService userService;

    private final UserTokenService userTokenService;

    @Autowired
    public JwtController(JwtUserDetailsService jwtUserDetailsService, AuthenticationManager authenticationManager, TokenManager tokenManager, UserService userService, UserTokenService userTokenService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.userService = userService;
        this.userTokenService = userTokenService;
    }

    @PostMapping(path= "/login",consumes = "application/json")
    public ResponseEntity<JwtResponseModel> createToken(@RequestBody JwtRequestModel request) throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
            );
        } catch (DisabledException e){
            throw new Exception("USER_DISABLED",e);
            // ngoại lệ nếu tài khoản bị vô hiệu hóa
        } catch (BadCredentialsException e){
            // bị ném nếu thông tin không chính xác
            throw new Exception("INVALID_CREDENTIALS",e);
        }
        final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        userTokenService.saveToken(request.getUsername(), jwtToken);
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return ResponseEntity.ok(new JwtResponseModel(jwtToken));
    }

    @GetMapping(path = "/admin/{id}")
    public UserDTO getUserById(@PathVariable("id")Integer id){
        User user = userService.getById(id);
        return UserDTO.covertUserDTO(user);
    }

    @PostMapping(path = "/admin/add",consumes = "application/json")
    public ResponseData addUser(@RequestBody UserDTO userDTO){
        User user = userService.getByUsername(userDTO.getUsername());
        if(user==null){
            user = UserDTO.convert(userDTO);
            userService.saveUser(user);
            user = userService.getByUsername(userDTO.getUsername());
            String []role = userDTO.getRole().split(",");
            List<Role> roleList = new ArrayList<>();
            for(String r : role){
                Role role1 = new Role();
                role1.setRole(r);
                role1.setUser(user);
                roleList.add(role1);
            }
            user.setRoles(roleList);
            userService.saveUser(user);
            return ResponseData.ok();
        }
        else {
            return ResponseData.badRequest();
        }
    }

    @DeleteMapping(path = "/admin/delete/{id}")
    public HttpStatus deleteUserById(@PathVariable("id")Integer id){
        userService.deleteById(id);
        return HttpStatus.OK;
    }

}
