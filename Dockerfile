# Stage 1: Build with Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

# Stage 2: Run with JDK only
FROM openjdk:17-slim
WORKDIR /app
COPY --from=build /app/target/knight_board-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar
CMD ["java", "-jar", "app.jar"]
