FROM openjdk:15.0.2-oraclelinux7

VOLUME /tmp

COPY target/*.jar app.jar

ENTRYPOINT [ "java", "-jar", "/app.jar" ]