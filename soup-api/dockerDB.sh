#!/bin/bash

CONTAINER_NAME="soup-postgres-db"
POSTGRES_USER="soup-db-user"
POSTGRES_PASSWORD="IUJwwa1213WA1!wnfwpo484m"
POSTGRES_DB="soup_db"
SCHEMA_NAME="soup"

echo "🚀 Bereite Datenbank-Setup vor..."

docker rm -f $CONTAINER_NAME > /dev/null 2>&1

docker run --name $CONTAINER_NAME \
  -e POSTGRES_USER=$POSTGRES_USER \
  -e POSTGRES_PASSWORD=$POSTGRES_PASSWORD \
  -e POSTGRES_DB=$POSTGRES_DB \
  -p 5432:5432 \
  -d postgres:17-alpine

echo "⏳ Warte auf Datenbank-Initialisierung..."
until docker exec $CONTAINER_NAME pg_isready -U $POSTGRES_USER -d $POSTGRES_DB > /dev/null 2>&1; do
  echo -n "."
  sleep 1
done

echo -e "\n✅ Datenbank '$POSTGRES_DB' ist bereit!"

echo "🛠 Erstelle Schema '$SCHEMA_NAME' in Datenbank '$POSTGRES_DB'..."
docker exec -u postgres $CONTAINER_NAME psql -U $POSTGRES_USER -d $POSTGRES_DB -c "CREATE SCHEMA IF NOT EXISTS \"$SCHEMA_NAME\";"

echo "🔎 Verifiziere Struktur in '$POSTGRES_DB':"
docker exec -u postgres $CONTAINER_NAME psql -U $POSTGRES_USER -d $POSTGRES_DB -c "\dn"

echo "🎉 Fertig! User, DB und Schema sind einsatzbereit."