FROM eclipse-temurin:21-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} transfer-service.jar
ENTRYPOINT ["java","-jar","/transfer-service.jar"]