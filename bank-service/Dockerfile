FROM eclipse-temurin:21-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} bank-service.jar
ENTRYPOINT ["java","-jar","/bank-service.jar"]