#!/bin/sh

# start docker compose with a database and backend
docker-compose -f ../docker-compose.yml up -d --build --force-recreate db backend prometheus