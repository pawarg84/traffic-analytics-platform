# Use a minimal Java runtime
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy built JAR file
COPY target/traffic-backend-0.0.1-SNAPSHOT.jar app.jar

# Run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
