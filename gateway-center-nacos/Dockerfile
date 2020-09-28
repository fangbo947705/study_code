FROM openjdk:8-jdk-alpine
MAINTAINER andrew.fang
VOLUME /tmp
COPY service-2-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar /app.jar
