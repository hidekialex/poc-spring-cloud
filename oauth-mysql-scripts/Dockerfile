FROM mysql:5.7
ENV MYSQL_DATABASE oauth
COPY ./oauth-mysql-scripts/sql-scripts/ /docker-entrypoint-initdb.d/
