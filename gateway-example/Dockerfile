FROM eipwork/jdk:1.0.0

ARG JAR_FILE_NAME=gateway-0.0.1-SNAPSHOT.jar
ARG PORT=9201
ARG MANAGEMENT_PORT=9001

EXPOSE $PORT
EXPOSE $MANAGEMENT_PORT

WORKDIR /eip
COPY ./target/$JAR_FILE_NAME $JAR_FILE_NAME
COPY ./docker/entrypoint.sh /entrypoint_gateway.sh

RUN chmod +x /entrypoint_gateway.sh

ENV JAR_FILE_NAME=$JAR_FILE_NAME

CMD ["/entrypoint_gateway.sh"]