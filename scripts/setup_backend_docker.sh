#!/bin/sh

# inject keycloak hostname
sudo -- sh -c -e "echo '127.0.0.1   keycloak' >> /etc/hosts"

# start docker compose with backend (used for developing the monitor)
docker-compose -f ../docker-compose.yml up -d db liquibase backend prometheus keycloak