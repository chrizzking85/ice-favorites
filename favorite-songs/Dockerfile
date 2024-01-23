#FROM openjdk:23-jdk-oracle
#COPY build/libs/demo-0.0.1-SNAPSHOT.jar demo-0.0.1.jar
#ENTRYPOINT ["java","-jar","/demo-0.0.1.jar"]

# Use the official Gradle image for building the application
FROM gradle:8.5.0-jdk17 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy only the Gradle files to cache dependencies
COPY build.gradle.kts settings.gradle.kts /app/

# Copy the entire source code
COPY src /app/src

# Build the application
RUN gradle build --no-daemon

# Create a new image with the JRE
FROM openjdk:23-jdk-oracle

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file built in the previous stage
COPY --from=builder /app/build/libs/demo-0.0.1-SNAPSHOT.jar /app/

# Expose the port that your Spring Boot application will run on
EXPOSE 8080

# Specify the command to run on container start
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]
