Using Apache Kafka

mvn io.quarkus:quarkus-maven-plugin:1.3.0.Final:create -DprojectGroupId="tech.donau" -DprojectArtifactId=kafka -Dextensions="kafka"

sudo curl -L "https://github.com/docker/compose/releases/download/1.29.2/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
 
docker-compose up

quarkus.http.port=9090

.\mvnw quarkus:dev -Ddebug=600

<dependency>
    <groupId>io.quarkus</groupId>
    <artifactId>quarkus-resteasy-jsonb</artifactId>
</dependency>


Using AMQP with Reactive Messaging


mvn io.quarkus:quarkus-maven-plugin:1.2.1.Final:create -DprojectGroupId="tech.donau" -DprojectArtifactId=amqp -Dextensions="amqp"

docker run -it --rm -p 8161:8161 -p 61616:61616 -p 5672:5672 -e ARTEMIS_USERNAME=root -e ARTEMIS_PASSWORD=root vromero/activemq-artemis


	<dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-resteasy-jackson</artifactId>
    </dependency>
	
http://philcalcado.com/
Born in the abyss

Asynchronous Message Passing

mvn io.quarkus:quarkus-maven-plugin:1.3.0.Final:create -DprojectGroupId="tech.donau" -DprojectArtifactId=async-messaging -DclassName="tech.donau.greeting.GreetingResource" -Dpath="/greeting" -Dextensions="vertx"

	dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-resteasy-mutiny</artifactId>
    </dependency>
	
JMS on Quarkus


mvn io.quarkus:quarkus-maven-plugin:1.3.0.Final:create -DprojectGroupId="tech.donau" -DprojectArtifactId=jms -Dextensions="resteasy,qpid-jms"

mvn io.quarkus.platform:quarkus-maven-plugin:2.4.1.Final:create -DprojectGroupId="org.acme" -DprojectArtifactId=jms-quickstart -DclassName="org.acme.jms.PriceResource" -Dpath="/prices" -Dextensions="resteasy,qpid-jms"

quarkus.qpid-jms.url=amqp://localhost:5672
quarkus.qpid-jms.username=root
quarkus.qpid-jms.password=root

docker run -it --rm -p 8161:8161 -p 61616:61616 -p 5672:5672 -e ARTEMIS_USERNAME=root -e ARTEMIS_PASSWORD=root vromero/activemq-artemis:2.11.0-alpine