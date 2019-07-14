FROM eipwork/mysql:5.7.26-1.1.11

LABEL maintainer="shaohq@foxmail.com"

#把数据库初始化数据的文件复制到工作目录下
RUN  mv /etc/my.cnf /etc/my.cnf.backup
COPY docker/my.cnf /etc/my.cnf
COPY docker/init_sql/*.sql /init_sql/

EXPOSE 3306
EXPOSE 9104

ENV ENABLE_EUREKA_CLIENT=TRUE
ENV eureka.name=db-example
ENV eureka.port=80
ENV eureka.management.port=9104
ENV eureka.serviceUrl.default=http://monitor-eureka:9000/eureka