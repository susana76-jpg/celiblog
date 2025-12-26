FROM ubuntu:24.04

RUN apt-get update \
    && apt-get install -y iputils-ping lsof \
    && apt-get install -y certbot openssl

RUN mkdir -p /etc/letsencrypt/live/celiblog.es/

ENTRYPOINT ["tail"]
CMD ["-f","/dev/null"]