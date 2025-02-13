# Użyj oficjalnego obrazu JDK
FROM openjdk:17-jdk-slim

# Ustaw katalog roboczy
WORKDIR /app

# Skopiuj plik JAR do kontenera
COPY target/coding-api-0.0.1-SNAPSHOT.jar coding-api-0.0.1-SNAPSHOT.jar

# Ustawienie portu
EXPOSE 8080

# Komenda uruchamiająca aplikację
CMD ["java", "-jar", "coding-api-0.0.1-SNAPSHOT.jar"]
