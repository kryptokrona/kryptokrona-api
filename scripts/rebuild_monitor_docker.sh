#!/bin/sh

# remove old images
docker rm kryptokrona_api_db
docker rm kryptokrona_api_monitor
docker rm kryptokrona_api_liquibase

# start docker compose to setup monitor (used for developing the backend)
docker-compose -f ../docker-compose.yml up -V -d --build --force-recreate db liquibase monitor