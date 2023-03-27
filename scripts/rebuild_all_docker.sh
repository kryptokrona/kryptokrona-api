#!/bin/sh

# remove old images
docker-compose down --rmi all

# start docker compose with all services
docker-compose -f ../docker-compose.yml up -V -d --build --force-recreate