package com.example.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserConsumer {
    @KafkaListener(topics = "users", groupId = "user-group")
    public void listen(ConsumerRecord<String, User> record) {
        User user = record.value();
        System.out.println("Received User: ID=" + user.getId() + ", Name=" + user.getName() + ", Email=" + user.getEmail());
        // Simulate processing, e.g., saving to a database
        // userRepository.save(user);
        System.out.println("Processed user with ID: " + user.getId());
    }
}
