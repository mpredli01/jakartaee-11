#!/bin/zsh

curl -X GET http://localhost:8080/jwtbridge-1.0.0/api/secured/hi-with-token -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNaWtlIiwicm9sZXMiOlsiYWRtaW4iXSwiaXNzIjoicGF5YXJhIiwiZXhwIjoxNzQwNTIyMjkxfQ.tBudGQ1YjaIYvQK4lj7hB6bA_LUg1dpyuISa_oSTDAA"

