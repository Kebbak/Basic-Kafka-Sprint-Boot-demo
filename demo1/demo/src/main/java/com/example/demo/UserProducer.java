package com.example.demo;

import com.example.demo.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Service
public class UserProducer {
    private static final Logger logger = LoggerFactory.getLogger(UserProducer.class);
    private final KafkaTemplate<String, UserDTO> kafkaTemplate;

    @Value("${app.kafka.user-topic}")
    private String topic;

    public UserProducer(KafkaTemplate<String, UserDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public CompletableFuture<String> sendUser(UserDTO user) {
        logger.info("Sending user to topic {}: {}", topic, user);
        return kafkaTemplate.send(topic, user.getId(), user)
            .thenApply(result -> {
                logger.info("User sent successfully: {}", user.getId());
                return "User sent: " + user.getId();
            })
            .exceptionally(ex -> {
                logger.error("Failed to send user: {}", user.getId(), ex);
                return "Failed to send user: " + user.getId();
            });
    }
}
