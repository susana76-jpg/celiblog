FROM nginx:latest

RUN apt-get update \
    && apt-get install -y nginx-common

COPY html/index.html /etc/nginx/html/index.html
#COPY conf/nginx.conf /etc/nginx/conf.d/default.conf

COPY conf/certbot/celiblog.https.conf /etc/nginx/sites-available/celiblog.es
COPY conf/certbot/api.celiblog.http.conf /etc/nginx/sites-available/api.celiblog.es

RUN ln -s /etc/nginx/sites-available/celiblog.es /etc/nginx/sites-enabled/
RUN ln -s /etc/nginx/sites-available/api.celiblog.es /etc/nginx/sites-enabled/

EXPOSE 80