#!/bin/sh

# start docker compose with monitor (used for developing the monitor)
docker-compose -f ../docker-compose.yml up -d db liquibase monitor keycloak