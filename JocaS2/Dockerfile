# Usando OpenJDK 17 como base
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho no container
WORKDIR /app

# Copia o JAR gerado para dentro do container
COPY target/JocaS2-0.0.1-SNAPSHOT.jar app.jar


# Define a porta que a aplicação vai rodar
EXPOSE 25000

# Comando para rodar o Spring Boot
CMD ["java", "-jar", "app.jar"]
