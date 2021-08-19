# Recursive Maze Solver

Solve any size of text maze files.

## Description

This project allows users to give an input of a text file maze, with the walls made up off "#" and then path made up off " ". The program will then generate a random start point and a random end point through out the maze. Program will now solve the maze with the generated start and end points and while it does so will draw the path it takes. After a solution to the maze has been found the program will then output a text file that contains the solution to the maze. 
* FYI: Start postion is represented by: G, End Position is represented by: H.

## Getting Started

### Dependencies

* You will need to install the BasicIO Brock Package to the classpath of the java project. 
* BasicIO can be found in the Brock_Packages folder. 

### Installing

* Download the project to your computer and open the MazeSolver folder with any Java compatible IDE's.
* Find the section in your IDE that allows you to customize project classpath. Once found, add the BasicIO.jar (inside the Brock_Packages folder) as an external library/path. 

### Executing program

* When you run the program it will ask you to select a file, this is where you select the text file maze that you want solved. (Please refer to "Make your own maze"
below to learn how to make your own mazes). You can also select between the two text maze files provided, named: "mz1" and "mz2"
* Next you will be prompted to select an output file this will be for the solution to the maze, name your file anything you like with .txt at the end (this will make your output file a text file). 

## Make your own maze

* On the first line of the text file have two numbers, first one representing columns in your maze and the second representing the rows in your maze.
* From the next line you can start to fill up your maze with walls and paths. 
* Walls are represented by: #####
* Paths are represented by:      (blank spaces)
* TIP: Have a outline of walls that hold the maze inside. 
* Here is an example of a simple maze:
```
  8 	   11
  ###########
  #         #
  #### ######
  #    #    #
  #    #    #
  # ###### ##
  #         #
  ###########
```

## Authors

Abhi Patel
Contact: contact@abhipatel.ca

## Version History

* 0.1
    * Initial Release

## License

This project is licensed under the MIT License - see the LICENSE.md file for details

