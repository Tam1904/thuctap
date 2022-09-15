package com.example.demo.dto;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String role;


    public static User convert(UserDTO userDTO){
        User user = new User();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        List<Role> roleList = new ArrayList<>();
//        user.setRoles(roleList);
        return user;
    }

    public  static UserDTO covertUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        String role = "";
        for(Role r : user.getRoles()){
            role += r.getRole() + ",";
        }
        userDTO.setRole(role.substring(0,role.length()-1));
        return userDTO;
    }
}
