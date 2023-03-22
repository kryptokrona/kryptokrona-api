#!/bin/sh

# start docker compose with backend (used for developing the monitor)
docker-compose -f ../docker-compose.yml up -d db liquibase backend prometheus keycloak