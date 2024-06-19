FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY /build/libs/messaging-0.0.1-SNAPSHOT.jar kafka-messaging.jar
EXPOSE 8081
CMD ["java", "-jar", "kafka-messaging.jar"]