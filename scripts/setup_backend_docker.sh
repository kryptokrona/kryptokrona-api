#!/bin/sh

# start docker compose with a database and backend
docker-compose -f ../docker-compose.yml up -d db liquibase backend prometheus