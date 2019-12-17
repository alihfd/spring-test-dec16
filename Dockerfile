FROM maven:3.6.1-jdk-8-alpine AS maven_build

VOLUME /tmp

COPY pom.xml /tmp/

COPY src /tmp/src/

#COPY /target/*.jar Demo-H2-0.0.1-SNAPSHOT.jar

#ENTRYPOINT ["java","-jar","/Demo-H2-0.0.1-SNAPSHOT.jar"]
#RUN echo pwd

WORKDIR /tmp/

RUN mvn package 

#RUN mvn clean install

#COPY target/*.jar Demo-H2-0.0.1-SNAPSHOT.jar



#pull base image

FROM openjdk:8-jdk-alpine

#maintainer 

EXPOSE 7000

#default command
CMD java -jar /data/Demo-H2-0.0.1-SNAPSHOT.jar

#copy hello world to docker image from builder image

COPY /tmp/target/*.jar /data/Demo-H2-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","data/Demo-H2-0.0.1-SNAPSHOT.jar"]

#COPY --from=maven_build /tmp/target/hello-world-0.1.0.jar /data/hello-world-0.1.0.jar
