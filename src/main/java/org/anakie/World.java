package org.anakie;

public class World {

    private final int maxHorizontalLength;
    private final int maxVerticalLength;
    private final int initialBugNo;
    private final int initialAntNo;

    private static char[][] world;

    public World(int maxVerticalLength,int maxHorizontalLength,int initialBugNo,int initialAntNo){
        this.maxHorizontalLength=maxHorizontalLength;
        this.maxVerticalLength=maxVerticalLength;
        this.initialBugNo=initialBugNo;
        this.initialAntNo=initialAntNo;
        world=new char[maxVerticalLength][maxHorizontalLength];
        makeWorld();
    }

    public World(){
        this(4,4,2,3);
    }
    private void makeWorld(){
        
        for(int i=0;i<initialAntNo;i++){
            boolean tryAgain=true;
            while(tryAgain){
                int iValue=(int)(Math.random()*maxVerticalLength);
                int jValue=(int)(Math.random()*maxHorizontalLength);

                if(world[iValue][jValue]=='\u0000'){
                    world[iValue][jValue]='o';
                    tryAgain=false;
                }
            }
        }

        for(int j=0;j<initialBugNo;j++){
            boolean tryAgain=true;
            while(tryAgain){
                int iValue=(int)(Math.random()*maxVerticalLength);
                int jValue=(int)(Math.random()*maxHorizontalLength);
                if(world[iValue][jValue]=='\u0000'){
                    world[iValue][jValue]='X';
                    tryAgain=false;
                }
            }
        }
        
        for(int i=0;i<maxVerticalLength;i++){
            for (int j=0;j<maxHorizontalLength;j++){
                if(world[i][j]=='\u0000'){
                    world[i][j]=' ';
                }
            }
        }


    }
    public void setWorld(char[][] world){
        World.world =world;
    }

    public char[][] getWorld() {
        return world;
    }

    public boolean empty(){
         for(char[] arr:world){
            for(char symbol:arr){
                if(symbol!=' '){
                    return false;
                }
            }
        }
        return true;
    }
}
