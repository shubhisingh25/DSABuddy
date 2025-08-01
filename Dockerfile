# -------- Build Stage --------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml and download dependencies first (faster rebuilds)
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the project
COPY . .

# Build the Spring Boot JAR (skip tests for faster CI/CD)
RUN mvn clean package -DskipTests

# -------- Runtime Stage --------
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy built JAR from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]
