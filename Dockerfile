FROM openjdk:17

WORKDIR /opt/app

COPY target/user-manage-service-*.jar /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

EXPOSE 9001