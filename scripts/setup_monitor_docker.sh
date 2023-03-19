#!/bin/sh

# start docker compose with a database and monitor
docker-compose -f ../docker-compose.yml up -d db liquibase monitor