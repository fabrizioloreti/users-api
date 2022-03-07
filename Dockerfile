FROM openjdk:8
ARG JAR_FILE="target/users-api*.jar"

COPY scripts/docker-entrypoint.sh /usr/local/bin/
RUN  ln -s -f usr/local/bin/docker-entrypoint.sh /docker-entrypoint.sh  && chmod +x docker-entrypoint.sh # backwards compat

COPY ${JAR_FILE} /opt/users-api/users-api.jar
ENTRYPOINT ["docker-entrypoint.sh"]