# Imagen base con Java
FROM openjdk:21-jdk-slim

# Autor
LABEL authors="Ricardo Moreno"

# Directorio dentro del contenedor
WORKDIR /app

# Copiar el jar generado por Maven
COPY target/users-api-0.0.1-SNAPSHOT.jar app.jar

# Exponer puerto de Spring Boot
EXPOSE 8080

# Ejecutar la aplicación
ENTRYPOINT ["java","-jar","app.jar"]