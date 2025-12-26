FROM nginx:latest

COPY html/index.html /etc/nginx/html/index.html
COPY conf/nginx.conf /etc/nginx/conf.d/default.conf

RUN mkdir /etc/nginx/sites-available
RUN mkdir /etc/nginx/sites-enabled

RUN cp conf/celiblog.http.conf /etc/nginx/sites-available/celiblog.es
RUN cp conf/api.celiblog.http.conf /etc/nginx/sites-available/api.celiblog.es

RUN ln -s /etc/nginx/sites-available/celiblog.es /etc/nginx/sites-enabled/
RUN ln -s /etc/nginx/sites-available/api.celiblog.es /etc/nginx/sites-enabled/

EXPOSE 80