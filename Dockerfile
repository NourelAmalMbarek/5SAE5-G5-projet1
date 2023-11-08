FROM openjdk:8
EXPOSE 8081
ADD target/gestion-station-ski-1.0.jar gestion-station-ski-1.0.jar
ENTRYPOINT ["java","-jar","gestion-station-ski-1.0.jar"]
