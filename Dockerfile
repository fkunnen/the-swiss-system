FROM adoptopenjdk/openjdk11:jdk-11.0.3_7-alpine-slim

MAINTAINER frank.kunnen@cegeka.com

EXPOSE 8080

COPY the-swiss-system-backend/build/libs/the-swiss-system.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]