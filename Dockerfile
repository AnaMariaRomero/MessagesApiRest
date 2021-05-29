FROM openjdk:8
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} docker-spring-boot.jar
ENTRYPOINT ["java","-jar","/docker-spring-boot.jar"]