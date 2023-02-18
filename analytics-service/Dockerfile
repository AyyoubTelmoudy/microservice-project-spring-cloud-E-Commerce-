FROM openjdk:11
EXPOSE 8086
ADD target/analytics-service.jar analytics-service.jar
ENTRYPOINT ["java","-jar","/analytics-service.jar"]