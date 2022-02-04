FROM openjdk:17-oracle
EXPOSE 8888

ARG JAR_FILE=target/spring-cloud-config-server.jar
ADD ${JAR_FILE} config-server.jar
ENTRYPOINT ["java","-jar","/config-server.jar"]