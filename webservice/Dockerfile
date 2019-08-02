FROM openjdk:8-alpine
MAINTAINER Subhadip Ghosh (subhadip.sky@gmail.com)
ARG JAR_FILE
COPY ${JAR_FILE} /opt/app.jar
ENTRYPOINT ["/usr/bin/java", "-jar", "/opt/app.jar"]
EXPOSE 8080
