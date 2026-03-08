package com.example.demo;

import com.example.demo.dto.UserDTO;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserProducer userProducer;

    public UserController(UserProducer userProducer) {
        this.userProducer = userProducer;
    }

    @PostMapping
    public CompletableFuture<String> sendUser(@Valid @RequestBody UserDTO user) {
        return userProducer.sendUser(user);
    }

}