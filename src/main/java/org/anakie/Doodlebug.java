package org.anakie;

public class Doodlebug extends Organism{

    private final int[][] starveTime;
    private final int[][] activeTime;
    private final char[][] world;
    private final boolean activateLogs;

    private boolean[][] attended;
    public Doodlebug(char[][] world,boolean activateLogs) {
        super(world, 'X',activateLogs);
        this.activateLogs=activateLogs;
        this.world=world;
        starveTime=new int[world.length][world.length];
        activeTime=new int[world.length][world.length];
        attended=new boolean[world.length][world.length];
    }
    public Doodlebug(char[][] world) {
        this(world,true);
    }
    @Override
    public char[][] move() {

        int selectDirection=(int) (Math.random()*4+1);
        attended=new boolean[world.length][world.length];

        for(int i=0;i<world.length;i++){
            for(int j=0;j< world.length;j++){

               // this filters all the bugs
                if(world[i][j]=='X' && !attended[i][j]){



                    // if it has been active 8 times we breed
                    if(activeTime[i][j]==8){

                        int[] cods=breed(i,j); // it breeds, cods holds the co-ordinates of the new bug

                        if(cods!=null){

                            int ourI=cods[0];
                            int ourJ=cods[1];

                            // this is for our bugs
                            // we don't want to move them once attended

                            activeTime[ourI][ourJ]=0;
                            attended[ourI][ourJ]=true;
                        }
                        activeTime[i][j]=0;
                        attended[i][j]=true;
                        continue;
                    }
                    // if it's been starving 3 times, it dies
                    if(starveTime[i][j]==3){
                        world[i][j]=' ';
                        activeTime[i][j]=0;
                        starveTime[i][j]=0;
                        if(activateLogs){
                            System.out.println("\nBug at world["+i+"]["+j+"] starved to dead so it disappeared!!!");
                        }
                        continue;
                    }

                    activeTime[i][j]++; //it increments the active time of a bug


                    if(j+1< world.length && world[i][j+1]=='o' && !attended[i][j]){ // if there's an ant next to it, the bug eats it
                        moveRight(i,j,true); //the 'true' is to signal that the bug ate the ant
                        continue;
                    }
                    if(j-1>=0 && world[i][j-1]=='o' && !attended[i][j]){
                        moveLeft(i,j,true);
                        continue;
                    }
                    if(i-1>=0 && world[i-1][j]=='o' && !attended[i][j]){
                        moveUp(i,j,true);
                        continue;
                    }
                    if(i+1< world.length && world[i+1][j]=='o' && !attended[i][j]){
                        moveDown(i,j,true);
                        continue;
                    }

                    starveTime[i][j]++; // if it doesn't eat we increment the starve time

                    switch (selectDirection){
                        case 1->{
                            if(j+1< world.length && world[i][j+1]==' ' && !attended[i][j]){
                                moveRight(i,j,false); // the false to signal  that it didn't eat
                                continue;
                            }

                        }
                        case 2->{
                            if(j-1>=0 && world[i][j-1]==' '&& !attended[i][j]) {
                                moveLeft(i, j, false);
                                continue;
                            }


                        }
                        case 3->{
                            if(i-1>=0 && world[i-1][j]==' '&& !attended[i][j]){
                                moveUp(i,j,false);
                                continue;
                            }

                        }
                        case 4->{
                            if(i+1< world.length && world[i+1][j]==' '&& !attended[i][j]){
                                moveDown(i,j,false);
                                continue;
                            }

                        }
                    }
                    // if it didn't move at all then it will just print this message
                    // otherwise this message is printed when it moves
                    if(activateLogs) {
                        System.out.printf("Bug at world[%d][%d] activeTime=%d ", i, j, activeTime[i][j]);
                        System.out.println();

                        System.out.printf("Bug at world[%d][%d] starveTime=%d ", i, j, starveTime[i][j]);
                        System.out.println();
                    }

                }
            }

        }

        return world;

    }

    public void moveRight(int i,int j,boolean bugAte){

        activeTime[i][j+1]=activeTime[i][j]; //as  long as it moves we track it's
        activeTime[i][j]=0;

        starveTime[i][j+1]=bugAte?0:starveTime[i][j]; // if it ate we reset the starve time, else we increment it
        starveTime[i][j]=0;

        world[i][j]=' ';
        world[i][j+1]='X';

        attended[i][j]=false;
        attended[i][j+1]=true; //since it is attended and moved, we flag it as moved

        if(activateLogs) {
            System.out.printf("Bug at world[%d][%d] activeTime=%d ", i, j + 1, activeTime[i][j + 1]);
            System.out.println();

            System.out.printf("Bug at world[%d][%d] starveTime=%d ", i, j + 1, starveTime[i][j + 1]);
            System.out.println();
        }

    }
    public void moveLeft(int i,int j,boolean bugAte){

        activeTime[i][j-1]=activeTime[i][j]; //as  long as it moves we track it's
        activeTime[i][j]=0;

        world[i][j]=' ';
        world[i][j-1]='X';

        starveTime[i][j-1]=bugAte?0:starveTime[i][j]; // if it ate we reset the starve time, else we increment it
        starveTime[i][j]=0;

        attended[i][j]=false;
        attended[i][j-1]=true;

        if(activateLogs) {
            System.out.printf("Bug at world[%d][%d] activeTime=%d ", i, j - 1, activeTime[i][j - 1]);
            System.out.println();

            System.out.printf("Bug at world[%d][%d] starveTime=%d ", i, j - 1, starveTime[i][j - 1]);
            System.out.println();
        }
   
    }
    public void moveUp(int i,int j,boolean bugAte){

        activeTime[i-1][j]=activeTime[i][j]; //as  long as it moves we track it's
        activeTime[i][j]=0;
        world[i][j]=' ';
        world[i-1][j]='X';

        starveTime[i-1][j]=bugAte?0:starveTime[i][j]; // if it ate we reset the starve time, else we increment it
        starveTime[i][j]=0;

        attended[i][j]=false;
        attended[i-1][j]=true;


        if(activateLogs) {
            System.out.printf("Bug at world[%d][%d] activeTime=%d ", i - 1, j, activeTime[i - 1][j]);
            System.out.println();

            System.out.printf("Bug at world[%d][%d] starveTime=%d ", i - 1, j, starveTime[i - 1][j]);
            System.out.println();
        }

    }
    public void moveDown(int i,int j,boolean bugAte){
        activeTime[i+1][j]=activeTime[i][j]; //as  long as it moves we track it's
        activeTime[i][j]=0;

        world[i][j]=' ';
        world[i+1][j]='X';

        starveTime[i+1][j]=bugAte?0:starveTime[i][j]; // if it ate we reset the starve time, else we increment it
        starveTime[i][j]=0;

        attended[i][j]=false;
        attended[i+1][j]=true;

        if(activateLogs) {
            System.out.printf("Bug at world[%d][%d] activeTime=%d ", i + 1, j, activeTime[i + 1][j]);
            System.out.println();

            System.out.printf("Bug at world[%d][%d] starveTime=%d ", i + 1, j, starveTime[i + 1][j]);
            System.out.println();
        }



    }
}
