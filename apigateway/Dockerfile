FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8080
ADD target/apigateway-0.0.1-SNAPSHOT.jar apigateway.jar
ENTRYPOINT ["java","-jar","/apigateway.jar"]