FROM openjdk:8-jre
ADD target/resourcetechnologymapping-0.0.1-SNAPSHOT.war app.war
COPY stuff /stuff
RUN cp -r /stuff/* / && rm -r /stuff
