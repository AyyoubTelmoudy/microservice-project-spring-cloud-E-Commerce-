FROM openjdk:11
EXPOSE 8083
ADD target/billing-service.jar billing-service.jar
ENTRYPOINT ["java","-jar","/billing-service.jar"]