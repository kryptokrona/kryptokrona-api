#!/bin/sh

# removing old images
docker-compose down --rmi all

# start docker compose with backend (used for developing the monitor)
docker-compose -f ../docker-compose.yml up -V -d --build --force-recreate db liquibase backend slate prometheus keycloak