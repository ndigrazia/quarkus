 Using OpenID Connect to Protect JAX-RS Applications
 
 
 mvn io.quarkus:quarkus-maven-plugin:1.3.0.Final:create -DprojectGroupId="tech.donau" -DprojectArtifactId=openid-connect -Dextensions="oidc, resteasy-jsonb"

docker run --name keycloak -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin -p 8180:8080 jboss/keycloak

quarkus.http.port=9090
quarkus.oidc.auth-server-url=http://localhost:8180/auth/realms/quarkus
quarkus.oidc.client-id=backend-service

.\mvnw quarkus:dev -Ddebug=600

If webapp
quarkus.oidc.application-type=web-app
quarkus.http.auth.permission.authenticated.paths=/*
quarkus.http.auth.permission.authenticated.policy=authenticated


curl -X POST -k -H 'Content-Type: application/x-www-form-urlencoded' -i 'https://135.250.138.93:8666/auth/realms/<Realm-Name>/protocol/openid-connect/token' 
--data 'username=<userName>&password=<Password>&client_id=<Client-ID>&grant_type=password&client_secret=7df18c0d-d4c7-47b1-b959-af972684dab0'

ef754ce7-5855-433e-9615-306082180b2e

mvn io.quarkus.platform:quarkus-maven-plugin:2.4.1.Final:create \
    -DprojectGroupId=org.acme \
    -DprojectArtifactId=security-keycloak-authorization-quickstart \
    -Dextensions="oidc,keycloak-authorization,resteasy-jackson" \
    -DnoExamples
	
	<dependency>
		<groupId>io.quarkus</groupId>
		<artifactId>quarkus-oidc</artifactId>
	</dependency>
	<dependency>
		<groupId>io.quarkus</groupId>
		<artifactId>quarkus-keycloak-authorization</artifactId>
	</dependency>
	
Using Keycloak to centralize your authorizations

mvn io.quarkus:quarkus-maven-plugin:1.3.0.Final:create \
-DprojectGroupId=tech.donau \
-DprojectArtifactId=openid-connect \
-Dextensions="oidc, resteasy-jsonb"

docker run --name keycloak -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin -p 8180:8080 jboss/keycloak

quarkus.oidc.auth-server-url=http://localhost:8180/auth/realms/quarkus
quarkus.oidc.client-id=backend-service

If webapp
quarkus.oidc.application-type=web-app
quarkus.http.auth.permission.authenticated.paths=/*
quarkus.http.auth.permission.authenticated.policy=authenticated

./mvnw quarkus:add-extension -Dextension="keycloak-authorization"

quarkus.oidc.credentials.secret=secret
quarkus.keycloak.policy-enforcer.enable=true

578da2d0-be8d-4e62-b3b1-f82ba798d658

    eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI2aE52R2RZQ2tpdV9aMTZjU3ROWExBdm1TYkl3cm5kMEVmVzE2U0FFVjJFIn0.eyJleHAiOjE2MzYxNDE2NjksImlhdCI6MTYzNjE0MDQ2OSwianRpIjoiOGM4MWRhY2YtZGE2NC00MTA4LTkyZGUtZWYxZDdjOWI0NGZjIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MTgwL2F1dGgvcmVhbG1zL3F1YXJrdXMtcmVhbG0tMiIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiJiMDIyNTA3OC05Y2E2LTQ3Y2MtOWZjMi1jODJjODhlMjEzNWMiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJiYWNrZW5kLXNlcnZpY2UiLCJzZXNzaW9uX3N0YXRlIjoiZmU1NDQ3NTMtNDU0NC00ODQ3LTk3NmYtNDg4YjE3YTc2MjI3IiwiYWNyIjoiMSIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsImFkbWluIiwidW1hX2F1dGhvcml6YXRpb24iLCJ1c2VyIiwiZGVmYXVsdC1yb2xlcy1xdWFya3VzLXJlYWxtLTIiXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJzaWQiOiJmZTU0NDc1My00NTQ0LTQ4NDctOTc2Zi00ODhiMTdhNzYyMjciLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsInByZWZlcnJlZF91c2VybmFtZSI6ImFkbWluIn0.2Ez0dGETsOxtgu-BRkAv7uFMXt8CzDBSnTOgQ5leP_PwfAPFA6ngsYAVWMm9Rwvf2UMTntE0QIRVSfHU7ebBayCQfbAfExES8Bo1gxmZpjHNb_nNftNS-tTlylwf0mnc2ZaeRkhUKcr1Kyyvpx896iyVqPGypE5LwTBPT2QZf4z8l9Dy1Io8AhvfgZaoZy36TNfCosyC9z1quTPT3fLUfegA1_T02U2HPZH918HjBlm_sq860j3nLR8ZsABVAwpULsdN5y9BgknFn_A3nVpsuKNd-5Xb6bDMc28K7bKULkrZKQxv_0SAKIFg2OHuWNrBJwDTiEZBeoSC74EjRuc37Q
