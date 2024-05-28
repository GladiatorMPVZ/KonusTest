FROM openjdk:17.0.2-jdk-slim-buster
WORKDIR /opt/app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src
RUN sed -i 's/\r$//' mvnw
RUN ./mvnw clean install -DskipTests
RUN cp target/KonusTest-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]