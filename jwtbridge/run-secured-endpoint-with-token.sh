#!/bin/zsh

curl -X GET http://localhost:8080/jwtbridge-1.0.0/api/secured/hi-with-token -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaWtlIiwicm9sZXMiOlsiYWRtaW4iXSwiaXNzIjoicGF5YXJhIiwiZXhwIjoxNzM2NDU0MjA0fQ.Zi_v8iuFnYzj1O7N7TWtH2DQLHdw8aTsZu-N4yfm--M"
