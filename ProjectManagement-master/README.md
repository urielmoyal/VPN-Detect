### Yummly Project

Yumlly is a web which allows the user select recipes according to several criteria<br />
that the user defines in advance such as: milky/meat, vegetarian/vegan, preparation time, etc.<br />
The site allows recipes filtering according to the user criteria.<br />  
Our features are:<br />
* mealTime - The feature will return recipes according to the current system local time such for breakfast (6:00 - 12:00), Lunch (12:01 - 18:00), Dinner (18:01 - 24:00).<br />
In addition, the feature allow the user to enter a time which he is interested. Meaning that if the user is interested in a dinner he will put the evening time (18:01 to 24:00) then he will get dinner recipes.<br />
* buildMenu - The system will randomly build a nutrition menu for the user that contains breakfast, lunch and dinner.<br />
The user will be able to decide whether he is interested with the menu or whether he want to switch to another one.<br />

### Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.<br />
See deployment for notes on how to deploy the project on a live system.

### Prerequisites

* Python interperter
* pip
* IDE - Development envenvironment

### How to install

__1)__ Clone this repo to your computer:  `https://github.com/DavidSaal/ProjectManagement`

__2)__ Open command line.

__3)__ Enter the following commands in the CL:
```
      1.  pip install requests
      2.  pip install pycodestyle
```

__4)__ Open the project in your IDE.

## API's used

The web uses [Recipes Data- API](https://yummly.com) in order to get the recipes.  

The project is synchronized with:
* SemaphoreCi - An API used to implement CI/CD (Continuous Integration & Continuous Deployment).
* Heroku - A virtual production environment.

## Running the tests

In order to run the test, use command line to enter the following command: (open command line in project's directory)
```
python MyTest.py
```
If all tests passed, you should see this output:

![image](https://github.com/DavidSaal/ProjectManagement/blob/master/images/TestsPass.jpg)


## Using the application
Run the program with the command (you can on  CL inside the directed file).
```
python Yummly.py
```
and the following output will be : 

![image](https://github.com/DavidSaal/ProjectManagement/blob/master/images/ShowFeatures.jpg)
