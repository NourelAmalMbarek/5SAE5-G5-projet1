FROM openjdk:11
EXPOSE 8089
ADD target/nedra-benyoussef-spring.jar nedra-benyoussef-spring.jar
ENTRYPOINT ["java", "-jar", "nedra-benyoussef-spring.jar"]