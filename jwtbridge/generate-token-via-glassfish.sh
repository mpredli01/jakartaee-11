#!/bin/zsh

curl -H 'Content-Type: application/json' -d '{ "username":"Mike", "password":"password" }' -X POST http://localhost:8081/jwtbridge/token/generate
