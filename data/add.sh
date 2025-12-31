#!/bin/zsh

# Add a brewer to the Brewer collection
curl -X POST -H "Content-Type: application/json" -d '{"_id": 16, "name": "Redlich Brewing Company", "city": "Edgecomb", "state": "Maine" }' http://localhost:8080/data-1.0.0/data/brewer/16

# Add a beer to the Beer collection
curl -X POST -H "Content-Type: application/json" -d '{"_id": 18, "name": "Booth Bay Stout", "type": "STOUT", "brewer_id": 14, "abv": 10.0}' http://localhost:8080/data-1.0.0/data/beer/18
