package com.example.demo.repository;

import com.example.demo.entities.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserTokenRepository extends JpaRepository<UserToken,String> {
}
