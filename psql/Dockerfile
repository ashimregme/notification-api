FROM postgres:15.4
RUN mkdir -p /tmp/psql_data/
RUN chown postgres -R /tmp/psql_data/
COPY dump.sql /tmp/psql_data/
COPY init.sh /docker-entrypoint-initdb.d/