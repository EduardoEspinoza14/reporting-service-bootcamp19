FROM openjdk:11
#VOLUME /tmp
EXPOSE 8083
EXPOSE 8085
ADD target/reporting-service.jar reporting-service.jar
ENTRYPOINT ["java", "-jar", "reporting-service.jar", "-web -webAllowOthers -tcp -tcpAllowOthers -browser"]
