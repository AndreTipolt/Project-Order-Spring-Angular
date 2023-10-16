FROM openjdk:17

WORKDIR /app

COPY . /app

# RUN ./mvnw clean package -DskipTests

COPY target/spring-0.0.1-SNAPSHOT.jar /app/spring-app.jar

ENTRYPOINT [ "java", "-jar", "/app/spring-app.jar" ]