## API jar builder
FROM adoptopenjdk:11-jdk-hotspot as builder
WORKDIR /app
COPY . .

RUN ./gradlew build


## API jar Release
FROM adoptopenjdk:11-jre-hotspot as release
WORKDIR /app

COPY --from=builder /app/despachalo-configuration/build/libs/*.jar app.jar

EXPOSE 8080

VOLUME /app/logs

ENTRYPOINT ["java","-jar","app.jar"]
