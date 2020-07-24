package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.UserClient;
import com.ironhack.edgeservice.enums.Role;
import com.ironhack.edgeservice.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @MockBean
    private UserClient userClient;
    @Autowired
    private UserService userService;
    private User user;

    @BeforeEach
    void setUp() {
        user= new User(1, "manuelPerez", "barco", Role.ROLE_PATIENT);
        when(userClient.findById(1)).thenReturn(user);
        when(userClient.findByUsername("manuelPerez")).thenReturn(user);
        when(userClient.findAllUsers()).thenReturn(Arrays.asList(user));
        doNothing().when(userClient).delete(1);
        when(userClient.createUser(Mockito.any(User.class))).thenReturn(user);
    }

    @Test
    void findUserById() {
        assertEquals("manuelPerez", userService.findUserById(1).getUsername());
    }

    @Test
    void findUserByIdFake() {
        assertThrows(NullPointerException.class, ()-> userService.findUserByIdFake(1));
    }

    @Test
    void create() {
        assertEquals("manuelPerez", userService.create(user).getUsername());
    }

    @Test
    void createFakeUser() {
        assertThrows(NullPointerException.class, ()-> userService.createFakeUser(user));
    }

    @Test
    void findUserByUsername() {
        assertEquals("manuelPerez", userService.findUserByUsername("manuelPerez").getUsername());
    }

    @Test
    void findUserByUsernameFake() {
        assertThrows(NullPointerException.class, ()-> userService.findUserByUsernameFake("pepito"));
    }

    @Test
    void findAllUsers() {
        assertEquals(1, userService.findAllUsers().size());
    }

    @Test
    void findAllUsersFake() {
        assertThrows(NullPointerException.class, ()-> userService.findAllUsersFake());
    }

    @Test
    void delete() {
        userService.delete(1);
    }
}