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


./mvnw clean package -Dquarkus.container-image.build=true -Dquarkus.container-image.push=true

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

SITE: 

https://quarkus.io/guides/container-image

https://quarkus.io/guides/building-native-image




Deploying Native Applications on Kubernetes

#Install Minikube y kubectl (using Docker)
# Minikube
New-Item -Path 'd:\' -Name 'minikube' -ItemType Directory -Force

Invoke-WebRequest -OutFile 'd:\minikube\minikube.exe' -Uri 'https://github.com/kubernetes/minikube/releases/latest/download/minikube-windows-amd64.exe' -UseBasicParsing

$oldPath = [Environment]::GetEnvironmentVariable('Path', [EnvironmentVariableTarget]::Machine)
if ($oldPath.Split(';') -inotcontains 'd:\minikube'){ `
  [Environment]::SetEnvironmentVariable('Path', $('{0};d:\minikube' -f $oldPath), [EnvironmentVariableTarget]::Machine) `
}

minikube dashboard --url

#Intall kubectl

curl -LO "https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl"

chmod +x ./kubectl

sudo mv ./kubectl /usr/local/bin/kubectl

kubectl version --client

# To use with minikube:
eval $(minikube docker-env)

#Project

mvn io.quarkus:quarkus-maven-plugin:1.3.1.Final:create -DprojectGroupId="tech.donau" -DprojectArtifactId=quarkus-kubernetes -DclassName="tech.donau.WeatherResource" -Dpath="/weather" -Dextensions="kubernetes, docker"

#Check pom.xml with:

-Build image with docker:

 <dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-kubernetes</artifactId>
    </dependency>
	
    <dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-container-image-docker</artifactId>
    </dependency>

-or Build image with jib:

	<dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-kubernetes</artifactId>
    </dependency>
	
    <dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-container-image-jib</artifactId>
    </dependency>

# application.properties

#optional, default to the system user name
quarkus.container-image.group=quarkus
#optional, defaults to the application name
quarkus.container-image.name=demo-app
#optional, defaults to the application version
quarkus.container-image.tag=1.0

quarkus.kubernetes.image-pull-policy=ifNotPresent
quarkus.kubernetes.service-type=LoadBalancer

#Generate image

./mvnw clean package
	
#Deploy on kubernetes 		with:

./mvnw clean package -Dquarkus.kubernetes.deploy=true


kubectl expose deployment kubernetes --type=LoadBalancer --name=kubernetes-service

#See resources
 kubectl get pod -a
 kubectl get svc
 
#Get service url 

 minikube service <service_name> --url
 
 minikube service demo-app --url

#Test app:
 curl -s  http://192.168.49.2:32136/weather
  
  
Deploying Quarkus on Google Cloud Platform GKE


application.properties

quarkus.kubernetes.replicas=3
quarkus.kubernetes.env-vars.test.value=test
quarkus.kubernetes.env-vars.key.value=value

quarkus.kubernetes.image-pull-policy=ifNotPresent
quarkus.kubernetes.service-type=LoadBalancer

#Google Cloud Plataform
quarkus.container-image.registry=eu.gcr.io
quarkus.container-image.group=driven-atrium-259009


./mvnw clean package -Dquarkus.container-image.build=true -Dquarkus.container-image.push=true
./mvnw clean package -Dquarkus.kubernetes.deploy=true


Deploying Native Applications on Knative Kubernetes
 
#Kbernetes must support knative
NOTE: On Kbenetes Cluster's GCP enables "Run for anthos" on Severless. This option is Clubernetes Cluster-> feature
 
 mvn io.quarkus:quarkus-maven-plugin:1.3.1.Final:create -DprojectGroupId="tech.donau" -DprojectArtifactId=quarkus-knative -DclassName="tech.donau.WeatherResource" -Dpath="/weather" -Dextensions="kubernetes, docker"
 
#Connect GCP Kubernetes Cluster using gcloud command.

#application.properties
quarkus.kubernetes.deployment-target=knative
quarkus.knative.image-pull-policy=ifNotPresent
quarkus.container-image.registry=eu.gcr.io
quarkus.container-image.group=driven-atrium-259009
  
kubectl apply -f  ./target/kubernetes/knative.yml

./mvnw clean package -Dquarkus.kubernetes.deploy=true

#If exist an error try out: 
	On ./target/kubernetes/knative.yml
	In Kind: Service change spec.template.spec.containers.ports.name="http"  by   spec.template.spec.containers.ports.name="http1" 
	kubectl apply -f  ./target/kubernetes/knative.yml
 
#Get Url knative service
kubectl get ksvc //Use URL Field. For example: http://quarkus-native.dafult.example.com

#Invoke to service

####

GET http://35.228.39.197:80/weather
host: quarkus-native.dafult.example.com



Using the Kubernetes Client to Interact with a Kubernetes Cluster


 mvn io.quarkus:quarkus-maven-plugin:1.3.1.Final:create -DprojectGroupId="tech.donau" -DprojectArtifactId=quarkus-knative -DclassName="tech.donau.WeatherResource" -Dpath="/weather" -Dextensions="kubernetes, docker"
 
	<dependency>
      <groupId>io.quarkus</groupId>
      <artifactId>quarkus-kubernetes-client</artifactId>
    </dependency> 

quarkus.kubernetes.deployment-target=kubernetes
quarkus.knative.image-pull-policy=ifNotPresent
quarkus.container-image.registry=eu.gcr.io
quarkus.container-image.group=driven-atrium-259009
quarkus.kubernetes-client.trust=false
quarkus.kubernetes-client.namespace=default

 Host to deplay (kubernetes.default.svc)

#Build & deploy
./mvnw clean package -Dquarkus.kubernetes.deploy=true



Azure Functions (Serverless) with Vert.x Web, Servlet, or Resteasy

#Create a project
	mvn archetype:generate -DarchetypeGroupId="io.quarkus" -DarchetypeArtifactId="quarkus-azure-functions-http-archetype" -DarchetypeVersion="1.3.1.Final"

# Instal azure-client
	brew install azure-cli //Check linux install

# Log Azure
	az login
	
# Deploy
	
./mvnw clean install azure-functions:deploy

# Test
GET https://azure-function-20211118142504492.azurewebsites.net/aip/hello

GET https://azure-function-20211118142504492.azurewebsites.net/api/vertx/hello

GET https://azure-function-20211118142504492.azurewebsites.net/api/servlet/hello

#NOTE: Choose one option either REST or Servlet or vertx




AWS Lambda


mvn archetype:generate -DarchetypeGroupId="io.quarkus" -DarchetypeArtifactId="quarkus-amazon-lambda-http-archetype" -DarchetypeVersion="1.3.1.Final"

#Create a S3 bucket
	Foe example, quarkus-bucket-lambda

#Clean & install
	mvn clean install

#Install AWS SAM CLI

# Local
	sam local start-api --template sam.jvm.yaml
	#Test
	GET http://localhost:3000/hello


-Pnative


# AWS
	1. sam package --template-file sam.jvm.yaml --output-template-file packaged.yaml --s3-bucket <YOUR_S3_BUCKET>
		sam package --template-file sam.jvm.yaml --output-template-file packaged.yaml --s3-bucket quarkus-bucket-lambda

	2. sam deploy --template-file packaged.yaml --capabilities CAPABILITY_IAM --stack-name <YOUR_STACK_NAME>
		sam deploy --template-file packaged.yaml --capabilities CAPABILITY_IAM --stack-name quarkusawslambda

 
 
 
 


