#!/bin/sh

# inject keycloak hostname
sudo -- sh -c -e "echo '127.0.0.1   keycloak' >> /etc/hosts"

# start docker compose with all services
docker-compose -f ../docker-compose.yml up -d