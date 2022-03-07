#!/bin/bash

# turn on bash's job control
set -m

# Filebeat (comment to disable file beat)
#filebeat setup --index-management
#filebeat -e &

# Application (with agents, should be used when creating an image for production)
#java -Duser.timezone=GMT \
#  -Dlogging.config=/etc/event-processor/logback.xml \
#  -javaagent:$OTEL_AGENT_PATH \
#  -Dotel.exporter=otlp \
#  -Dotel.exporter.otlp.endpoint=$OTEL_EXPORTER_ENDPOINT \
#  -javaagent:"$JOLOKIA_AGENT_PATH"=port=$JOLOKIA_AGENT_PORT,host=0.0.0.0 \
#  -jar /opt/event-processor/event-processor.jar \
#  --spring.config.location=classpath:/application.yml,file:/etc/event-processor/application.yml

# Application (without agents)
java -Duser.timezone=GMT \
  -jar /opt/users-api/users-api.jar \
  --spring.config.location=classpath:/application.properties
