# Użycie oficjalnego obrazu Javy jako bazowego
FROM eclipse-temurin:17-jdk-alpine

# Ustawienie katalogu roboczego wewnątrz kontenera
WORKDIR /app

# Skopiowanie pliku JAR aplikacji do obrazu
COPY target/StronaDoNaukiWybranegoJezykaProgramowania-0.0.1-SNAPSHOT.jar app.jar

# Otworzenie portu 8080 w kontenerze
EXPOSE 8080

# Polecenie uruchamiające aplikację
ENTRYPOINT ["java", "-jar", "app.jar"]
