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
- `UserProducer`: Sends messages to Kafka
- `UserConsumer`: Receives messages from Kafka
- `User`: User data model

## License
MIT
