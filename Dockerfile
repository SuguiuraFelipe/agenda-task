FROM gradle:8.14.4-jdk21 AS build
WORKDIR /app
COPY . .
RUN gradle bootJar --no-daemon

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/agenda-task.jar

EXPOSE 8081

CMD ["java", "-jar", "/app/agenda-task.jar"]