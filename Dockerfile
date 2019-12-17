FROM maven:3.6.1-jdk-8-alpine AS maven_build
WORKDIR /app
COPY pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY src ./src

# TODO: jollof-* should be replaced with the proper prefix
RUN mvn package && cp target/Demo-*.jar app.jar

# Rely on Docker's multi-stage build to get a smaller image based on JRE
FROM openjdk:8-jdk-alpine
LABEL maintainer="xxxxx@xxx.com"
WORKDIR /app
COPY --from=maven_build /app/app.jar ./app.jar

# VOLUME /tmp  # optional
EXPOSE 8080    # also optional

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]




#VOLUME /tmp

#COPY pom.xml /tmp/

#COPY src /tmp/src/

#COPY /target/*.jar Demo-H2-0.0.1-SNAPSHOT.jar

#ENTRYPOINT ["java","-jar","/Demo-H2-0.0.1-SNAPSHOT.jar"]
#RUN echo pwd

#WORKDIR /tmp/

#RUN mvn package 

#RUN mvn clean install

#COPY target/*.jar Demo-H2-0.0.1-SNAPSHOT.jar



#pull base image

#FROM openjdk:8-jdk-alpine

#maintainer 

#EXPOSE 7000

#default command
3CMD java -jar /data/Demo-H2-0.0.1-SNAPSHOT.jar

#copy hello world to docker image from builder image

#COPY /data/target/*.jar /data/Demo-H2-0.0.1-SNAPSHOT.jar

#ENTRYPOINT ["java","-jar","data/Demo-H2-0.0.1-SNAPSHOT.jar"]

#COPY --from=maven_build /tmp/target/hello-world-0.1.0.jar /data/hello-world-0.1.0.jar
