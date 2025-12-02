#!/bin/zsh

mvn clean package && java -jar /usr/local/bin/payara-micro/payara-micro-7.2025.1.jar target/data-1.0.0.war
