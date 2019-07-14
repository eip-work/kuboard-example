#!/bin/bash

## db-example
tag=eipsample/example-db-example

cd ./db-example

docker build -t $tag:latest .

if test "$1" != ""; then
  docker push $tag:latest
  docker tag $tag:latest $tag:$1
  docker push $tag:$1
fi

cd ..


## svc-example
tag=eipsample/example-svc-example

cd ./svc-example

mvn clean package -Dmaven.test.skip=true

docker build -t $tag:latest .

if test "$1" != ""; then
  docker push $tag:latest
  docker tag $tag:latest $tag:$1
  docker push $tag:$1
fi

cd ..


## gateway-example
tag=eipsample/example-gateway-example

cd ./gateway-example

mvn clean package -Dmaven.test.skip=true

docker build -t $tag:latest .

if test "$1" != ""; then
  docker push $tag:latest
  docker tag $tag:latest $tag:$1
  docker push $tag:$1
fi

cd ..

## web-example
tag=eipsample/example-web-example

cd ./web-example

npm install --registry=https://registry.npm.taobao.org
npm run build

docker build -t $tag:latest .

if test "$1" != ""; then
  docker push $tag:latest
  docker tag $tag:latest $tag:$1
  docker push $tag:$1
fi

cd ..

## cloud-eureka
tag=eipsample/example-cloud-eureka

cd ./cloud-eureka

mvn clean package -Dmaven.test.skip=true

docker build -t $tag:latest .

if test "$1" != ""; then
  docker push $tag:latest
  docker tag $tag:latest $tag:$1
  docker push $tag:$1
fi

