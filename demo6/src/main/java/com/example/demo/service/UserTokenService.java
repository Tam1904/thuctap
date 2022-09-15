package com.example.demo.service;

import com.example.demo.entities.UserToken;
import com.example.demo.repository.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTokenService {

    private final UserTokenRepository userTokenRepository;

    @Autowired
    public UserTokenService(UserTokenRepository userTokenRepository) {
        this.userTokenRepository = userTokenRepository;
    }

    public String getToken(String username){
        return userTokenRepository.findById(username).get().getToken();
    }

    public UserToken saveToken(String username, String token){
        return userTokenRepository.save(new UserToken(username,token));
    }
}
