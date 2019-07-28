FROM openjdk:11-jdk-slim AS builder
WORKDIR /app
COPY .mvn .mvn
COPY mvnw ./
COPY pom.xml ./
RUN chmod +x mvnw && ./mvnw verify --fail-never --batch-mode
COPY src src
RUN ./mvnw package -Dmaven.test.skip=true

FROM openjdk:11-jre-slim
RUN mkdir /spring-as-backend
COPY --from=builder /app/target/spring-as-backend.jar /spring-as-backend/spring-as-backend.jar
WORKDIR /spring-as-backend
