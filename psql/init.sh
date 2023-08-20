#!/bin/bash

# this script is run when the docker container is built
# it imports the base database structure and create the database for the tests

DATABASE_NAME=${POSTGRES_DB}
DB_DUMP_LOCATION="/tmp/psql_data/dump.sql"

whoami

# remove indentation
sed "s/^[ \t]*//" -i "$DB_DUMP_LOCATION"

# remove comments
sed '/^--/ d' -i "$DB_DUMP_LOCATION"

# remove new lines
sed ':a;N;$!ba;s/\n/ /g' -i "$DB_DUMP_LOCATION"

# remove other spaces
sed 's/  */ /g' -i "$DB_DUMP_LOCATION"

# remove firsts line spaces
sed 's/^ *//' -i "$DB_DUMP_LOCATION"

# append new line at the end (suggested by @Nicola Ferraro)
sed -e '$a\' -i "$DB_DUMP_LOCATION"

echo "*** import sql_dump ***"
psql -d "$DATABASE_NAME" -U "${POSTGRES_USER}" -f "$DB_DUMP_LOCATION"
echo "*** imported dump ***"

echo "*** DATABASE CREATED ***"