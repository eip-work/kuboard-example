#!/bin/bash

export JAR_FILE_RENAME="gateway-$SPRING_PROFILES_ACTIVE.jar"
export PINPOINT_APP_NAME="gateway-$SPRING_PROFILES_ACTIVE"

echo "pinpoint does not support spring cloud gateway, https://github.com/naver/pinpoint/issues/5267"
sed -i "/profiler.entrypoint=/ s/=.*/=com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter.filter/" /pinpoint-agent/pinpoint.config

/entrypoint.sh
