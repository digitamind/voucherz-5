FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 9091
ADD target/value-voucherz-0.0.1-SNAPSHOT.jar value-voucherz.jar
ENTRYPOINT ["java","-jar","/value-voucherz.jar"]