import requests
import datetime
import random


class Yummly(object):

    def mealTime(self, time=0, deafult=None):
        setmeal = ''
        if (time < 6 or time > 24) and time != 0:
            print('no Meal for this Time')
            return 'no Meal for this Time'
        if time == 0:
            time = datetime.datetime.now().time().hour
        if 6 <= time <= 12:
            setmeal = 'breakfast'
        if 12 < time <= 18:
            setmeal = 'lunch'
        if 18 < time <= 24:
            setmeal = 'dinner'

        js = requests.get(
            url='http://api.yummly.com/v1/api/recipes?_app_id=a39b4356&_app_key=fb5e30845a6f6634f0bf404f6d99f8ab&q=' + setmeal).json()
        matches = js['matches']
        j = 1
        recipe = []
        for i in matches:
            recipe.append(i['recipeName'])
            print(str((j)) + '.' + i['recipeName'])
            j = j + 1

        print('\nChoose Recpie: ')
        if deafult is None:
            choose = int(input())
        else:
            choose = deafult

        for i in matches:
            if i['recipeName'] == recipe[choose - 1]:
                print('Ingredients: ' + str(i['ingredients']) + ', ' + 'Total Time: ' + str(
                    i['totalTimeInSeconds']) + ' sec')
                return i['ingredients']

    def buildMenu(self, deafult=None):
        br = js = requests.get(
            url='http://api.yummly.com/v1/api/recipes?_app_id=a39b4356&_app_key=fb5e30845a6f6634f0bf404f6d99f8ab&q=breakfast').json()[
            'matches']
        lu = js = requests.get(
            url='http://api.yummly.com/v1/api/recipes?_app_id=a39b4356&_app_key=fb5e30845a6f6634f0bf404f6d99f8ab&q=lunch').json()[
            'matches']
        di = js = requests.get(
            url='http://api.yummly.com/v1/api/recipes?_app_id=a39b4356&_app_key=fb5e30845a6f6634f0bf404f6d99f8ab&q=dinner').json()[
            'matches']
        breakfast = []
        lunch = []
        dinner = []
        for i in br:
            breakfast.append(i['recipeName'])
        for i in lu:
            lunch.append(i['recipeName'])
        for i in di:
            dinner.append(i['recipeName'])
        ok = 'no'
        while ok == 'no':
            randbreak = random.choice(breakfast)
            randlunch = random.choice(lunch)
            randdinner = random.choice(dinner)
            print('Your Meun - [Breakfast:: ' + randbreak + ', Lunch:: ' + randlunch + ', Dinner:: ' + randdinner + ']')
            print("\nDid you want this meun? (yes/no)")

            if deafult is None:
                ok = input()
            else:
                ok = 'yes'

        return [randbreak, randlunch, randdinner]


api = Yummly()
print("--------Feature Number 1:--------")
api.mealTime(0, 1)
print("\n--------Feature Number 2:--------")
api.buildMenu(1)
