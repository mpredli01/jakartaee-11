#!/bin/zsh

curl -X GET http://localhost:8080/jwtbridge-1.0.0/api/secured/hi-with-token -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNaWtlIiwicm9sZXMiOlsiYWRtaW4iXSwiaXNzIjoicGF5YXJhIiwiZXhwIjoxNzM2ODkwMTAwfQ.5J_O40dmtPUCAH5MWgwXuiCV7HeTA7UfAFtsuS9dGDU"

