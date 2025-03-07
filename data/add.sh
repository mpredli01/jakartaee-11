#!/bin/zsh

# Add a brewer to the Brewer collection
curl -X POST -H "Content-Type: application/json" -d '{"_id": 13, "name": "Redlich Brewing Company", "city": "Atlanta", "state": "Georgia" }' http://localhost:8080/data-1.0.0/data/brewer/13

# Add a beer to the Beer collection
curl -X POST -H "Content-Type: application/json" -d '{"_id": 15, "name": "Devnexus Mardi Gras Stout", "type": "STOUT", "brewer_id": 8, "abv": 9.0}' http://localhost:8080/data-1.0.0/data/beer/15
