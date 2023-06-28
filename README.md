# DungeonsnDragons_ProjectOOP
Github project link: https://github.com/adam22993/DungeonsnDragons_ProjectOOP

A 2D dnd style game to test and implement OOP principles.

The project is attempting to create a 2D game that is in the light of Dungeons and Dragons taking the form
of a roguelike game.
The game will be a turn based game where the player will be able to move around a map and interact with objects and enemies.
The game will be created using the pure java to create the logic and the JFrame library for the view and the controls.
In the project, we will attempt to implement the following OOP principles:

- **Inheritance**
- **Polymorphism**
- **Abstraction**
- **Encapsulation**
- **Composition**
- **Aggregation**
- **Association**
- **Delegation**
- **Coupling**
- **Cohesion**

We are also implementing the MVC design pattern to the project. A control layer is added to control the inputs and
the flow of information with in the game, passing the information from the player to the model and the view.
Model is the logic of the game, it will contain the units, the map and is requesting unit interaction with player
inputs and enemy logics. The information is then passed to the view, which will display the information to the player.

## Getting Started

To play the game, you need to download the project and run the jar file through the command line.
In order to do so, you need to open the command line and navigate to the folder where the jar file is located.
To navigate the file directory, you need to use the cd command, for example:
- ``` cd C:\Users\user\Downloads\ ``` - Windows version - it is possible to right click the folder the jar is located
in and open the terminal from the menu, that set the directory of the terminal directly in the folder the jar is in.
- ``` cd /Users/user/Downloads/ ``` - Mac version
- ``` cd /home/user/Downloads/ ``` - Linux version


Then you need to run the following command:

- ``` java -jar hw3.jar "[NECESSARY ARGUMENTS]" ``` - The necessary argument is a file directory in which the levels are stored.

### Prerequisites

In order to run the game, you need to have Java installed on your computer.
You can download Java from the following link:

- https://www.java.com/en/download/ - Java download - **Windows version Java 15 is supported as per assignment requirements, yet is compatible with upto java 20 as well.**

