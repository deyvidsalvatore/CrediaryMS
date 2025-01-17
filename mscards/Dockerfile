FROM maven:3.9.8-eclipse-temurin-21 AS build
COPY src /app/src
COPY pom.xml /app
WORKDIR /app
RUN mvn clean package -DskipTests

FROM openjdk:21
COPY --from=build /app/target/*.jar /app/app.jar
WORKDIR /app
CMD ["java", "-jar", "app.jar"]