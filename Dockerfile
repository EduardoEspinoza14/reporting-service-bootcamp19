FROM openjdk:11
#VOLUME /tmp
EXPOSE 8083
EXPOSE 9001
ADD target/reporting-service.jar reporting-service.jar
ENTRYPOINT ["java", "-jar", "reporting-service.jar"]
