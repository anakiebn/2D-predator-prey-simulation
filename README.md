# 2D-predator-prey-simulation

- It's a simulation game of a grid world where doodlebugs eat ants, this game showcases my problem solving skills in 
  java and  it's the rules of the game that makes it all interesting.


# How it works

- As a user you'll need to initialize the world with bugs and ants just make sure they can fit into the world
- Define the grid, could be 4×4 grid, less or more.
- After defining all that, to play the game it's simple.
- press enter then the organisms will automatically eat each other, breed and some will die and disappear
- the game ends if there are no organisms at all
- As a developer you can activate/disable the log messages that show which organism moved and their location or status.

# Game rules

- The bugs will move first.
- If a bug survives 8 moves without starving to death it breeds.
- If it moves 3 times without eating an ant, it dies( disappear).
- If the ant survives/ not eaten in 3 last moves, it breeds.


# Demo

 ## Initial world

-Note that 'X' is a bug and 'o' is an ant

![Initially the world looks like this grid](https://github.com/anakiebn/AntBugSimulationGame/blob/cc8eb4fa7e295210bc81320b11f57e10a435691b/inti.png)

 ## When the starve time is 3 for the bug it dies in the next time step

![starveTime](https://github.com/anakiebn/AntBugSimulationGame/blob/cc8eb4fa7e295210bc81320b11f57e10a435691b/starvTi.png)

 ## Should the ant survive 3 steps it breeds

![ant breeds](https://github.com/anakiebn/AntBugSimulationGame/blob/cc8eb4fa7e295210bc81320b11f57e10a435691b/new%20ant.png)


 

