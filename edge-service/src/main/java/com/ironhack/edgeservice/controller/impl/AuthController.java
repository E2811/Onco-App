package com.ironhack.edgeservice.controller.impl;


import com.ironhack.edgeservice.controller.dto.JwtAuthenticationResponse;
import com.ironhack.edgeservice.controller.dto.UserLoginRequest;
import com.ironhack.edgeservice.service.AuthService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @ApiOperation(value = "Login for admin and sales reporter. Method returns the auth token that you will use in the header of all secured routes.")
    @PostMapping("/sign-in")
    @ResponseStatus(HttpStatus.OK)
    public JwtAuthenticationResponse loginUser(@RequestBody @Valid UserLoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

}
