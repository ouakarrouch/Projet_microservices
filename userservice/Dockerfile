# Étape 1 : Utiliser une image de base pour Java
FROM openjdk:17-jdk-slim AS build

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier pom.xml et télécharger les dépendances Maven
COPY pom.xml .

RUN mvn dependency:go-offline

# Copier le code source dans le conteneur
COPY src ./src

# Construire le package avec Maven
RUN mvn clean package -DskipTests

# Étape 2 : Utiliser une image plus légère pour l'exécution
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le fichier jar généré à partir de l'étape précédente
COPY --from=build /app/target/user-service-0.0.1-SNAPSHOT.jar /app/user-service.jar

# Exposer le port de l'application
EXPOSE 8081

# Commande pour démarrer l'application
CMD ["java", "-jar", "user-service.jar"]
