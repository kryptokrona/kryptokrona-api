FROM gradle:8-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle shadowJar --no-daemon

FROM openjdk:17.0.2
EXPOSE 8080:8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/kryptokrona-api-shadow.jar /app/ktor.jar
ENTRYPOINT ["sh", "-c", "sleep 15 && java -jar /app/ktor.jar"]