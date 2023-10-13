FROM openjdk:17

WORKDIR /app

COPY . /app

RUN ./mvnw clean package -DskipTests

