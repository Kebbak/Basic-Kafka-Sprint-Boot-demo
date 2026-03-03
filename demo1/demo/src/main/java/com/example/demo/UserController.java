package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserProducer userProducer;

    public UserController(UserProducer userProducer) {
        this.userProducer = userProducer;
    }

    @PostMapping
    public CompletableFuture<String> sendUser(@RequestBody User user) {
        return userProducer.sendUser(user);
    }

}