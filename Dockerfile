FROM gradle:8.14-jdk21 AS builder
WORKDIR /app
COPY . .
RUN ./gradlew build -x test --no-daemon
RUN ls -la /app/build/libs/

FROM amazoncorretto:21.0.7-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/ /app/libs/
RUN cp /app/libs/tarantool-service-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]