#!/bin/zsh

mvn clean package && java -jar /usr/local/bin/payara-micro/payara-micro-7.2025.2.jar target/security-1.0.0.war
