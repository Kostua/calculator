FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar calculator-0.0.1.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/calculator-0.0.1.jar"]