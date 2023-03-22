#!/bin/sh

# remove old images
docker rm kryptokrona_api_db
docker rm kryptokrona_api_backend
docker rm kryptokrona_api_monitor
docker rm kryptokrona_api_liquibase
docker m kryptokrona_api_keycloak

# start docker compose with all services
docker-compose -f ../docker-compose.yml up -V -d --build --force-recreate