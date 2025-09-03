# Usamos la imagen oficial de Node.js
FROM node:20-alpine

# Crear usuario limitado para seguridad
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Directorio de trabajo
WORKDIR /app

# Copiamos package.json y package-lock.json
COPY package*.json ./

# Instalamos dependencias
RUN npm install --legacy-peer-deps

# Copiamos el resto del código
COPY . .

# Cambiamos a usuario no root
USER appuser

# Exponemos el puerto donde correrá el Gateway
EXPOSE 8080

# Comando para iniciar el Gateway
CMD ["node", "server.js"]
