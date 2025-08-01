# Use an official lightweight JDK runtime as base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy your Spring Boot jar into the image
COPY target/*.jar app.jar

# Expose the port that your app runs on
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
