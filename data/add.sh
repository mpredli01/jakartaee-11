#!/bin/zsh

# Add a brewer to the Brewer collection
curl -X POST -H "Content-Type: application/json" -d '{"_id": 14, "name": "Redlich Brewing Company", "city": "Dublin", "state": "Ireland" }' http://localhost:8080/data-1.0.0/data/brewer/14

# Add a beer to the Beer collection
curl -X POST -H "Content-Type: application/json" -d '{"_id": 16, "name": "Dubliner Stout", "type": "STOUT", "brewer_id": 14, "abv": 10.0}' http://localhost:8080/data-1.0.0/data/beer/16
