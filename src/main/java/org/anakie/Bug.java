package org.anakie;


public class Bug extends Organism{

    private final int[][] starvingTime;
    private final int[][] eatCount;
    private final boolean[][] bugAte;
    private char[][] world;

    private int[][] activeTime;

    public Bug(char[][] world){
        super(world,'X');
        this.world=world;
        bugAte=new boolean[world.length][world.length];
        starvingTime=new int[world.length][world.length];
        eatCount=new int[world.length][world.length];
    }

    @Override
    public char[][] move() {
        boolean[][] movedAlready = new boolean[world.length][world.length];

        int selectDirection = (int) (Math.random() * 4 + 1);

        for(int i=0;i< world.length;i++) {
            for (int j = 0; j < world.length; j++) {

                if (world[i][j] == 'X' && !movedAlready[i][j]) {         //filters bugs only
                    if (j + 1 < world.length && world[i][j + 1] == 'o') { //checks if there's an ant on the right-side

                        right(i,j,true);

                        movedAlready[i][j]=false; //this ensures that we only move each bug once per turn
                        movedAlready[i][j+1]=true;
                        break;
                    }

                    //checks if there's an ant on the left-side, so it can eat it
                    //also the !movedAlready[i][j] ensures that we move each bug once per time step
                    if (j - 1 >= 0 && world[i][j - 1] == 'o' && !movedAlready[i][j]) {



                        movedAlready[i][j]=false;
                        movedAlready[i][j-1]=true;
                        break;
                    }
                    if (i - 1 >= 0 && world[i - 1][j] == 'o' && !movedAlready[i][j] ) { //checks if there's an ant above it


                        movedAlready[i][j]=false;
                        movedAlready[i-1][j]=true;
                        break;

                    }
                    if (i + 1 < world.length && world[i + 1][j] == 'o' && !movedAlready[i][j]) {//checks if there's an ant below it


                        movedAlready[i][j]=false;
                        movedAlready[i+1][j]=true;
                        break;
                    }
                    switch (selectDirection) { // this is called the enhanced switch
                        case 1 -> {
                            if(j-1>=0 && world[i][j-1]==' ' && !movedAlready[i][j]){


                                movedAlready[i][j]=false;
                                movedAlready[i][j-1]=true;



                            }
                        }
                        case 2 -> {
                            if (j + 1 < world.length && world[i][j + 1] == ' '  && !movedAlready[i][j]){

                                movedAlready[i][j]=false;
                                movedAlready[i][j+1]=true;

                            }
                        }
                        case 3 -> {
                            if (i - 1 >= 0 && world[i - 1][j] == ' '  && !movedAlready[i][j]) {


                                movedAlready[i][j]=false;
                                movedAlready[i-1][j]=true;

                            }
                        }
                        case 4 ->{
                            if (i + 1 < world.length && world[i + 1][j] == ' '  && !movedAlready[i][j]){


                                movedAlready[i][j]=false;
                                movedAlready[i+1][j]=true;

                            }
                        }
                    }
                }
            }
        }


        return world;
    }

    private void left(int i,int j,boolean bugAteAnt){ // eat or move to the left side
        if (!bugAteAnt) { //if the bug didn't eat...
            if (starvingTime[i][j - 1] > 3) { //after 3 time steps, we check if it ate anything before
                starvingTime[i][j - 1] = 0;
                world[i][j] = ' ';
                world[i][j - 1] = ' ';
            }

            starvingTime[i][j - 1] = starvingTime[i][j] + 1; // everytime a bug moves without eating we increment it's starving time
            starvingTime[i][j] = 0;

            world[i][j] = ' ';
            world[i][j - 1] = 'X';


        } else {
            world[i][j - 1] = 'X';
            world[i][j] = ' ';
            bugAte[i][j] = false;
            bugAte[i][j - 1] = true; // reflect that it at


            starvingTime[i][j - 1] = 0;
        }


    }


    private void right(int i,int j,boolean bugAteAnt) {

        if (!bugAteAnt) { //if the bug didn't eat

            starvingTime[i][j + 1] = starvingTime[i][j] + 1; // everytime a bug moves without eating we increment it's starving time
            starvingTime[i][j] = 0;

            if (starvingTime[i][j + 1] > 3) { //after 3 time steps, we check if it ate anything before
                starvingTime[i][j + 1] = 0;
                world[i][j] = ' ';
                world[i][j + 1] = ' ';
            }

        } else {
            world[i][j + 1] = 'X';
            world[i][j] = ' ';
            bugAte[i][j] = false;
            bugAte[i][j + 1] = true; // reflect that it at
            starvingTime[i][j + 1] = 0;
        }
    }
    private void down(int i,int j,boolean bugAteAnt){

        if (!bugAteAnt) { //if the bug didn't eat

            starvingTime[i][j + 1] = starvingTime[i][j] + 1; // everytime a bug moves without eating we increment it's starving time
            starvingTime[i][j] = 0;

            if (starvingTime[i + 1][j] > 3) { //after 3 time steps, we check if it ate anything before
                starvingTime[i + 1][j] = 0;
                world[i][j] = ' ';
                world[i + 1][j] = ' ';
            }
        } else {
            world[i + 1][j] = 'X';
            world[i][j] = ' ';
            bugAte[i][j] = false;
            bugAte[i+ 1][j ] = true; // reflect that it at
            starvingTime[i+ 1][j] = 0;
        }



    }

    private void up(int i,int j,boolean bugAteAnt){

        if (!bugAteAnt) { //if the bug didn't eat

            starvingTime[i-1][j] = starvingTime[i][j] + 1; // everytime a bug moves without eating we increment it's starving time
            starvingTime[i][j] = 0;

            if (starvingTime[i -1][j] > 3) { //after 3 time steps, we check if it ate anything before
                starvingTime[i-1][j] = 0;
                world[i][j] = ' ';
                world[i-1][j] = ' ';
            }
        } else {
            world[i-1][j] = 'X';
            world[i][j] = ' ';
            bugAte[i][j] = false;
            bugAte[i-1][j ] = true; // reflect that it at
            starvingTime[i-1][j] = 0;
        }



    }


}
