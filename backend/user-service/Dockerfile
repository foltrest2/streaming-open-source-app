# syntax=docker/dockerfile:1
###############################################################################
# Stage 1: Build with Maven
###############################################################################
FROM maven:3.9.5-eclipse-temurin-17 AS build
WORKDIR /workspace

# 1) Copiamos solo pom.xml para aprovechar cache de dependencias
COPY pom.xml .

# 2) Descargar dependencias (cacheable). Opcional -B para batch mode.
RUN mvn -B dependency:go-offline

# 3) Copiamos el código fuente y empaquetamos (skip tests por defecto aquí)
COPY src ./src
RUN mvn -B package -DskipTests

###############################################################################
# Stage 2: Runtime (JRE liviano)
###############################################################################
FROM eclipse-temurin:17-jre-jammy
ARG JAR_FILE=target/*.jar

# metadata útiles
LABEL maintainer="Julian Riascos <riascosjulian5@gmail.com>"
WORKDIR /app

# Copiamos el jar desde la etapa de build; lo renombramos a app.jar
COPY --from=build /workspace/${JAR_FILE} app.jar

# Creamos usuario no-root y ajustamos permisos
RUN groupadd -r spring && useradd -r -g spring spring \
    && chown -R spring:spring /app

# Variables de entorno útiles
ENV JAVA_OPTS=""
ENV SPRING_PROFILES_ACTIVE=prod

# Exponer puerto interno (Spring Boot por defecto 8080)
EXPOSE 8080

# Healthcheck (requiere que tengas actuator o /health disponible)
HEALTHCHECK --interval=30s --timeout=5s --start-period=10s CMD wget --no-verbose --tries=1 --spider http://localhost:8080/actuator/health || exit 1

# Ejecutar como usuario no-root
USER spring

# ENTRYPOINT que permite pasar JAVA_OPTS desde docker run / compose
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
