FROM openjdk:8
EXPOSE 9000
ADD /target/restapp-0.0.1-SNAPSHOT.jar restapp.jar
ENTRYPOINT [ "java", "-jar", "restapp.jar"]
