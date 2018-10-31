FROM openjdk:8-jre
ADD build/libs/resourcetechnologymapping-1.0.5-SNAPSHOT.war /resourcetechnologymapping-1.0.5-SNAPSHOT.war
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -war /resourcetechnologymapping-1.0.5-SNAPSHOT.war"]
