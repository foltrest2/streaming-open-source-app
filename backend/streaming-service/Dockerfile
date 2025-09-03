# backend/streaming/Dockerfile
FROM golang:1.21-alpine AS builder

# Crear usuario sin privilegios
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

WORKDIR /app

# Copiar código y compilar
COPY . .
RUN go mod download
RUN go build -o streaming .

# Usar imagen más ligera para runtime
FROM alpine:3.18
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

WORKDIR /app
COPY --from=builder /app/streaming .

# Cambiar a usuario limitado
USER appuser

EXPOSE 8080

CMD ["./streaming"]
