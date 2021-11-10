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


Using JWT RBAC


 .\mvnw quarkus:dev -Ddebug=600

mvn io.quarkus.platform:quarkus-maven-plugin:2.4.1.Final:create -DprojectGroupId="org.acme"     -DprojectArtifactId=security-jwt-quickstart -DclassName="org.acme.security.jwt.TokenSecuredResource"     -Dpath="/secured" -Dextensions="resteasy,resteasy-jackson,smallrye-jwt,smallrye-jwt-build"

pom.xml:

	<plugin>
        <groupId>org.codehaus.mojo</groupId>
        <artifactId>exec-maven-plugin</artifactId>
        <version>1.4.0</version>
        <configuration>
          <mainClass>org.acme.security.jwt.GenerateToken</mainClass>
          <systemProperties>
            <systemProperty>
              <key>smallrye.jwt.sign.key.location</key>
              <value>privateKey.pem</value>
            </systemProperty>
          </systemProperties>
        </configuration>
    </plugin>
     
	 
Get Token:
mvn exec:java 

#properties
mp.jwt.verify.publickey.location=publicKey.pem
mp.jwt.verify.issuer=DonauTech

Token:
eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJEb25hdVRlY2giLCJ1cG4iOiJqZG9lQHF1YXJrdXMuaW8iLCJncm91cHMiOlsiVXNlciIsIkFkbWluIl0sImJpcnRoZGF0ZSI6IjIwMDEtMDctMTMiLCJpYXQiOjE2MzYzODYxMjAsImV4cCI6MTYzNjM4NjQyMCwianRpIjoiNjY0MTM0NTAtZmIyNC00ZWYwLThlYzMtYzM3M2NhYmY1NWQ1In0.rvx5ZuaEFrP6Folqi7Z2dINTurc59UkzBulD1OMw6WKN0kwTWuPD6RFUQvVAYlHchCVypEXvugESoFvLWBUsKQxw5V0HxGEVM5YAaBgKMnoM9rODKUZRMI0Kj03KEbWBHUETcqwkPX7GAXcWtVgALp8HK4_XS8Eld2ujYOwHY0vZFLhXyiZQaL223MzA4IExyuv2KgFJ7aArKBfO2VofteGBZTQPdsUJxtWdOUBUH11o3ptxaLXTBbnekAFnUkjzwmlucN9oY_2mnCLcQkkDBmhyyRdV2UZEfm09FdBLSqQCuBLHJMCJMSd-wn9Gss5yzPDRlYkyofJ17BqBuBD2rw


Generate RSA Public and Private Key:


openssl genrsa -out publicKey.pem
openssl pkcs8 -topk8 -inform PEM -in publicKey.pem -out privateKey.pem -nocrypt
openssl rsa -in publicKey.pem -pubout -outform PEM -out publicKey.pem

Add privateKey.pem & publicKey.pem files in main/resources

----------------------------------------------------------------------
#properties
mp.jwt.verify.publickey.location=META-INF/resources/publicKey.pem
mp.jwt.verify.issuer=DonauTech
quarkus.smallrye-jwt.enabled=true

# generate token
JwtClaims jwtClaims = new JwtClaims();
jwtClaims.setIssuer("DonauTech");
jwtClaims.setJwtId("a-123");
jwtClaims.setSubject(email);
jwtClaims.setClaim(Claims.upn.name(), email);
jwtClaims.setClaim(Claims.preferred_username.name(), username);
jwtClaims.setClaim(Claims.birthdate.name(), birthdate);
jwtClaims.setClaim(Claims.groups.name(), Arrays.asList(TokenUtils.ROLE_USER));
jwtClaims.setAudience("using-jwt");
jwtClaims.setExpirationTimeMinutesInTheFuture(1);
String token = TokenUtils.generateTokenString(jwtClaims);

# Project
mvn io.quarkus:quarkus-maven-plugin:1.3.0.Final:create -DprojectGroupId="tech.donau" -DprojectArtifactId=security-jwt -DclassName="tech.donau.ProfileResource" -Dpath="/profile" -Dextensions="resteasy-jsonb, jwt"

