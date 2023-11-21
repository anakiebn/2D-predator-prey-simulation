# 2D-predator-prey-simulation

- It's a simulation game of a grid world where doodlebugs eat ants, this game showcases my problem solving skills in 
  java and  it's the rules of the game that makes it all interesting.


# How it works

- By default the world is a 4x4 grid with 2 bugs and 3 ants.
- As a user you can customize the size of the world with certain number of bugs and ants just make sure they can fit into the world
- Define the grid, could be 4×4 grid, less or more.
- After defining all that, to play the game it's simple.
- press enter then the organisms will automatically eat each other, breed and some will die and disappear
- the game ends if there are no organisms at all
- As a developer you can activate/disable the log messages that show which organism moved and their location or status.
- By default the log messages are enabled

# Game rules

- The bugs will move first.
- If a bug survives 8 moves without starving to death it breeds.
- If it moves 3 times without eating an ant, it dies( disappear).
- If the ant survives/ not eaten in 3 last moves, it breeds.

# Demo

 ## The Initial World View

  - Note that 'X' is a bug and 'o' is an ant

![InitWorld](https://github.com/anakiebn/2D-predator-prey-simulation/blob/ad7cf53e835932f16e4fad83e8b79dcae962e8ac/initial.png)

 ## Log Messages show Organisms Status

  - ####  Ants with activeTime of 3 will breed on the next timestep
  - ####  Bugs with starveTime of 3 will die on the next timestep
  - ####  Bugs with activeTime of 8 will breed on the next timestep

![logs](https://github.com/anakiebn/2D-predator-prey-simulation/blob/ad7cf53e835932f16e4fad83e8b79dcae962e8ac/showing%20timestep%203%20for%20ants%20and%20starve%20time%20for%20bug.png)

 ## Should the ant survive 3 steps it breeds

![ant breeds](https://github.com/anakiebn/2D-predator-prey-simulation/blob/ad7cf53e835932f16e4fad83e8b79dcae962e8ac/ant%20breeds.png)

 ## Bugs with starveTime of 3 will die on the 4th time step

![bug dies](https://github.com/anakiebn/2D-predator-prey-simulation/blob/ad7cf53e835932f16e4fad83e8b79dcae962e8ac/bug%20dies.png)

 ## The official problem statement

![problem](https://github.com/anakiebn/2D-predator-prey-simulation/blob/ad7cf53e835932f16e4fad83e8b79dcae962e8ac/Screenshot%20(45).png)
 

