FROM openjdk:8-jre
ADD /target/resourcetechnologymapping.war usr/local/tomcat/webapps/
EXPOSE 8090
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","usr/local/tomcat/webapps/"]
