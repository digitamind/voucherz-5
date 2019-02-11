FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8991
ADD target/audit-trail-service-0.0.1-SNAPSHOT.jar audit-trail-service.jar
ENTRYPOINT ["java","-jar","/audit-trail-service.jar"]