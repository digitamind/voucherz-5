FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8180
ADD target/auth-service-0.0.1-SNAPSHOT.jar auth-service.jar
ENTRYPOINT ["java","-jar","/auth-service.jar"]