FROM eclipse-temurin:21-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} risk-service.jar
ENTRYPOINT ["java","-jar","/risk-service.jar"]