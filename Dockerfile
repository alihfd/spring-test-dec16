FROM maven:3.6.1-jdk-8-alpine AS maven_build

COPY pom.xml /tmp/

COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package

#pull base image

FROM openjdk:8-jdk-alpine

#expose port 7000
EXPOSE 7000

#default command
CMD java -jar /data/TravelAgency_SpringBoot-0.0.1-SNAPSHOT.jar

#copy hello world to docker image from builder image

COPY --from=maven_build /tmp/target/TravelAgency_SpringBoot-0.0.1-SNAPSHOT.jar /data/ta-image-0.1.0.jar
