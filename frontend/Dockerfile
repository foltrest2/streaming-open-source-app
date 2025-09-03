# Usamos la imagen oficial de Node.js
FROM node:20-alpine

# Creamos usuario limitado
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# Directorio de trabajo
WORKDIR /app

# Copiamos package.json y package-lock.json (o pnpm-lock.yaml)
COPY package*.json ./

# Instalamos dependencias sin root
RUN npm install --legacy-peer-deps

# Copiamos el resto del código
COPY . .

# Cambiamos a usuario no root
USER appuser

# Build del proyecto (para React / Next.js)
RUN npm run build

# Exponemos el puerto donde corre la app (por ejemplo 3000)
EXPOSE 3000

# Comando para arrancar el servidor
CMD ["npm", "start"]
