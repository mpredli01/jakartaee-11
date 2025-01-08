#!/bin/zsh

curl -X GET http://localhost:8080/jwtbridge-1.0.0/api/secured/hi-with-token -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNaWtlIiwicm9sZXMiOlsiYWRtaW4iXSwiaXNzIjoicGF5YXJhIiwiZXhwIjoxNzM2NDYyNjA5fQ.M_1iAinHHTo19ruLpX0y9vf7B1HqlxsFr3KRjihALV4"

