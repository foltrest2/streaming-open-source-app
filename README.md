# 🎬 Streaming Platform – Microservices + DevSecOps  

[![Docker](https://img.shields.io/badge/Docker-✔-2496ED?logo=docker&logoColor=white)](https://www.docker.com/) 
[![Keycloak](https://img.shields.io/badge/Auth-Keycloak-FF6C37?logo=keycloak)](https://www.keycloak.org/) 
[![Next.js](https://img.shields.io/badge/Frontend-Next.js-000000?logo=nextdotjs)](https://nextjs.org/) 
[![Node.js](https://img.shields.io/badge/Backend-Node.js-339933?logo=node.js&logoColor=white)](https://nodejs.org/)  
[![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-336791?logo=postgresql)](https://www.postgresql.org/) 
[![MongoDB](https://img.shields.io/badge/Database-MongoDB-47A248?logo=mongodb&logoColor=white)](https://www.mongodb.com/)  
[![Security](https://img.shields.io/badge/Security-DevSecOps-critical?logo=security)](https://owasp.org/)  

---

## 📖 Descripción  

Este proyecto es una **plataforma de streaming open source** diseñada bajo una **arquitectura de microservicios** con enfoque **DevSecOps desde el inicio**.  

La aplicación incluye:  
- Autenticación y autorización con **Keycloak (OIDC + JWT + MFA)**.  
- Microservicios independientes para **usuarios, catálogo, facturación y streaming**.  
- **Bases de datos desacopladas** para cada servicio.  
- **Pipeline CI/CD con seguridad integrada** (Trivy, OWASP ZAP, Snyk).  
- **API Gateway seguro** con control de acceso, rate limiting y CORS.  
- **Streaming de prueba** usando la API gratuita de [TMDB](https://www.themoviedb.org/documentation/api).  

---

## 🏗️ Arquitectura  

```mermaid
flowchart TD
    A[Frontend Next.js] -->|OIDC| B[Keycloak]
    A --> C[API Gateway]
    C --> D[Catalog Service]
    C --> E[User Service]
    C --> F[Billing Service]
    C --> G[Streaming Service]
    D -->|PostgreSQL| H[(DB Catalog)]
    E -->|MongoDB| I[(DB Users)]
    F -->|PostgreSQL| J[(DB Billing)]
    G --> K[(Media Storage)]
