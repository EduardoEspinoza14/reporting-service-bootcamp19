FROM openjdk:8
#VOLUME /tmp
EXPOSE 8083
EXPOSE 9000
ADD target/reporting-service.jar reporting-service.jar
ENTRYPOINT ["java", "-jar", "reporting-service.jar"]
