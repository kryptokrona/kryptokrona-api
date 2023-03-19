#!/bin/sh

# start docker compose with all services
docker-compose -f ../docker-compose.yml up -V -d --build --force-recreate