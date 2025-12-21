FROM nginx:latest

COPY html/index.html /etc/nginx/html/index.html
COPY conf/nginx.conf /etc/nginx/conf.d/default.conf

RUN apt-get update && apt-get install -y iputils-ping

EXPOSE 8080