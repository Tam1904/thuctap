package com.example.common.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@ToString
//@Component
public class JwtAuthenticationConfig {

    @Value("${jwt.url:/login}")
    private String url;

    @Value("${jwt.header:Authorization}")
    private String header;

    @Value("${jwt.prefix:Bearer}")
    private String prefix;

    @Value("${jwt.expiration:#{24*60*60}}")
    private int expiration; // default 24 hours

    @Value("${jwt.secret}")
    private String secret;
}