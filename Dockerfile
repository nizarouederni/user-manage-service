FROM openjdk:17

ARG JAR_FILE=target/user-manage-service-*.jar

ARG FILE_NAME=user-manage-service.jar

WORKDIR /opt/app

COPY ${JAR_FILE} ${FILE_NAME}

ENTRYPOINT ["java","-jar","/${FILE_NAME}"]

EXPOSE 9001