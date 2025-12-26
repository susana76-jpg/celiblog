FROM nginx:latest

RUN apt install nginx-common

COPY html/index.html /etc/nginx/html/index.html
#COPY conf/nginx.conf /etc/nginx/conf.d/default.conf

COPY conf/celiblog.conf /etc/nginx/sites-available/celiblog.es
COPY conf/api.celiblog.conf /etc/nginx/sites-available/api.celiblog.es

RUN ln -s /etc/nginx/sites-available/celiblog.es /etc/nginx/sites-enabled/
RUN ln -s /etc/nginx/sites-available/api.celiblog.es /etc/nginx/sites-enabled/

EXPOSE 80