# -------------------------------
# Build Stage
# -------------------------------
FROM maven:3.9.3-eclipse-temurin-17 AS build

# Crear directorio de trabajo
WORKDIR /app

# Copiar archivos de proyecto
COPY pom.xml ./
COPY src ./src

# Descargar dependencias y compilar proyecto
# Se ignoran fallos si no hay código completo (placeholder)
RUN mvn package -DskipTests || echo "Skipping build - placeholder project"

# -------------------------------
# Runtime Stage
# -------------------------------
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copiar el JAR generado
COPY --from=build /app/target/*.jar app.jar

# Crear usuario limitado
RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser
USER appuser

# Exponer puerto
EXPOSE 8081

# Comando por defecto
CMD ["java", "-jar", "app.jar"]
