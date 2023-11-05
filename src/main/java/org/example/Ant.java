package org.example;

public class Ant extends Organism{

    private final char[][] world;
    private final int[][] activeTime;
    private boolean[][] attended; // shows if we made any operations in each step
    private final boolean activateLogs;
    public Ant(char[][] world,boolean activateLogs){
        super(world,'o',activateLogs);
        this.activateLogs=activateLogs;
        this.world=world;
        activeTime=new int[world.length][world.length];

    }
    public Ant(char[][] world){
        this(world,true);
    }


    @Override
    public char[][] move() {



       attended=new boolean[world.length][world.length];
            for(int i=0;i<world.length;i++){
                for(int j=0;j<world.length;j++){

                    //filters ants
                    if(world[i][j]=='o'){

                    //active time is activated immediately we access an ant

                    // if it survives 3 moves it breeds
                    if(activeTime[i][j]==3){
                        int[] cods=breed(i,j);
                        int ourI=cods[0];
                        int ourJ=cods[1];

                        // make sure that our current bug active time is reset
                        // and that we mark it as attended so don't move it after being attended
                        activeTime[i][j]=0;
                        attended[i][j]=true;

                        // this is for our ants
                        // we don't want to move them once attended
                        activeTime[ourI][ourJ]=0;
                        attended[ourI][ourJ]=true;



                        continue;
                    }

                        //  as soon the ant is attended we increment it's active time
                        activeTime[i][j]++;

                    //ensures each ant moves where ever it desires randomly
                    int select = (int) (Math.random() * 4 + 1);

                        //goes to the chosen direction
                        switch (select) {
                            case 1 -> {
                                moveAntLeft(i,j,attended[i][j]);
                                continue;
                            }
                            case 2 -> {
                                moveAntRight(i,j,attended[i][j]);
                                continue;
                            }
                            case 3 -> {
                                moveAntDown(i,j,attended[i][j]);
                                continue;
                            }
                            case 4 -> {
                                 moveAntUp(i,j,attended[i][j]);
                                continue;
                            }
                        }

                        // this is only attended if the ant didn't move at all
                        if(activateLogs && !attended[i][j]){
                            System.out.printf("Ant at world[%d][%d] activeTime=%d ",i,j,activeTime[i][j]);
                            System.out.println();
                        }


                    }

                }
            }

        return world;
    }

    private void moveAntUp(int i,int j,boolean used) {

            //if it moves
            if(i-1>=0 && world[i-1][j]==' ' && !used){
                activeTime[i-1][j]=activeTime[i][j];
                activeTime[i][j]=0;

                world[i][j]=' ';
                world[i-1][j]='o';



                attended[i-1][j]=true; // ensures that we move an ant once and only once per step
                attended[i][j]=false;

                if(activateLogs){
                    System.out.printf("Ant at world[%d][%d] activeTime=%d ",i-1,j,activeTime[i-1][j]);
                    System.out.println();
                }
                return;
            }




    }

    private void moveAntDown(int i,int j,boolean used) {

            if(i+1< world.length  && world[i+1][j]==' ' && !used){
                activeTime[i+1][j]=activeTime[i][j];
                activeTime[i][j]=0;

                world[i][j]=' ';
                world[i+1][j]='o';
                attended[i+1][j]=true;
                attended[i][j]=false;

                if(activateLogs){
                    System.out.printf("Ant at world[%d][%d] activeTime=%d ",i+1,j,activeTime[i+1][j]);
                    System.out.println();
                }

            }

    }

    private void moveAntRight(int i,int j,boolean used) {


            if(j+1< world.length && world[i][j+1]==' ' && !used){

                activeTime[i][j+1]=activeTime[i][j];
                activeTime[i][j]=0; //resets the activeTime of the bug

                world[i][j]=' ';
                world[i][j+1]='o';

                attended[i][j+1]=true;
                attended[i][j]=false;

                if(activateLogs){
                    System.out.printf("Ant at world[%d][%d] activeTime=%d ",i,j+1,activeTime[i][j+1]);
                    System.out.println();
                }

            }

    }

    private void moveAntLeft(int i,int j,boolean used) {


            if(j-1>=0 && world[j-1][j]==' ' && !used){
                activeTime[i][j-1]=activeTime[i][j];
                activeTime[i][j]=0;

                world[i][j]=' ';
                world[i][j-1]='o';

                attended[i][j-1]=true; //ensures that the ant is only moved once per turn
                attended[i][j]=false;

                if(activateLogs){
                    System.out.printf("Ant at world[%d][%d] activeTime=%d ",i,j-1,activeTime[i][j-1]);
                    System.out.println();
                }


        }

    }


}
