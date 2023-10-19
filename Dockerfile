FROM openjdk:17-jdk-alpine
LABEL authors="Cartman"
ARG JAR_FILE=target/*.jar

COPY ./target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]