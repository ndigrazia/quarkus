Health Check

mvn io.quarkus:quarkus-maven-plugin:1.3.0.Final:create -DprojectGroupId="tech.donau" -DprojectArtifactId=microprofile-health -Dextensions="health"


Using OpenTracing


http://localhost:16686/search (jager home site)

docker run -p 5775:5775/udp -p 6831:6831/udp -p 6832:6832/udp -p 5778:5778 -p 16686:16686 -p 14268:14268 jaegertracing/all-in-one:latest

mvn io.quarkus:quarkus-maven-plugin:1.3.0.Final:create -DprojectGroupId="tech.donau" -DprojectArtifactId=opentracing -DclassName="tech.donau.opentracing.TracedResource" -Dpath="/hello" -Dextensions="quarkus-smallrye-opentracing"

mvn io.quarkus.platform:quarkus-maven-plugin:2.4.1.Final:create -DprojectGroupId="org.acme" -DprojectArtifactId=opentracing-quickstart     -DclassName="org.acme.opentracing.TracedResource" -Dpath="/hello" -Dextensions="resteasy,quarkus-smallrye-opentracing"

quarkus.jaeger.service-name=myservice
quarkus.jaeger.sampler-type=const
quarkus.jaeger.sampler-param=1
quarkus.log.console.format=%d{HH:mm:ss} %-5p traceId=%X{traceId}, spanId=%X{spanId}, sampled=%X{sampled} [%c{2.}] (%t) %s%e%n

<dependency>
    <groupId>io.opentracing.contrib</groupId>
    <artifactId>opentracing-jdbc</artifactId>
</dependency>


Additional instrumentation
The OpenTracing API Contributions project offers additional instrumentation that can be used to add tracing to a large variety of technologies/components.

The instrumentation documented in this section has been tested with Quarkus and works in both standard and native mode.

JDBC
The JDBC instrumentation will add a span for each JDBC queries done by your application, to enable it, add the following dependency to your pom.xml:

<dependency>
    <groupId>io.opentracing.contrib</groupId>
    <artifactId>opentracing-jdbc</artifactId>
</dependency>
As it uses a dedicated JDBC driver, you must configure your datasource and Hibernate to use it.

quarkus.datasource.db-kind=postgresql
# add ':tracing' to your database URL
quarkus.datasource.jdbc.url=jdbc:tracing:postgresql://localhost:5432/mydatabase
# use the 'TracingDriver' instead of the one for your database
quarkus.datasource.jdbc.driver=io.opentracing.contrib.jdbc.TracingDriver
# configure Hibernate dialect
quarkus.hibernate-orm.dialect=org.hibernate.dialect.PostgreSQLDialect

Kafka
The Kafka instrumentation will add a span for each message sent to or received from a Kafka topic. To enable it, add the following dependency to your pom.xml:

<dependency>
    <groupId>io.opentracing.contrib</groupId>
    <artifactId>opentracing-kafka-client</artifactId>
</dependency>
It contains OpenTracing interceptors that must be registered on Kafka producers and consumers.

If you followed the Kafka guide, the interceptors can be added on the generated-price and the prices channels as follows:

# Configure the Kafka sink (we write to it)
mp.messaging.outgoing.generated-price.connector=smallrye-kafka
mp.messaging.outgoing.generated-price.topic=prices
mp.messaging.outgoing.generated-price.value.serializer=org.apache.kafka.common.serialization.IntegerSerializer
mp.messaging.outgoing.generated-price.interceptor.classes=io.opentracing.contrib.kafka.TracingProducerInterceptor

# Configure the Kafka source (we read from it)
mp.messaging.incoming.prices.connector=smallrye-kafka
mp.messaging.incoming.prices.value.deserializer=org.apache.kafka.common.serialization.IntegerDeserializer
mp.messaging.incoming.prices.interceptor.classes=io.opentracing.contrib.kafka.TracingConsumerInterceptor
interceptor.classes accept a list of classes separated by a comma.

MongoDB client
The Mongo Driver instrumentation will add a span for each command executed by your application. To enable it, add the following dependency to your pom.xml:

<dependency>
    <groupId>io.opentracing.contrib</groupId>
    <artifactId>opentracing-mongo-common</artifactId>
</dependency>
It contains the OpenTracing CommandListener that will be registered on the configuration of the mongo client. Following the MongoDB guide, the command listener will be registered defining the config property as follows:

# Enable tracing commands in mongodb client
quarkus.mongodb.tracing.enabled=true


Error tracking with Sentry


https://sentry.io/welcome/

From error tracking to performance monitoring, developers can see what actually matters, solve quicker, and learn continuously about their applications - from the frontend to the backend.

mvn io.quarkus:quarkus-maven-plugin:1.3.0.Final:create -DprojectGroupId="tech.donau" -DprojectArtifactId=sentry -Dextensions="logging-sentry" 

io.quarkus
quarkus-logging-sentry

# Properties:
quarkus.log.sentry.in-app-packages=tech.donau,other.package
quarkus.log.sentry.in-app-packages=*

quarkus.log.sentry=true
quarkus.log.sentry.dsn=https://60e03514e03b44e68bf777e32801bd17@o1066221.ingest.sentry.io/6058779
quarkus.log.sentry.in-app-packages=*


Collecting Metrics

mvn io.quarkus:quarkus-maven-plugin:1.3.0.Final:create -DprojectGroupId="tech.donau" -DprojectArtifactId=metrics -Dextensions="metrics"

