package com.example.demo;

import com.example.demo.dto.UserDTO;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.concurrent.CompletableFuture;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserProducer userProducer;
    private final UserRepository userRepository;

    public UserController(UserProducer userProducer, UserRepository userRepository) {
        this.userProducer = userProducer;
        this.userRepository = userRepository;
    }

    @PostMapping
    public CompletableFuture<String> sendUser(@Valid @RequestBody UserDTO user) {
        return userProducer.sendUser(user);
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}