FROM openjdk:17-jdk-alpine
LABEL authors="Cartman"
ARG JAR_FILE=target/*.jar

COPY ./target/vocabluary-with-security-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]