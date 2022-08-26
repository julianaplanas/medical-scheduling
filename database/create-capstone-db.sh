#!/bin/bash
#!/bin/bash
export PGPASSWORD='postgres1'

BASEDIR=$(dirname $0)
psql -U postgres -f "$BASEDIR/01-create-database.sql" &&
psql -U postgres -d medical_scheduling -f "$BASEDIR/02-create-schema.sql" &&
psql -U postgres -d medical_scheduling -f "$BASEDIR/03-create-users.sql" &&
psql -U postgres -d medical_scheduling -f "$BASEDIR/04-insert-seed-data.sql"