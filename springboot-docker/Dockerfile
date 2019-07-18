FROM openjdk:latest
WORKDIR .
ADD target/docker-springboot.jar .
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "docker-springboot.jar"]