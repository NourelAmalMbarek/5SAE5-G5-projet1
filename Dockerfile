# Use an official OpenJDK runtime as a base image
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file built by Maven into the container at the specified location
COPY target/gestion-station-ski-1.0.jar /app/app.jar

# Expose the port that the application will listen on (e.g., 8080)
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "app.jar"]
