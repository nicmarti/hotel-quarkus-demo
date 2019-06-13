# Quarkus Hotel

### Another Quarkus demo: Hibernate ORM with Panache, RESTEasy and Vue.js

Back in 2009, JBoss Seam, Spring JSF and Play Framework 1 had a cool demo based on a simple Hotel & Booking reservation system.
We decided to build a new version on top of [Quarkus](https://quarkus.io/). 
The application has a simple backend, exposing a couple of endpoints over REST. 
The project was bootstraped from [hibernate-orm-panache-resteasy demo].(https://github.com/quarkusio/quarkus-quickstarts/tree/master/hibernate-orm-panache-resteasy)

While the code is surprisingly simple, under the hood this is using:
 - RESTEasy to expose the REST endpoints
 - Hibernate ORM with Panache to perform the CRUD operations on the database
 - A PostgreSQL database; see below to run one via Docker
 - ArC, the CDI inspired dependency injection tool with zero overhead
 - The high performance Agroal connection pool
 - Infinispan based caching
 - All safely coordinated by the Narayana Transaction Manager

# Step by step

## Requirements

To compile and run this demo you will need:
- GraalVM - see [our Building native image guide](https://quarkus.io/guides/building-native-image-guide)
- Apache Maven `3.5.3+`
- npm v6.9.0+ for the frontend Vue.JS source code 
- docker 

If you don't have GraalVM installed, you can download it here:

<https://github.com/oracle/graal/releases>

Installing GraalVM is very similar to installing any other JDK:
just unpack it, add it to your path, and point the `JAVA_HOME`
and `GRAALVM_HOME` environment variables to it.

You should then use this JDK to run the Maven build.

Note : there is a bug with Graal VM 0.16 and Quarkus 

## Database with Docker

First we will need a PostgreSQL database; you can launch one easily if you have Docker installed:

> docker run --ulimit memlock=-1:-1 -it --rm=true --memory-swappiness=0 --name quarkus_test -e POSTGRES_USER=quarkus_test -e POSTGRES_PASSWORD=quarkus_test -e POSTGRES_DB=quarkus_test -p 5432:5432 postgres:10.5

Alternatively you can setup a PostgreSQL instance in any another way.

The connection properties of the Agroal datasource are configured in the standard Quarkus configuration file, which you will find in
`src/main/resources/application.properties`.

## Build and start the frontend

Pre-requisites : npm v6.9.0 

> cd hotel-quarkus

> npm install

You can then start the frontend, this start a node server on port 8081 :

> npm run serve

You can also build and package the frontend, then copy the generated files to the backend folder :

> cd hotel-quarkus

> npm install

> npm run build

> cp -R dist/* ../src/main/resources/META-INF/resources/

## Build the backend

From the top directory hotel-quarkus-demo you can start the `package` target. The docker-maven-plugin starts a Docker 
container with postgreSQL. If a container was previously started, use `docker ps` and `docker stop <container_id>` to stop id.
The docker plugin is used to span a database for unit tests.

> mvn package

### Run Quarkus in developer mode

To run the application in interactive mode (developer mode):

>  mvn compile quarkus:dev

In this mode you can make changes to the code and have the changes immediately applied, by just refreshing your browser.

### Run Quarkus in JVM mode

When you're done playing with "dev-mode" you can run it as a standard Java application.

First compile it:

> mvn package

Then run it:

> java -jar ./target/hibernate-orm-panache-resteasy-1.0-SNAPSHOT-runner.jar


### Run Quarkus as a native application

This same demo can be compiled into native code: no modifications required.

This implies that you no longer need to install a JVM on your production environment, as the runtime technology is included in the produced binary, and optimized to run with minimal resource overhead.

Compilation will take a bit longer, so this step is disabled by default;
let's build again by enabling the `native` profile:

> mvn package -Dnative

After getting a cup of coffee, you'll be able to run this binary directly:

> ./target/hibernate-orm-panache-resteasy-1.0-SNAPSHOT-runner


## See the demo in your browser

Navigate to: [http://localhost:8081](http://localhost:8081) this is the Vue.js frontend application. Please note that node will use port 8080 if the 
java backend is not running.

To see the backend in action : 
[http://localhost:8080/hotels](http://localhost:8080/hotels)

# Photos credits

Photos donwloaded from [https://www.pexels.com/]

End of document