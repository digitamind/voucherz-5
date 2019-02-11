FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 9092
ADD target/gift-voucherz-0.0.1-SNAPSHOT.jar gift-voucherz.jar
ENTRYPOINT ["java","-jar","/gift-voucherz.jar"]