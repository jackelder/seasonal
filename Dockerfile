FROM openjdk:15.0.2-oraclelinux7

# ENV JAR_FILE=seasonal/target/*.jar 

ARG JAR_FILE=../

COPY $JAR_FILE app.jar

ENTRYPOINT [ "java", "-jar", "/app.jar" ]