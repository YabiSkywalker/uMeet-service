FROM openjdk:17-jdk-slim

LABEL authors="YabiSkywalker"

WORKDIR /app

USER root

COPY target/uMeet-service.jar /app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]