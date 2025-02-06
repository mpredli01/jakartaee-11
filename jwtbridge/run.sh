#!/bin/zsh

curl -X GET http://localhost:8080/jwtbridge-1.0.0/api/secured/hi-with-token -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJNaWtlIiwicm9sZXMiOlsiYWRtaW4iXSwiaXNzIjoicGF5YXJhIiwiZXhwIjoxNzM4OTQ2Mzc3fQ.1Rc_b4TaHe_Uep6soupUMnH_2EkHm4-04QbBKqB6ePY"

