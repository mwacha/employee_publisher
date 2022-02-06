LOCAL_USERNAME=postgres
LOCAL_PASSWORD=postgres
LOCAL_DB_URL=jdbc:postgresql://localhost:5432/integrations

# Run migration for local environment
execute-migration:
	 mvn flyway:migrate -Dflyway.user=postgres -Dflyway.url=jdbc:postgresql://localhost:5432/integrations -Dflyway.password=postgres -f pom.xml

validation-migration:
	 mvn flyway:validate -Dflyway.user=postgres -Dflyway.url=jdbc:postgresql://localhost:5432/integrations -Dflyway.password=postgres -f pom.xml

start-environment:
	docker-compose -p integrations_env -f docker-compose.yaml up

down-environment:
	docker-compose -p integrations_env -f docker-compose.yaml down
