FROM openjdk:8-jre
ADD target/resourcetechnologymapping-0.0.1-SNAPSHOT.war app.war
ADD /var/lib/docker/tmp/docker-builder998978265/target/resourcetechnologymapping-0.0.1-SNAPSHOT.war
EXPOSE 8010
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-war","/app.war"]
