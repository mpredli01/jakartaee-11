#!/bin/zsh

# Add a brewer to the Brewer collection
curl -X POST -H "Content-Type: application/json" -d '{"_id": 8, "name": "Redlich Brewing Company", "city": "New Orleans", "state": "Louisiana" }' http://localhost:8080/data-1.0.0/db/brewer/8

# Add a beer to the Beer collection
curl -X POST -H "Content-Type: application/json" -d '{"_id": 12, "name": "New Orleans Mardi Gras Stout", "type": "STOUT", "brewer_id": 8, "abv": 9.0}' http://localhost:8080/data-1.0.0/db/beer/12
