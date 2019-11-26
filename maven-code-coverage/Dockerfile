FROM openjdk:8-jdk-alpine
VOLUME /tmp
WORKDIR /usr/src/
COPY target/*.jar /usr/src/
RUN sh -c 'touch /usr/src/maven-code-coverage.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=dev","-Xmx200m", "-jar", "/usr/src/maven-code-coverage.jar.jar"]
EXPOSE 8080
