#!/bin/zsh

# Add a brewer to the Brewer collection
curl -X POST -H "Content-Type: application/json" -d '{"_id": 6, "name": "SweetWater Brewing Company", "city": "Atlanta", "state": "Georgia" }' http://10.66.1.212:8080/data-1.0.0/db/brewer/6

# Add a beer to the Beer collection
curl -X POST -H "Content-Type: application/json" -d '{"_id": 9, "name": "420 Extra Pale Ale", "type": "IPA", "brewer_id": 6, "abv": 5.7}' http://10.66.1.212:8080/data-1.0.0/db/beer/9
