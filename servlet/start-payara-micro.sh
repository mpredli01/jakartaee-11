#!/bin/zsh

java -jar /usr/local/bin/payara-micro/payara-micro.jar target/servlet-1.0.0.war
# java -Djakarta.security.jacc.PolicyFactory.provider="org.glassfish.exousia.modules.def.DefaultPolicyFactory" -jar /usr/local/bin/payara-micro/payara-micro-7.2024.1.Alpha2.jar target/servlet-1.0.0.war
