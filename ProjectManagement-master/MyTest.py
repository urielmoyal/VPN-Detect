import Yummly as api
import DataBase as db
from unittest.mock import MagicMock, patch, Mock
import unittest


def get_input(text):
    return input(text)


class MyTest(unittest.TestCase):
    obj = api.Yummly()
    obj.method = MagicMock(side_effect=obj.mealTime)

    def mockConnectionBreakfast(self, Mock):
        Mock.return_value = db.breakfast
        return Mock()

    def mockConnectionLunch(self, Mock):
        Mock.return_value = db.lunch
        return Mock()

    def mockConnectionDinner(self, Mock):
        Mock.return_value = db.dinner
        return Mock()

    @patch('DataBase.breakfast')
    def test_BreakfastRecipeNameNotNone(self, input):
        Data = self.mockConnectionBreakfast(Mock())
        for i in Data:
            self.assertIsNotNone(i['recipeName'])

    @patch('DataBase.lunch')
    def test_LunchRecipeNameNotNone(self, input):
        Data = self.mockConnectionLunch(Mock())
        for i in Data:
            self.assertIsNotNone(i['recipeName'])

    @patch('DataBase.dinner')
    def test_DinnerRecipeNameNotNone(self, input):
        Data = self.mockConnectionDinner(Mock())
        for i in Data:
            self.assertIsNotNone(i['recipeName'])

    @patch('DataBase.breakfast')
    def test_BreakfastIngredientsNotNone(self, input):
        Data = self.mockConnectionBreakfast(Mock())
        for i in Data:
            self.assertIsNotNone(i['ingredients'])

    @patch('DataBase.lunch')
    def test_LunchIngredientsNotNone(self, input):
        Data = self.mockConnectionLunch(Mock())
        for i in Data:
            self.assertIsNotNone(i['ingredients'])

    @patch('DataBase.dinner')
    def test_DinnerIngredientsNotNone(self, input):
        Data = self.mockConnectionDinner(Mock())
        for i in Data:
            self.assertIsNotNone(i['ingredients'])

    @patch('MyTest.get_input', return_value=1)
    def test_NotReturnNoneMealTime(self, input):
        self.assertIsNotNone(self.obj.mealTime(0, 1))

    @patch('MyTest.get_input', return_value=1)
    def test_NotReturnEmptyMealTime(self, input):
        listOfIngredients = self.obj.mealTime(0, 1)
        for i in range(len(listOfIngredients)):
            self.assertIsNotNone(listOfIngredients[i])

    def test_NotReturnMealForThisTime(self):
        self.assertEqual(self.obj.method(5), 'no Meal for this Time')
        self.assertEqual(self.obj.method(25), 'no Meal for this Time')

    def test_NotReturnEmptyBuildMenu(self):
        listOfMeal = self.obj.buildMenu(1)
        for i in range(len(listOfMeal)):
            self.assertIsNotNone(listOfMeal[i])

    @patch('MyTest.get_input', return_value='yes')
    def test_NotReturnNoneBuildMenu(self, input):
        self.assertIsNotNone(self.obj.buildMenu(1))
