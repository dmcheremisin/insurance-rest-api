FROM openjdk:8-jdk-alpine

VOLUME /tmp
ADD /insurance-controller-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app'
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]