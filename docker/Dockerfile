FROM amazoncorretto:17-al2023
WORKDIR /app
ARG SERVER_PORT=8080
ENV DB_USER="gustavo"
ENV DB_PASSWORD=4642941
COPY target/devops-example-0.0.1-SNAPSHOT.jar app.jar
EXPOSE $SERVER_PORT
ENTRYPOINT ["java", "-jar", "app.jar"]

#FROM ubuntu:23.10
#RUN apt-get update
#RUN apt-get install -y openjdk-17-jre
#COPY devops-example-0.0.1-SNAPSHOT.jar /teste/devops-example-0.0.1-SNAPSHOT.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "/teste/devops-example-0.0.1-SNAPSHOT.jar"]