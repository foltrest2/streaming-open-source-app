# backend/catalog/Dockerfile
FROM node:20-alpine

# Crear usuario sin privilegios
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

WORKDIR /app

# Copiar package files primero
COPY package*.json ./

# Instalar dependencias
RUN npm ci --production

# Copiar el resto del código
COPY . .

# Cambiar a usuario limitado
USER appuser

# Exponer puerto
EXPOSE 4000

CMD ["node", "server.js"]
