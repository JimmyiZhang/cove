FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY jazzy-api/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]