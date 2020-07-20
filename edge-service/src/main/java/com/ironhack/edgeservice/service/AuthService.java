package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.controller.dto.JwtAuthenticationResponse;
import com.ironhack.edgeservice.controller.dto.UserLoginRequest;

import com.ironhack.edgeservice.security.JwtTokenProvider;
import com.ironhack.edgeservice.security.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    private static final Logger LOGGER = LogManager.getLogger(AuthService.class);

    public JwtAuthenticationResponse authenticateUser(UserLoginRequest userLoginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginRequest.getUsername(),
                        userLoginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        LOGGER.info("User with [username: {}] has logged in", userPrincipal.getUsername());

        JwtAuthenticationResponse response = new JwtAuthenticationResponse(jwt);
        response.setUserRole(userPrincipal.getRole());
        response.setUsername(userPrincipal.getUsername());

        return response;
    }
}
