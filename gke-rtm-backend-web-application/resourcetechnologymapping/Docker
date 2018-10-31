FROM openjdk:8-jre
ADD target/resourcetechnologymapping-0.0.1-SNAPSHOT.war app.war
EXPOSE 8010
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-war","/app.war"]
