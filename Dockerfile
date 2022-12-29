FROM openjdk:17

ARG JAR_FILE=target/*.jar

ARG FILE_NAME=cloud-config-server

WORKDIR /opt/app

COPY ${JAR_FILE} ${FILE_NAME}.jar

ENTRYPOINT ["java","-jar","/${FILE_NAME}.jar"]

EXPOSE 9001