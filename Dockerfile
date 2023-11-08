FROM openjdk:8
EXPOSE 8080
ADD target/nedra-benyoussef-spring.jar nedra-benyoussef-spring.jar
ENTRYPOINT ["java", "-jar", "nedra-benyoussef-spring.jar"]