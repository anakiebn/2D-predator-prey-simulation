package org.anakie;

public abstract class Organism {

    private final char[][] world;
    private final char symbol;

    private final boolean activateLogs;


    public Organism(char[][] world,char symbol,boolean activateLogs){
        this.world=world;
        this.symbol=symbol;
        this.activateLogs=activateLogs;
    }

    public Organism(char[][] world,char symbol){
       this(world,symbol,true);
    }

    public abstract char[][] move();

    public int[] breed(int i,int j){

        // this helps our statement to adapt depending on the organism calling this method
        String organism=symbol=='X'?"Bug":symbol=='o'?"Ant":"Unknown organism";

        if(j+1<world.length && world[i][j+1]==' '){ //breeds to the right
            world[i][j+1]=symbol;
            if(activateLogs){
                System.out.println("\n"+organism+" at world["+i+"]["+j+"] bred a new "+organism+"!!!");
                System.out.println("\n"+organism+" at world["+i+"]["+(j+1)+"] is the new born "+organism+"!!!");
            }
            return new int[]{i,(j+1)};
        }
        if(j-1>=0 && world[i][j-1]==' '){ // breeds to the left
            world[i][j-1]=symbol;
            if(activateLogs){
                System.out.println("\n"+organism+" at world["+i+"]["+j+"] bred a new "+organism+"!!!");
                System.out.println("\n"+organism+" at world["+i+"]["+(j-1)+"] is the new born "+organism+"!!!");
            }
            return new int[]{i,(j-1)};
        }
        if(i+1<world.length && world[i+1][j]==' '){ // breeds down
            world[i+1][j]=symbol;
            if(activateLogs){
                System.out.println("\n"+organism+" at world["+i+"]["+j+"] bred a new "+organism+"!!!");
                System.out.println("\n"+organism+" at world["+(i+1)+"]["+j+"] is the new born "+organism+"!!!");
            }
            return new int[]{(i+1),j};
        }
        if(i-1>=0 && world[i-1][j]==' '){ // breeds up
            world[i-1][j]=symbol;
            if(activateLogs){
                System.out.println("\n"+organism+" at world["+i+"]["+j+"] bred a new "+organism+"!!!");
                System.out.println("\n"+organism+" at world["+(i-1)+"]["+j+"] is the new born "+organism+"!!!");
                return new int[]{(i-1),(j)};
            }
        }
        return null;
    }



}
