mvn io.quarkus:quarkus-maven-plugin:1.2.1.Final:create -DprojectGroupId="tech.donau" -DprojectArtifactId=datasource-setup -DclassName="tech.donau.datasource.GreetingResource" -Dpath="/hello"

.\mvnw quarkus:add-extension -Dextensions="agroal"
.\mvnw quarkus:add-extension -Dextensions="jdbc-h2"
.\mvnw quarkus:add-extension -Dextensions="jdbc-mysql"

.\mvnw quarkus:dev


quarkus.http.port=9090


docker run --name quarkus-db -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql
sudo docker inspect quarkus-db
docker exec -ti 1f bash

mysql  -uroot -proot
create database hello;
use hello;
create table greeting(id int, greeting text); 

insert into greeting values (1, "hello");

mvn io.quarkus:quarkus-maven-plugin:1.2.1.Final:create -DprojectGroupId="tech.donau" -DprojectArtifactId=hibernate -DclassName="tech.donau.BookResource" -Dpath="/weather"

.\mvnw quarkus:add-extension -Dextensions="resteasy-jsonb,hibernate-orm,jdbc-mysql"

mvn io.quarkus:quarkus-maven-plugin:1.2.1.Final:create -DprojectGroupId="tech.donau" -DprojectArtifactId=cache -DclassName="tech.donau.WeatherResource" -Dpath="/weather"


docker run -it --rm=true --name elasticsearch_quarkus_test -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:7.5.0

Using Infinispan Client


mvn io.quarkus:quarkus-maven-plugin:1.2.1.Final:create -DprojectGroupId="tech.donau" -DprojectArtifactId=infinispan -DclassName="tech.donau.BookResource" -Dpath="/books" -Dextensions="infinispan-client,resteasy-jsonb"

http://localhost:11222/console/welcome

https://infinispan.org/get-started/
Create cache (LOCAL):

{
  "local-cache": {
    "name": "mycache",
    "statistics": "true",
    "encoding": {
      "media-type": "application/x-protostream"
    }
  }
}


Transactional

	<dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-narayana-jta</artifactId>
    </dependency>
	
How to use Cache in your application

Add extensions: .\mvnw quarkus:add-extension -Dextensions="cache, resteasy-jsonb"


Schema Migration with Flyway
 
mvn io.quarkus:quarkus-maven-plugin:1.2.1.Final:create -DprojectGroupId="tech.donau" -DprojectArtifactId=migration-flyway -DclassName="tech.donau.GreetingResource" -Dpath="/weather" -Dextensions="flyway,jdbc-mysql"

docker exec -ti 1f43909999ec bash
mysql  -uroot -proot
create database migration_test;

quarkus.datasource.driver=com.mysql.cj.jdbc.Driver
quarkus.datasource.url=jdbc:mysql://localhost:3306/migration_test
quarkus.datasource.username=root
quarkus.datasource.password=root
quarkus.datasource.min-size=3
quarkus.datasource.max-size=13

quarkus.flyway.migrate-at-start=true

quarkus.http.port=9090

create table book(id int, name text);
insert into book values(1, 'test1');
insert into book values(2, 'test2');


\mvnw quarkus:dev -Ddebug=6006


Schema Migration with Liquibase


mvn io.quarkus:quarkus-maven-plugin:1.2.1.Final:create -DprojectGroupId="tech.donau" -DprojectArtifactId=migration-liquibase -DclassName="tech.donau.GreetingResource" -Dpath="/hello" -Dextensions="jdbc-mysql"


#Add next library to pom.xml
<dependency>
<groupId>com.konnectkode</groupId>
<artifactId>quarkus-liquibase</artifactId>
<version>1.0.0.CR2</version>
</dependency>


next application.properties were used in lectures
quarkus.datasource.driver=com.mysql.cj.jdbc.Driver
quarkus.datasource.url=jdbc:mysql://localhost:3310/hello
quarkus.datasource.username=root
quarkus.datasource.password=root
quarkus.datasource.min-size=3
quarkus.datasource.max-size=13
quarkus.hibernate-orm.database.generation=drop-and-create
quarkus.hibernate-orm.dialect=org.hibernate.dialect.MySQL8Dialect


