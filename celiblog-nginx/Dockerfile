FROM nginx:latest

COPY html/index.html /etc/nginx/html/index.html
COPY conf/nginx.conf /etc/nginx/conf.d/default.conf
COPY cert/fullchain1.pem /etc/nginx/ssl/fullchain.pem
COPY cert/privkey1.pem /etc/nginx/ssl/privkey.pem

RUN apt-get update && apt-get install -y iputils-ping

EXPOSE 8080