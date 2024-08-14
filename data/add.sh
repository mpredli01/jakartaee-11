#!/bin/zsh

# Add a brewer to the Brewer collection
curl -X POST -H "Content-Type: application/json" -d '{"_id": 7, "name": "Redlich Brewing Company", "city": "Flemington", "state": "New Jersey" }' http://localhost:8080/data-1.0.0/db/brewer/7

# Add a beer to the Beer collection
curl -X POST -H "Content-Type: application/json" -d '{"_id": 11, "name": "Flemington Pumpkin Ale", "type": "ALE", "brewer_id": 7, "abv": 9.0}' http://localhost:8080/data-1.0.0/db/beer/11
