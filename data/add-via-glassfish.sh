#!/bin/zsh

# Add a brewer to the Brewer collection
curl -X POST -H "Content-Type: application/json" -d '{"_id": 18, "name": "Redlich Brewing Company", "city": "London", "state": "England" }' http://localhost:8081/data/brewer/18

# Add a beer to the Beer collection
curl -X POST -H "Content-Type: application/json" -d '{"_id": 20, "name": "London Eye IPA", "type": "IPA", "brewer_id": 14, "abv": 10.0}' http://localhost:8081/data/beer/20
