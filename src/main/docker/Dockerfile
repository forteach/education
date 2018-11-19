FROM openjdk:8-jdk-alpine
MAINTAINER forteach/education zhang10092009@hotmail.com
VOLUME /tmp
ADD education-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]