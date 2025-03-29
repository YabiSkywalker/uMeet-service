FROM openjdk:17-jdk-slim

LABEL authors="YabiSkywalker"

WORKDIR /app

USER root

COPY pom.xml /app

RUN mvn dependency:go-offline

COPY src /app/src

#Yes clean once again ***
RUN mvn clean package -DskipTests

WORKDIR /app

COPY --from=build /app/target/uMeet-service.jar /app/uMeet-service.jar

EXPOSE 8080

CMD ["java", "-jar", "/uMeet-service.jar"]