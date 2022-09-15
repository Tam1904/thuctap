package com.example.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenManager implements Serializable {

    private  static  final long serialVersionUID = 7008375124389347049L;

    private  static  final long TOKEN_VALIDITY = 10*60*60*1000;

    @Value("${secret}")
    private String jwtSecret;

    public String generateJwtToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))// thời điểm token được phát hành
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY)) // thời gian tồn tại của token
                .signWith(SignatureAlgorithm.HS512,jwtSecret).compact();
    }

    /*
    * Claims - Payload bao gồm 1 số quyền Iss(nhà phát hành), exp (thời gian hết hạn), sub chủ đề
    * nội dung cần gửi và khóa dùng mã hóa
    * signWith tạo chữ ký với loại mã hóa và khóa
    * */
    public Boolean validateJwtToken(String token, UserDetails userDetails){
        String username = getUsernameFromToken(token);
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        Boolean isTokenExpired = claims.getExpiration().before(new Date());
        return (username.equals(userDetails.getUsername()) && !isTokenExpired);
    }

    // kiểm tra username từ token được giải mã và thời gian tồn tại của token

    public String getUsernameFromToken(String token){
        final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
    // setSigningKey : khóa cho quá trình giải mã parseClaimJws: giải mã chữ ký Sign getSubject() lấy nội dung là username
}
