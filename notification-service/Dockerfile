FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8990
ADD target/notification-service-0.0.1-SNAPSHOT.jar notification-service.jar
ENTRYPOINT ["java","-jar","/notification-service.jar"]