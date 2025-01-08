#!/bin/zsh

curl -H 'Content-Type: application/json' -d '{ "username":"Mike", "password":"password" }' -X POST http://localhost:8080/jwtbridge-1.0.0/api/token/generate
