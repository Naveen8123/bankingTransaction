FROM openjdk:17-alpine
ADD target/banking-transaction.jar banking-transaction.jar
ENTRYPOINT ["java", "-jar", "banking-transaction.jar"]
