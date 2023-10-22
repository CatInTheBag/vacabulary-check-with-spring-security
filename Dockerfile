FROM eclipse-temurin:17-alpine
LABEL authors="Cartman"
ARG JAR_FILE=target/*.jar

COPY ./target/vocabluary-with-security-0.0.1.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]