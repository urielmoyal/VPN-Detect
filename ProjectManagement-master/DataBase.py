import json

with open("Yummly.json", "r") as jsonFile:
    breakfast = (json.load(jsonFile)['breakfast']['matches'])

with open("Yummly.json", "r") as jsonFile:
    dinner = (json.load(jsonFile)['dinner']['matches'])

with open("Yummly.json", "r") as jsonFile:
    lunch = (json.load(jsonFile)['lunch']['matches'])
