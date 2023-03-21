#!/bin/sh

# removing old images
docker rmi postgres
docker rmi kryptokrona_api_backend
docker rmi kryptokrona_api_monitor
docker rmi kryptokrona_api_liquibase
docker rmi prom/prometheus:latest

# start docker compose with a database and backend
docker-compose -f ../docker-compose.yml up -V -d --build --force-recreate db liquibase backend prometheus