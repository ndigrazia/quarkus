Generating Kubernetes Resources

mvn io.quarkus.platform:quarkus-maven-plugin:2.4.2.Final:create -DprojectGroupId="tech.donau" -DprojectArtifactId=containers   -DclassName="tech.donau.WeatherResource" -Dpath="/weather" -Dextensions="container-image-jib"

./mvnw quarkus:add-extension -Dextensions="container-image-jib"

docker run -d -p 5000:5000 --restart always --name registry registry:2

Generate package & image Docker

native:
./mvnw clean package -Pnative -Dquarkus.native.container-build=true

jvm:
./mvnw clean package -Dquarkus.container-image.build=true

If you ever want to build a native container image and already have an existing native image you can set -Dquarkus.native.reuse-existing=true and the native image build will not be re-run.

#application.properties
#Set registry
quarkus.container-image.registry=localhost:5000
quarkus.container-image.insecure=true


#Push image to registry
./mvnw clean package -Dquarkus.container-image.push=true

	If no registry is set (using quarkus.container-image.registry) then docker.io will be used as the default.


#Quarkus provides extensions for building (and pushing) container images. Currently it supports:

Jib

	<dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-container-image-jib</artifactId>
    </dependency>

Docker

	<dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-container-image-jib</artifactId>
    </dependency>

S2I

The extension quarkus-container-image-s2i is using S2I binary builds in order to perform container builds inside the OpenShift cluster. The idea behind the binary build is that you just upload the artifact and its dependencies to the cluster and during the build they will be merged to a builder image (defaults to fabric8/s2i-java).

The benefit of this approach, is that it can be combined with OpenShift’s DeploymentConfig that makes it easy to roll out changes to the cluster.

To use this feature, add the following extension to your project.
	./mvnw quarkus:add-extension -Dextensions="container-image-s2i"

S2I builds require creating a BuildConfig and two ImageStream resources, one for the builder image and one for the output image. The creation of such objects is being taken care of by the Quarkus Kubernetes extension.

GO localhost:5000/v2/_catalog

https://quarkus.io/guides/container-image