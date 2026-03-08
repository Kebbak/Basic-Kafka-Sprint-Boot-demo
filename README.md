# Basic Kafka Spring Boot Demo

This project demonstrates a simple integration between Spring Boot and Apache Kafka. It includes:

- A REST API to send user data to a Kafka topic
- A Kafka producer that publishes messages to the `users` topic
- A Kafka consumer that listens to the `users` topic and logs received messages

## Prerequisites
- Java 17+
- Maven
- Kafka broker running (Docker Compose or Confluent Platform)


## How to Run
1. **Start Docker Compose containers:**
   Navigate to the directory containing `docker-compose.yml` and run:
   ```
   docker compose up -d
   ```
   Make sure all containers (zookeeper, kafka, schema-registry, kafka-ui) are running:
   ```
   docker compose ps
   ```

2. **Build the project:**
   ```
   mvn clean install
   ```
3. **Start the application:**
   ```
   mvn spring-boot:run
   ```
   or
   ```
   java -jar target/demo-0.0.1-SNAPSHOT.jar
   ```
4. **Send a test user:**
   ```
   curl -X POST http://localhost:8082/api/user \
     -H "Content-Type: application/json" \
     -d '{"id":"1","name":"jason","email":"jason@example.com"}'
   ```

## Kafka Configuration
- Kafka bootstrap server: `localhost:9092` (see `application.properties`)
- REST API port: `8082` (see `application.properties`)

## Project Structure

- `UserController`: Handles HTTP requests
- `UserProducer`: Sends messages to Kafka (using UserDTO)
- `UserConsumer`: Receives messages from Kafka (using UserDTO)
- `UserDTO`: Data Transfer Object for user messages (used for API and Kafka)
- `User`: (Optional) Internal user entity/model
- `KafkaProducerConfig`: Configures KafkaTemplate for UserDTO

## Notes & Troubleshooting
- Make sure your Kafka broker is running and accessible at the address in `application.properties`.
- If you see validation errors, ensure you have the `jakarta.validation-api` dependency and use `jakarta.validation` imports.
- If you see errors about missing KafkaTemplate beans, ensure `KafkaProducerConfig` is present and correctly injects the bootstrap servers property.
- For serialization issues, ensure your DTOs are serializable and use the correct serializers in your config.