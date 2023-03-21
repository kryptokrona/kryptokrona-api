#!/bin/sh

# remove old images
docker rmi postgres
docker rmi kryptokrona_api_backend
docker rmi kryptokrona_api_monitor
docker rmi kryptokrona_api_liquibase

# start docker compose with all services
docker-compose -f ../docker-compose.yml up -V -d --build --force-recreate