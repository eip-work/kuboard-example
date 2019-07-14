FROM eipwork/nginx:1.0.0
LABEL maintainer="shaohq@foxmail.com"

COPY dist /usr/local/nginx/www
COPY docker/nginx.80.conf /usr/local/nginx/conf.d/nginx.80.conf

EXPOSE 80
EXPOSE 443
EXPOSE 9913

ENV ENABLE_EUREKA_CLIENT=TRUE
ENV eureka.name=web-admin
ENV eureka.port=80
ENV eureka.management.port=9913
ENV eureka.serviceUrl.default=http://monitor-eureka:9000/eureka
