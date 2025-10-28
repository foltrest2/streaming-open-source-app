# backend/users/Dockerfile
FROM python:3.12-slim

# Crear usuario sin privilegios
RUN useradd -m appuser

# Crear directorio de trabajo
WORKDIR /app

# Copiar solo requirements primero para aprovechar cache
COPY requirements.txt .

# Instalar dependencias como root
RUN pip install --no-cache-dir -r requirements.txt

# Copiar el resto del código
COPY . .

# Cambiar a usuario limitado
USER appuser

# Variables de entorno sensibles (se pueden sobreescribir en docker-compose)
ENV FLASK_ENV=production

# Exponer puerto
EXPOSE 5005

CMD ["python", "app.py"]
