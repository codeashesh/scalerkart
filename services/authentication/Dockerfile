# Use Maven to build the application
FROM maven:latest AS build
WORKDIR /home/app

# Copy parent POM (ensures dependencies are resolved correctly)
COPY pom.xml pom.xml

# Copy the entire cart service directory
COPY authentication authentication

# Build the service
WORKDIR /home/app/authentication
RUN mvn clean package -DskipTests

# Use OpenJDK runtime for running the built JAR
FROM openjdk:17-jdk-slim
WORKDIR /home/app
ENV SPRING_PROFILES_ACTIVE gke

# Copy built JAR
COPY --from=build /home/app/authentication/target/authentication-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "app.jar"]
