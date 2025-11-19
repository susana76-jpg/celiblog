# Imagen base oficial de Node
FROM node:20
# Directorio de trabajo dentro del contenedor
WORKDIR /app
# Copiamos los archivos de configuración primero (para aprovechar la cache de Docker)
COPY package*.json ./
# Instalamos dependencias de forma limpia (usa package-lock.json)
RUN npm ci
# Copiamos el resto del proyecto
COPY . .
# Construimos la aplicación Nuxt para producción
RUN npm run build
# Exponemos el puerto que usa Nuxt
EXPOSE 3000
# Comando de arranque
CMD ["npm", "run", "start"]
