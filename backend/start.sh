#!/bin/bash

set -e

echo "checking postgreSQL..."

pg_isready -d b12cl

echo "postgreSQL and b12cl are ready."
echo "starting B12CL..."

./mvnw spring-boot:run
