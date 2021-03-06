test:
	mvn test

build:
	mvn package

# build Docker image via docker-compose
docker-build: build
	docker-compose build

# run postgres DB container, flyway container to bootstrap DB, and API container
docker-run:
	docker-compose up

# connect to a running DB container
connect:
	docker exec -it seasonal-postgres bash
	# after connecting, run this command to inspect the DB:
	# psql -U seasonaladmin seasonalpostgres