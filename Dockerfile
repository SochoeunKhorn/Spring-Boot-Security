FROM openjdk:17-jdk-slim

WORKDIR /app

EXPOSE 8080

COPY target/SecurityAPI.war /app/SecurityAPI.war

ENTRYPOINT ["java", "-jar","/app/SecurityAPI.war"]