FROM openjdk:8-jdk
RUN mkdir /spring-as-backend
COPY ./target/spring-as-backend.jar /spring-as-backend/spring-as-backend.jar
WORKDIR /spring-as-backend