Using Security with JDBC Realm

mvn io.quarkus.platform:quarkus-maven-plugin:2.4.1.Final:create \
    -DprojectGroupId=org.acme \
    -DprojectArtifactId=security-jdbc-quickstart \
    -Dextensions="elytron-security-jdbc,jdbc-postgresql,resteasy" \
    -DnoExamples

mvn io.quarkus:quarkus-maven-plugin:1.3.0.Final:create -DprojectGroupId="tech.donau" -DprojectArtifactId=security-jpa -Dextensions="quarkus-elytron-security-jdbc, jdbc-postgresql, resteasy"

BCrypt:
	https://developer.jboss.org/people/aabdelsa/blog/2019/06/11/configuring-a-jdbc-security-realm-with-bcrypt-and-modular-crypt-password-mappers

pass: quickstartPwd1

sudo docker exec -ti 1f bash
 
psql
	CREATE DATABASE quarkus;
	
	\connect quarkus;
	 
	 CREATE TABLE users (
	  id INT,
	  username VARCHAR(255),
	  password VARCHAR(255),
	  role VARCHAR(255)
	);

	INSERT INTO users (id, username, password, role) VALUES (1, 'admin', 'admin', 'admin');
	INSERT INTO users (id, username, password, role) VALUES (2, 'user','user', 'user');
	
.\mvnw quarkus:dev
	 
Use security-jpa as initial setup
docker run --name quarkus-postgres -e POSTGRES_PASSWORD=root -e POSTGRES_USER=root -p 5432:5432 -d postgres

#posgresql
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=root
quarkus.datasource.password=root
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/quarkus

uarkus.security.jdbc.enabled=true
quarkus.security.jdbc.principal-query.sql=SELECT u.password, u.role FROM clients u WHERE u.username=?
quarkus.security.jdbc.principal-query.enabled=true
quarkus.security.jdbc.principal-query.clear-password-mapper.password-index=1
quarkus.security.jdbc.principal-query.attribute-mappings.0.index=2
quarkus.security.jdbc.principal-query.attribute-mappings.0.to=groups	


Using Security with JPA


mvn io.quarkus:quarkus-maven-plugin:1.3.0.Final:create \
-DprojectGroupId=tech.donau \
-DprojectArtifactId=security-jpa \
-Dextensions="security-jpa, jdbc-postgresql, resteasy, hibernate-orm-panache"

docker run --name quarkus-postgres -e POSTGRES_PASSWORD=root -e POSTGRES_USER=root -p 5432:5432 -d postgres

#mysql
quarkus.datasource.db-kind=mysql
quarkus.datasource.username=root
quarkus.datasource.password=root
quarkus.datasource.jdbc.url=jdbc:mysql://localhost:3310/hello
quarkus.hibernate-orm.database.generation=drop-and-create

#posgresql
quarkus.datasource.db-kind=postgresql
quarkus.datasource.username=quarkus
quarkus.datasource.password=quarkus
quarkus.datasource.jdbc.url=jdbc:postgresql:security_jpa
quarkus.hibernate-orm.database.generation=drop-and-create

	CREATE DATABASE test;
	
	\connect test;
	
	CREATE TABLE users_test (
	  id INT,
	  username VARCHAR(255),
	  password VARCHAR(255),
	  role VARCHAR(255)
	);

	INSERT INTO users (id, username, password, role) VALUES (1, 'admin', 'admin', 'admin');
	INSERT INTO users (id, username, password, role) VALUES (2, 'user','user', 'user');
	
	
Security with Properties


mvn io.quarkus:quarkus-maven-plugin:1.2.1.Final:create -DprojectGroupId="tech.donau" -DprojectArtifactId=security-properties -DclassName="tech.donau.UserResource" -Dpath="/user" -Dextensions="elytron-security-properties-file"

quarkus.security.users.file.enabled=true
quarkus.security.users.file.users=users.properties
quarkus.security.users.file.roles=roles.properties
quarkus.security.users.file.realm-name=QuarkusRealm
quarkus.security.users.file.plain-text=true
