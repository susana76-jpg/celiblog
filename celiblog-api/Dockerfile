FROM openjdk:17-jdk-slim
WORKDIR /app
ARG JAR_FILE=target/celiblog-api-0.0.1.jar
COPY ${JAR_FILE} app_celiblog.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_celiblog.jar"]