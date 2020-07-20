package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-client")
public interface UserClient {

    @GetMapping("/user/find_by_username/{username}")
    User findByUsername(@PathVariable String username);

    @GetMapping("/user/find_by_id/{id}")
    User findById(@PathVariable Integer id);

    @GetMapping("/users")
    List<User> findAllUsers();

    @PostMapping("/user")
    User createUser(@RequestBody User user);

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable Integer id);
}
