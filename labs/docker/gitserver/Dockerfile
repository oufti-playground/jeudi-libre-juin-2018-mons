FROM gitea/gitea:1.4.2

# We need tini to reap child processes
RUN apk add --no-cache jq tini

ENV EXTERNAL_URL=http://localhost:3000/git \
    EXTERNAL_DOMAIN=localhost \
    SERVICE_CONFIG_FILE=/data/gitea/conf/app.ini \
    LOAD_SSH_KEY_FROM_JENKINS=false \
    JENKINS_URL=http://jenkins:8080/jenkins \
    FIRST_USER=butler \
    JENKINS_ADMIN_USER=butler

COPY ./setup-gitea.sh /app/setup-gitea.sh
COPY ./app.ini "${SERVICE_CONFIG_FILE}.tmpl"
COPY ./entrypoint.sh /usr/local/bin/entrypoint.sh
COPY ./set-key-from-jenkins-to-gitea.sh /usr/local/bin/set-key-from-jenkins-to-gitea.sh

# Custom SSH
EXPOSE 5022

# Custom Entrypoint
ENTRYPOINT ["/sbin/tini","-g","--"]
CMD ["bash","/usr/local/bin/entrypoint.sh"]

# Pre-configure the embeded Gitserver when you build an image inheriting from this one
ONBUILD ARG first_user
ONBUILD ARG SOURCE_REPO_CONFIG
ONBUILD RUN bash /app/setup-gitea.sh start-gitserver
ONBUILD VOLUME "/app/data"
