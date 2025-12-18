FROM eclipse-temurin:17-jdk
WORKDIR /app
ARG JAR_FILE=target/celiblog-api-0.0.1.jar
COPY ${JAR_FILE} app_celiblog.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app_celiblog.jar"]