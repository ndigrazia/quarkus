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