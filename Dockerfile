FROM openjdk:8
#VOLUME /tmp
EXPOSE 8083
EXPOSE 9000
ADD target/gateway.jar gateway.jar
ENTRYPOINT ["java", "-jar", "gateway.jar"]
