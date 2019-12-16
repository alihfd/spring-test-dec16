FROM maven:3.6.1-jdk-8-alpine AS maven_build

COPY pom.xml /tmp/

COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package

#pull base image

FROM openjdk:8-jdk-alpine

#expose port 7000
EXPOSE 7000

ENTRYPOINT ["java","-jar","/Demo-H2-0.0.1-SNAPSHOT.jar"]

#default command
#CMD java -jar /data/Demo-H2-0.0.1-SNAPSHOT.jar

#copy hello world to docker image from builder image

COPY --from=maven_build /tmp/target/Demo-H2-0.0.1-SNAPSHOT.jar /data/Demo-H2-0.0.1-SNAPSHOT.jar
