package com.example.demo;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Service
public class UserProducer {
    private final KafkaTemplate<String, User> kafkaTemplate;
    private static final String TOPIC = "users";

    public UserProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CompletableFuture<String> sendUser(User user) {
        return kafkaTemplate.send(TOPIC, user.getId(), user)
            .thenApply(result -> "User sent: " + user.getId());
    }
}
