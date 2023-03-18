#!/bin/sh

# start docker compose with a database, frontend and backend
docker-compose -f ../docker-compose.yml up -d --build --force-recreate prometheus db frontend backend