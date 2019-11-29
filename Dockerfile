FROM openjdk:11
COPY build/libs/studyit-* studyit.jar
ENTRYPOINT ["java", "-jar", "/studyit.jar"]
EXPOSE 8080
