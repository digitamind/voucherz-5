FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 9093
ADD target/discount-voucherz-0.0.1-SNAPSHOT.jar discount-voucherz.jar
ENTRYPOINT ["java","-jar","/discount-voucherz.jar"]