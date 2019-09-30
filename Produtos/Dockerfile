FROM mongo

ENV MONGO_INITDB_DATABASE products

COPY init-database.js /docker-entrypoint-initdb.d/

EXPOSE 27017