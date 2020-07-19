package com.ironhack.userclient.service;
import com.ironhack.userclient.model.User;


import com.ironhack.userclient.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public User findById(Integer id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<User> findAll(){
        return userRepo.findAll();
    }

    public User createUser(User user){
        boolean validUsername = findByUsername(user.getUsername()) == null;
        if(validUsername) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepo.save(user);
        }
        else return null;
    }
}