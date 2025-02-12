# Stage 1: Build the application using Gradle
FROM eclipse-temurin:21-alpine AS build

# Set working directory
WORKDIR /app

# Copy Gradle Wrapper and project files
COPY gradlew gradlew.bat gradle/ ./gradle/
COPY build.gradle settings.gradle ./
COPY src ./src

# Grant execute permission to the Gradle wrapper
RUN chmod +x gradlew

# Build the application (excluding tests for faster builds)
RUN ./gradlew clean build -x test

# Stage 2: Create a minimal runtime image
FROM eclipse-temurin:21-alpine

# Set working directory
WORKDIR /app

# Copy the built JAR from the previous stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
