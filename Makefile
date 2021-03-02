# run postgres DB container, flyway container to bootstrap DB, and API container
run:
	docker-compose up

# connect to a running DB container
connect:
	docker exec -it seasonal-postgres bash
	# after connecting, run this command to inspect the DB:
	# psql -U seasonaladmin seasonalpostgres