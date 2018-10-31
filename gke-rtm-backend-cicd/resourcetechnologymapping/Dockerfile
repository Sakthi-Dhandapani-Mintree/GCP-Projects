FROM openjdk:8-jre
ADD target/resourcetechnologymapping-0.0.1-SNAPSHOT.war /workspace/gke-rtm-backend-cicd/resourcetechnologymapping/target/resourcetechnologymapping.war
COPY stuff /stuff
RUN cp -r /stuff/* / && rm -r /stuff
