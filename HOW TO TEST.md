¡Perfecto! 😎 Vamos a dejar un **README final de testing** para tu proyecto seguro. Este README explica **paso a paso cómo levantar todo el stack, probar las conexiones y verificar que todo funcione**.

---

# Proyecto Microservicios Streaming – Testing Local

## 1️⃣ Preparar variables de entorno

1. Copia el archivo `.env.example` como `.env` dentro de `infrastructure/`:

```bash
cd infrastructure
cp .env.example .env
```

2. Asegúrate de definir contraseñas seguras:

```env
USERS_DB_PASSWORD=superSecretUserPass
CATALOG_DB_PASSWORD=superSecretCatalogPass
BILLING_DB_PASSWORD=superSecretBillingPass
KEYCLOAK_ADMIN_PASSWORD=SuperSecretKeycloakPass
```

> **Importante:** nunca subir `.env` a GitHub.

---

## 2️⃣ Levantar los contenedores

Desde `infrastructure/`:

```bash
docker-compose up --build
```

* Esto construye todos los Dockerfiles.
* Levanta Redis, Keycloak, PostgreSQL y todos los microservicios.
* Ejecuta automáticamente los scripts `init.sql` para crear tablas y datos de prueba.

---

## 3️⃣ Verificar que las bases de datos funcionan

### Conectar a Users DB:

```bash
docker exec -it postgres-users psql -U $USERS_DB_USER -d $USERS_DB_NAME
```

Prueba:

```sql
SELECT * FROM users;
SELECT * FROM roles;
```

### Conectar a Catalog DB:

```bash
docker exec -it postgres-catalog psql -U $CATALOG_DB_USER -d $CATALOG_DB_NAME
```

Prueba:

```sql
SELECT * FROM movies;
SELECT * FROM genres;
```

### Conectar a Billing DB:

```bash
docker exec -it postgres-billing psql -U $BILLING_DB_USER -d $BILLING_DB_NAME
```

Prueba:

```sql
SELECT * FROM plans;
SELECT * FROM subscriptions;
```

---

## 4️⃣ Verificar Redis

```bash
docker exec -it redis redis-cli ping
```

Deberías recibir:

```
PONG
```

---

## 5️⃣ Verificar Keycloak

* Abre en tu navegador: [http://localhost:8082](http://localhost:8082)
* Usuario admin: `${KEYCLOAK_ADMIN}`
* Contraseña admin: `${KEYCLOAK_ADMIN_PASSWORD}`

> Puedes crear realms, clientes y roles para testing de autenticación.

---

## 6️⃣ Verificar microservicios

| Servicio  | URL Local                                                | Puerto |
| --------- | -------------------------------------------------------- | ------ |
| Users     | [http://localhost/users](http://localhost/users)         | 5000   |
| Catalog   | [http://localhost/catalog](http://localhost/catalog)     | 4000   |
| Streaming | [http://localhost/streaming](http://localhost/streaming) | 8080   |
| Billing   | [http://localhost/billing](http://localhost/billing)     | 8081   |

Prueba con `curl` o Postman:

```bash
curl http://localhost:5000/health
curl http://localhost:4000/health
curl http://localhost:8080/health
curl http://localhost:8081/health
```

> Cada microservicio debería responder un JSON indicando que está vivo.

---

## 7️⃣ Parar todo

```bash
docker-compose down
```

* Los volúmenes de Postgres (`users_data`, `catalog_data`, `billing_data`) persisten los datos.
* Para limpiar todo, incluyendo volúmenes:

```bash
docker-compose down -v
```

---

✅ Con esto puedes levantar **todo tu stack seguro** localmente y probar:

* Bases de datos con usuarios limitados
* Redis funcionando
* Keycloak seguro
* Microservicios conectados a DB, Redis y Keycloak

---

Si quieres, puedo hacer una **versión final resumida para el README del repositorio**, lista para GitHub, con **diagrama de arquitectura incluido**, **para que los reclutadores lo vean profesional y seguro**.

¿Quieres que haga eso?
