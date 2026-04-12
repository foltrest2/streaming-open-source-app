# ETAPA 1: Compilación (Build)
FROM mcr.microsoft.com/dotnet/sdk:8.0 AS build-env
WORKDIR /app

# Copiar archivos de proyecto y restaurar dependencias
COPY *.csproj ./
RUN dotnet restore

# Copiar el resto de los archivos y publicar el sitio
COPY . ./
RUN dotnet publish -c Release -o output

# ETAPA 2: Servidor de Producción (Runtime)
FROM nginxinc/nginx-unprivileged:alpine
WORKDIR /usr/share/nginx/html

# Copiar los archivos publicados desde la etapa de compilación
# Nota: La ruta interna de Blazor suele ser /wwwroot después del publish
COPY --from=build-env /app/output/wwwroot .

# Copiar configuración personalizada de Nginx (Opcional pero recomendado)
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 3000

CMD ["nginx", "-g", "daemon off;"]