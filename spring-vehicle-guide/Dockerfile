FROM openjdk:8-jdk 
LABEL author="Miguel Angel Luna" 
ADD /target/spring-vehicle-guide-1.0.0.jar app.jar 
EXPOSE 5000 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
