FROM openjdk:25-slim

WORKDIR /app

USER root

COPY out/artifacts/uMeet_service_jar/uMeet-service.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]