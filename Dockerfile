# # Use a imagem oficial do Maven para construir o projeto
# FROM maven:3.8.4-openjdk-17 AS build
# WORKDIR /app
# COPY . .
# RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/simple-api-0.0.1-SNAPSHOT.jar simple-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/simple-api.jar"]

