#!/bin/zsh

java -jar /usr/local/bin/piranha/piranha-dist-webprofile-25.2.0.jar --war-file target/security-1.0.0.war
# java -jar /usr/local/bin/payara-micro/payara-micro-7.2025.1.Alpha1.jar target/security-1.0.0.war
# java -Djakarta.security.jacc.PolicyFactory.provider="org.glassfish.exousia.modules.def.DefaultPolicyFactory" -jar /usr/local/bin/payara-micro/payara-micro-7.2024.1.Alpha2.jar target/security-1.0.0.war
