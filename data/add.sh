#!/bin/zsh

# Add a brewer to the Brewer collection
curl -X POST -H "Content-Type: application/json" -d '{"_id": 17, "name": "Redlich Brewing Company", "city": "Old Alexandria", "state": "Virginia" }' http://localhost:8080/data-1.0.0/data/brewer/17

# Add a beer to the Beer collection
curl -X POST -H "Content-Type: application/json" -d '{"_id": 19, "name": "King Street Ale", "type": "ALE", "brewer_id": 14, "abv": 10.0}' http://localhost:8080/data-1.0.0/data/beer/19
