#!/bin/sh

# start docker compose with a database and frontend
docker-compose -f ../docker-compose.yml up -V -d --build --force-recreate db liquibase frontend