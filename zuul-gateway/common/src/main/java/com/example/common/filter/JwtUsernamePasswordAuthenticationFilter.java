package com.example.common.filter;

import com.example.common.config.JwtAuthenticationConfig;
import com.example.common.repository.sql.UserRepo;
import com.example.common.service.GatewayUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;

public class JwtUsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final JwtAuthenticationConfig config;
    private final ObjectMapper mapper;

    @Autowired
    UserRepo userRepo;

    @Autowired
    GatewayUserDetailsService gatewayUserDetailsService;

    public JwtUsernamePasswordAuthenticationFilter(JwtAuthenticationConfig config, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(config.getUrl(), "POST"));
        setAuthenticationManager(authManager);
        this.config = config;
        this.mapper = new ObjectMapper();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse rsp)
            throws AuthenticationException, IOException {
        User u = mapper.readValue(req.getInputStream(), User.class);
        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                u.getUsername(),u.getPassword(), Collections.emptyList()
        ));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse rsp, FilterChain chain,
                                            Authentication auth) throws IOException {
        Instant now = Instant.now();
        String token = Jwts.builder()
                .setSubject(auth.getName())
                .claim("authorities", auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(config.getExpiration())))
                .signWith(SignatureAlgorithm.HS256, config.getSecret().getBytes())
                .compact();

        rsp.addHeader(config.getHeader(), config.getPrefix() + " " + token);
        rsp.getWriter().write(mapper.writeValueAsString(new AuthenticationResponse(config.getPrefix() + " " + token, config.getExpiration())));
        rsp.addHeader("Content-Type", "application/json");
        rsp.getWriter().flush();
        rsp.getWriter().close();
    }

    @Data
    private static class AuthenticationResponse {
        private String access_token;
        private int expires_in;
        private String token_type = "bearer";

        public AuthenticationResponse(String token, int expires_in) {
            this.access_token = token;
            this.expires_in = expires_in;
        }
    }

    @Getter
    @Setter
    private static class User {
        private String username, password;
    }
}

