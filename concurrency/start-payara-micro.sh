#!/bin/zsh

java -jar /usr/local/bin/payara-micro/payara-micro-6.2024.12.jar target/concurrency-1.0.0.war
# java -jar /usr/local/bin/payara-micro/payara-micro-7.2024.1.Alpha3.jar target/concurrency-1.0.0.war
# java -jar /usr/local/bin/payara-micro/payara-micro-7.2024.1.Alpha2.jar -D'jakarta.security.jacc.PolicyFactory.provider'=org.glassfish.exousia.modules.def.DefaultPolicyFactory --deploy target/concurrency-1.0.0.war
