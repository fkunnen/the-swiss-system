FROM frolvlad/alpine-oraclejdk8:slim

MAINTAINER frank.kunnen@cegeka.com

EXPOSE 8080

COPY the-swiss-system-backend/build/libs/the-swiss-system.jar app.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
