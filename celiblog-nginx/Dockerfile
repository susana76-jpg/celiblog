FROM nginx:latest

COPY html/index.html /etc/nginx/html/index.html
#COPY conf/nginx.conf /etc/nginx/conf.d/default.conf

RUN apt-get update  \
    && apt-get install -y iputils-ping \
    && apt-get install -y certbot \
    && apt-get install -y openssl

EXPOSE 80