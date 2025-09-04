#!/bin/zsh

# Add a brewer to the Brewer collection
curl -X POST -H "Content-Type: application/json" -d '{"_id": 12, "name": "Redlich Brewing Company", "city": "Marco Island", "state": "Florida" }' http://localhost:8080/data-1.0.0/data/brewer/12

# Add a beer to the Beer collection
curl -X POST -H "Content-Type: application/json" -d '{"_id": 8, "name": "Marco Island Stout", "type": "STOUT", "brewer_id": 8, "abv": 9.0}' http://localhost:8080/data-1.0.0/data/beer/8
