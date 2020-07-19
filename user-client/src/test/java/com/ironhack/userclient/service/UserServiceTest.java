package com.ironhack.userclient.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void findByUsername() {
        assertEquals("elisaMontalvo", userService.findByUsername("elisaMontalvo").getUsername());
    }

    @Test
    void findById() {
        assertEquals("elisaMontalvo", userService.findById(3).getUsername());
    }

    @Test
    void findAll() {
        assertEquals(11, userService.findAll().size());
    }
}