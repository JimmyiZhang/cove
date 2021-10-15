FROM openjdk:17-jdk-alpine
MAINTAINER jimmy.zhang
COPY jazzy.jar app.jar
# EXPOSE 8080
ENTRYPOINT ["sh","-c","java ${JAVA_OPTS} -jar /app.jar"]