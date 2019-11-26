FROM openjdk:alpine
ADD target/maven-code-coverage.jar maven-code-coverage.jar
ENTRYPOINT ["java", "-jar", "/maven-code-coverage.jar"]
EXPOSE 8080
