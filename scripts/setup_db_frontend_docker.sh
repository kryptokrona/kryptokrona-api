#!/bin/sh

# start docker compose with a database and frontend
docker-compose -f ../docker-compose.yml up -d db frontend