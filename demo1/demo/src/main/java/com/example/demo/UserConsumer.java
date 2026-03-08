package com.example.demo;

import com.example.demo.dto.UserDTO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {
    private static final Logger logger = LoggerFactory.getLogger(UserConsumer.class);

    @Value("${app.kafka.user-topic}")
    private String topic;

    @KafkaListener(topics = "#{'${app.kafka.user-topic}'}", groupId = "user-group")
    public void listen(ConsumerRecord<String, UserDTO> record) {
        UserDTO user = record.value();
        try {
            logger.info("Received User: ID={}, Name={}, Email={}", user.getId(), user.getName(), user.getEmail());
            // Simulate processing, e.g., saving to a database
            // userRepository.save(user);
            logger.info("Processed user with ID: {}", user.getId());
        } catch (Exception ex) {
            logger.error("Error processing user: {}", user.getId(), ex);
        }
    }
}
