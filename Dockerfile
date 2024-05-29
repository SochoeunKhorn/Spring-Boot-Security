FROM openjdk:17-jdk-slim

WORKDIR /app

EXPOSE 8080

COPY target/Spring-Security-API.jar /app/Spring-Security-API.jar

ENTRYPOINT ["java", "-jar","/app/Spring-Security-API.jar"]