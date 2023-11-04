# Builder Stage
FROM maven:3-amazoncorretto-17 as builder
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn package

FROM eclipse-temurin:17-alpine
LABEL authors="Cartman"
WORKDIR /app
COPY --from=builder /app/target/vocabluary-with-security-0.0.1.jar app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]