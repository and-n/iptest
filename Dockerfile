#FROM openjdk:8-jdk-alpine
FROM hypriot/rpi-java
VOLUME /tmp
ARG JAR_FILE="iptest-0.0.1.jar"
ARG MONGO_IP="localhost"
ENV mongoip="$MONGO_IP"
EXPOSE 8080:8080
COPY target/${JAR_FILE} app.jar
ENTRYPOINT  java -Djava.security.egd=file:/dev/./urandom -Dspring.data.mongodb.host=$mongoip -jar ./app.jar
