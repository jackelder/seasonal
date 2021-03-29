FROM openjdk:15.0.2-oraclelinux7

# for better security, don't run as root user
RUN adduser spring

USER spring

VOLUME /tmp

COPY target/*.jar app.jar

ENTRYPOINT [ "java", "-jar", "/app.jar" ]