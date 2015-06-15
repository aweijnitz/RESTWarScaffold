JEE7WAR
=======

Empty JEE 7 Web Profile project based on Adam Bien's Essentials Archetype with added WildFly8 deployment scripts

## Requirements

- Java SDK installed (min Java 1.7)
- Maven 3
- [Wildfly 8](http://wildfly.org/) installed
- $WILDFLY_HOME/bin in path (needed to run the deploy, status and undeploy scripts, but not for building)


## Install and first deploy

- Clone this repo
- ```chmod +x *sh```
- Start Wildfly (in wildfly home dir, ```./bin/standalone.sh```)
- Done! You are ready to deploy (see below)


## Using

_Building_

```mvn clean install```

_Build, deploy and show status_

```mvn clean install && ./deploy.sh && ./status.sh```

Point browser to http://localhost:8080/MyWebApp
or do

```curl http://localhost:8080/MyWebApp/ ```

_Undeploy project from Wildfly_

```./undeploy.sh ```



