# Use Maven image to build the app
FROM maven:3.9.4-eclipse-temurin-17 AS build

# Set working directory in the container
WORKDIR /app

# Copy Maven project files
COPY pom.xml .
COPY src ./src

# Build the application (this creates a jar file in /app/target)
RUN mvn clean package -DskipTests

# Use a lightweight JDK runtime to run the app
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the jar from the builder stage
COPY --from=build /app/target/springboot-demo-0.0.1-SNAPSHOT.jar app.jar

# Expose port1
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
