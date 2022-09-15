package com.example.demo.service;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getById(Integer id){
        return userRepository.findById(id).get();
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public User getByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void deleteById(Integer id){
        userRepository.deleteById(id);
    }
}
