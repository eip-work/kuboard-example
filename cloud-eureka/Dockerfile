FROM eipwork/jdk:1.0.0

ARG JAR_FILE_NAME=cloud-eureka-0.0.1-SNAPSHOT.jar
ARG PORT=9200
ARG MANAGEMENT_PORT=9500

COPY ./target/lib /eip-work/lib
COPY ./target/$JAR_FILE_NAME.original /eip-work/app.jar

ENV CLASSPATH=/eip-work/lib

EXPOSE $PORT
EXPOSE $MANAGEMENT_PORT

WORKDIR /eip-work

ENTRYPOINT ["java", "-jar", "/eip-work/app.jar"]