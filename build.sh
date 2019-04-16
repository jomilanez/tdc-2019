#!/bin/bash
sudo killall java

mvn clean install -f api-gateway/pom.xml 
java -jar api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar & 

mvn clean install -f user-api/pom.xml 
java -jar user-api/target/user-api-0.0.1-SNAPSHOT.jar & 

mvn clean install -f content-api/pom.xml 
JAva -jar content-api/target/content-api-0.0.1-SNAPSHOT.jar & 
