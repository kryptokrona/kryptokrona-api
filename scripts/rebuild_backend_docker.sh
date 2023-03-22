#!/bin/sh

# removing old images
docker rm kryptokrona_api_db
docker rm kryptokrona_api_backend
docker rm kryptokrona_db_liquibase
docker rm kryptokrona_api_prometheus
docker rm kryptokrona_api_keycloak

# start docker compose with backend (used for developing the monitor)
docker-compose -f ../docker-compose.yml up -V -d --build --force-recreate db liquibase backend prometheus keycloak