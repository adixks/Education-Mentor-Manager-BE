# 1️⃣ Etap: Budowanie aplikacji
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app

# Skopiuj pliki źródłowe
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# 2️⃣ Etap: Uruchomienie aplikacji
FROM openjdk:17-jdk-slim
WORKDIR /app

# Kopiowanie skompilowanego pliku JAR
COPY --from=builder /app/target/*.jar app.jar

# Ustawienie portu aplikacji
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
