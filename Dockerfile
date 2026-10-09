FROM gradle:8.5-jdk21-alpine AS build
WORKDIR /app
COPY gradlew .
COPY gradle ./gradle
COPY settings.gradle.kts build.gradle.kts ./

RUN chmod +x ./gradlew

COPY src ./src
RUN ./gradlew bootJar --no-daemon -x test

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]