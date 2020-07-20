package com.ironhack.edgeservice.security;

import com.ironhack.edgeservice.model.User;
import com.ironhack.edgeservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userService.findUserByUsername(username);
        if (user != null) return UserPrincipal.create(user);
        else throw new RuntimeException("Username doesn't exist");
    }

    @Transactional
    public UserDetails loadUserById(Integer id) {
        User user = userService.findUserById(id);

        return UserPrincipal.create(user);
    }
}