package org.anakie;

public class Ant extends Organism {

    private final char[][] world;
    private final int[][] activeTime;
    private boolean[][] attended = null; // shows if we made any operations in each step
    private final boolean activateLogs;

    public Ant(char[][] world, boolean activateLogs) {
        super(world, 'o', activateLogs);
        this.activateLogs = activateLogs;
        this.world = world;
        activeTime = new int[world.length][world.length];

    }

    public Ant(char[][] world) {
        this(world, true);
    }


    @Override
    public char[][] move() {
        //this is resets per time step, enforces the rules, for an ant to move once per step
        //if it has been attended we mark it as such
        attended = new boolean[world.length][world.length];

        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world.length; j++) {

                //filters ants
                if (world[i][j] == 'o') {

                    if(activeTime[i][j]==3 && !attended[i][j]){
                        breedAlgorithm(i,j);
                        continue;
                    }

                    if (!attended[i][j]) {
                        //each time step, we increment the activeTime of an ant
                        activeTime[i][j]++;
                    } else {
                        continue;
                    }






                    //determines the direction of the ant, randomly
                    int select = (int) (Math.random() * 4 + 1);

                    //we move to the selected direction
                    switch (select) {
                        case 1 -> {
                            moveAntLeft(i, j, attended[i][j]);
                        }
                        case 2 -> {
                            moveAntRight(i, j, attended[i][j]);
                        }
                        case 3 -> {
                            moveAntDown(i, j, attended[i][j]);
                        }
                        case 4 -> {
                            moveAntUp(i, j, attended[i][j]);
                        }
                    }

                    //reached if it didn't move at all
                    if (attended[i][j] && activateLogs) {
                        // this code is only reached if the ant didn't find an empty space to occupy
                        // meaning it didn't move at all
                        System.out.printf("Ant at world[%d][%d] activeTime=%d ", i, j, activeTime[i][j]);
                        System.out.println();

                    }


                }

            }
        }

        return world;
    }

    public void breedAlgorithm(int i,int j){



            int[] cods=breed(i,j); // this method returns the co-ordinates of the newborn ant

            if(cods!=null){  //if the newborn found a space to occupy and has co-ordinates

                int ourI=cods[0]; //we retrieve the i-value
                int ourJ=cods[1]; //we retrieve the j value
                activeTime[ourI][ourJ]=0; //ensures that it's active time is zero
                attended[ourI][ourJ]=true; // this  makes sure that we don't do any operations on the newborn ant in this step
            }

            // make sure that our current ant active time is reset to 0
            // and that we mark it as attended so don't move it anymore in this step
            activeTime[i][j]=0;
            attended[i][j]=true;


    }


    private void moveAntUp(int i, int j, boolean used) {


        //if there's space above the ant, it will occupy it
        // i-1>=0, acts as a safeguard that ensure we don't pass margins when we move up
        if (i - 1 >= 0 && world[i - 1][j] == ' ' && !used) {  //the 'used' variable, ensures that we only operate on the ant once per time step

            activeTime[i - 1][j] = activeTime[i][j]; // keeps track of the activeTime of an ant
            activeTime[i][j] = 0;

            world[i][j] = ' ';
            world[i - 1][j] = 'o';
            attended[i - 1][j] = true; // ensures that we move an ant once and only once per step
            attended[i][j] = false;


            if (activateLogs) { // if the logs are activated we print them
                System.out.printf("Ant at world[%d][%d] activeTime=%d ", (i - 1), j, activeTime[i - 1][j]);
                System.out.println();
            }
            return;
        }
        attended[i][j] = true;

    }

    private void moveAntDown(int i, int j, boolean used) {

        if (i + 1 < world.length && world[i + 1][j] == ' ' && !used) {
            activeTime[i + 1][j] = activeTime[i][j];
            activeTime[i][j] = 0;

            world[i][j] = ' ';
            world[i + 1][j] = 'o';
            attended[i + 1][j] = true;
            attended[i][j] = false;

            if (activateLogs) {
                System.out.printf("Ant at world[%d][%d] activeTime=%d ", (i + 1), j, activeTime[i + 1][j]);
                System.out.println();
            }
            return;
        }
        attended[i][j] = true;
    }

    private void moveAntRight(int i, int j, boolean used) {

        if (j + 1 < world.length && world[i][j + 1] == ' ' && !used) {

            activeTime[i][j + 1] = activeTime[i][j];
            activeTime[i][j] = 0; //resets the activeTime of the ant

            world[i][j] = ' ';
            world[i][j + 1] = 'o';

            attended[i][j + 1] = true;
            attended[i][j] = false;

            if (activateLogs) {
                System.out.printf("Ant at world[%d][%d] activeTime=%d ", i, (j + 1), activeTime[i][j + 1]);
                System.out.println();
            }
            return;
        }
        attended[i][j] = true;

    }

    private void moveAntLeft(int i, int j, boolean used) {

        if (j - 1 >= 0 && world[j - 1][j] == ' ' && !used) {
            activeTime[i][j - 1] = activeTime[i][j];
            activeTime[i][j] = 0;

            world[i][j] = ' ';
            world[i][j - 1] = 'o';

            attended[i][j - 1] = true; //ensures that the ant is only moved once per turn
            attended[i][j] = false;

            if (activateLogs) {
                System.out.printf("Ant at world[%d][%d] activeTime=%d ", i, (j - 1), activeTime[i][j - 1]);
                System.out.println();
            }

            return;
        }
        attended[i][j] = true;


    }


}
