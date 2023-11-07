package org.anakie;

public class TheAnt extends Organism{

    private final char[][] world;
    private int select;
    private final int[][] activeTime;
    private boolean moved;

    public TheAnt(char[][] world){
        super(world,'o');
        this.world=world;
        activeTime=new int[world.length][world.length];

    }

    @Override
    public char[][] move() {

        //randomly chooses the direction of all ants
        while (true){

            select=(int)(Math.random()*4+1);

            switch (select) {
                case 1 -> {
                    return moveAntLeft();
                }
                case 2 -> {
                    return moveAntRight();
                }
                case 3 -> {
                    return moveAntDown();
                }
                case 4 -> {
                    return moveAntUp();
                }
            }
        }
    }
    private char[][] moveAntLeft(){


        for(int i=0;i<world.length;i++){
            for(int j=0;j<world.length;j++){


                if (world[i][j]=='o'){
                    activeTime[i][j]++;
                    if(j-1>=0 && world[i][j]=='o' && world[i][j-1]==' '){

                        System.out.println(activeTime[i][j]);

                        activeTime[i][j-1]=activeTime[i][j];
                        activeTime[i][j]=0;

                        world[i][j]=' ';
                        world[i][j-1]='o';

                        if(activeTime[i][j-1]>3){ //so if the ant has moved 3 times, it breeds
                            breed(i,(j-1)); //the i and the j-1, are the indexes of the ant that must breed
                            activeTime[i][j-1]=0;
                        }
                    }
                }
                if(activeTime[i][j]>3){
                    breed(i,j);
                    activeTime[i][j]=0;
                    System.out.println(activeTime[i][j]);

                }


            }
        }
        return world;

    }
    private char[][] moveAntRight(){

        // this loop is made on purpose, to ensure that the ant on the right moves first, leaving the ant behind it
        // a space to occupy.

        for(int i=world.length-1;i>=0;i--){
            for(int j=world.length-1;j>=0;j--){


                if(world[i][j]=='o'){
                    activeTime[i][j]++;
                    if(world[i][j]=='o' && j+1<world.length && world[i][j+1]==' '){

                        activeTime[i][j+1]=activeTime[i][j];
                        activeTime[i][j]=0;
                        System.out.println(activeTime[i][j]);


                        world[i][j]=' ';
                        world[i][j+1]='o';

                        if(activeTime[i][j+1]>3){ //so if the ant has moved 3 times, it breeds in adjacent position
                            breed(i,(j+1)); //it breeds,
                            activeTime[i][j+1]=0;
                        }
                    }
                }
                if(activeTime[i][j]>3){ //so if the ant has moved 3 times, it breeds in adjacent position
                    breed(i,j); //it breeds,
                    activeTime[i][j]=0;
                    System.out.println(activeTime[i][j]);
                }

            }
        }
        return world;

    }

    private char[][] moveAntUp(){

        for(int i=0;i< world.length;i++){
            for(int j=0;j<world.length;j++){

                if (world[i][j]=='o'){
                    activeTime[i][j]++;
                    if(world[i][j]=='o' && i-1>=0 && world[i-1][j]==' '){
                        System.out.println(activeTime[i][j]);

                        int n= activeTime[i][j];
                        activeTime[i-1][j]=activeTime[i][j]++;
                        activeTime[i][j]=0;

                        world[i][j]=' ';
                        world[i-1][j]='o';

                        if(activeTime[i-1][j]>3){
                            breed((i-1),j);
                            activeTime[i-1][j]=0;
                        }
                    }
                }

                if(activeTime[i][j]>3){
                    breed(i,j);
                    activeTime[i][j]=0;
                    System.out.println(activeTime[i][j]);

                }

            }
        }
        return world;

    }
    private char[][] moveAntDown(){

        for(int i= world.length-1;i>=0;i--){
            for(int j= world.length-1;j>=0;j--){

                if(world[i][j]=='o'){
                    activeTime[i][j]++;
                    if(i+1< world.length && world[i+1][j]==' '){

                        System.out.println(activeTime[i][j]);

                        activeTime[i+1][j]=activeTime[i][j];
                        activeTime[i][j]=0;

                        world[i][j]=' ';
                        world[i+1][j]='o';

                        if(activeTime[i+1][j]>3){
                            breed((i+1),j);
                            activeTime[i+1][j]=0;
                        }
                    }
                }

                if(activeTime[i][j]>3){
                    breed(i,j);
                    activeTime[i][j]=0;
                    System.out.println(activeTime[i][j]);
                }

            }
        }
        return world;

    }

    public int getSelect() {
        return select;
    }




}
