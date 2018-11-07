FROM openjdk:8-jre
ADD /target/resourcetechnologymapping.war user/local/tomcat/webapps/
EXPOSE 8090
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-war","user/local/tomcat/webapps/"]
