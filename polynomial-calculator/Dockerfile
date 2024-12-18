# Étape 1 : Construire le projet avec Maven
FROM maven:3.8.5-openjdk-17 AS build

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le pom.xml et télécharger les dépendances
COPY pom.xml .
RUN mvn dependency:go-offline

# Copier tout le projet et construire l'application
COPY src ./src
RUN mvn clean package -DskipTests

# Étape 2 : Exécuter l'application avec OpenJDK
FROM openjdk:17-jdk-slim

# Copier le jar généré dans le conteneur
COPY --from=build /app/target/polynomial-calculator-0.0.1-SNAPSHOT.jar /app/polynomial-calculator.jar

# Exposer le port 8080
EXPOSE 8080

# Démarrer l'application
CMD ["java", "-jar", "/app/polynomial-calculator.jar"]
