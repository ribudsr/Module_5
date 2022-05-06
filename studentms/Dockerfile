#base docker image
FROM openjdk:11
ADD target/studentms-0.0.1-SNAPSHOT.jar docker-student.jar
ENTRYPOINT ["java","-jar","docker-student.jar"]