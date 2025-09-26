#!/bin/zsh

curl -X GET http://localhost:8080/jwtbridge-1.0.0/jwtbridge/secured/hi-with-token -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNaWtlIiwicm9sZXMiOlsiYWRtaW4iXSwiaXNzIjoicGF5YXJhIiwiZXhwIjoxNzU4OTE1MjM1fQ.EPHsVYC6ddONXtqk8ehdwdRGpZXgW0WAy6y32RZU3xk"

