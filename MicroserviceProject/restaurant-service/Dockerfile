# Utiliser une image OpenJDK Runtime comme image parent
FROM openjdk:17-jdk-alpine
COPY target/resto.jar .
# Exposer le port sur lequel l'application va écouter
EXPOSE 9060
# Commande pour exécuter l'application Spring Boot
ENTRYPOINT ["java", "-jar", "resto.jar"]
