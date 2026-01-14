#!/bin/zsh

mvn clean package && java -jar /usr/local/bin/glassfish-embedded/glassfish-embedded-all-8.0.0-SNAPSHOT.jar
