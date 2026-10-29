FROM eclipse-temurin:21-jre
WORKDIR /app
COPY build/libs/depotflow-0.1.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]