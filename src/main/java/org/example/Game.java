package org.example;
import java.util.Scanner;

public class Game {

    private final World world;
    private final Organism ant;
    private final Organism bug;
    private int turn;

    public Game(World world,Organism ant,Organism bug){
        this.ant=ant;
        this.bug=bug;
        this.world=world;
    }


    public void Play(){


        Scanner scanner=new Scanner(System.in);
        boolean play=true;
        String n;
        while(play){
            turn++;
            System.out.print("bug turn: "+turn);
            n=scanner.nextLine();

            if(n.equals("")){
                world.setWorld(bug.move());
                display(world.getWorld());
            }else{play=false;}

            System.out.println("ant turn: "+turn);
            n=scanner.nextLine();
            if(n.equals("")){
                world.setWorld(ant.move());
                display(world.getWorld());
            }else play=false;
        }



    }

    public void display(char[][] world){
//        for(char[] array: world){
//            for(char symbol:array){
//                System.out.print("[ "+symbol+" ]");
//            }
//            System.out.println();
//        }
//        System.out.println(
//
//        );

        // shows the grid with in index format
        System.out.println();
        System.out.print("    ");
        for(int j=0;j<world.length;j++) {
            System.out.print(j + "    ");
        }
        System.out.println();
        for(int i=0;i<world.length;i++){
            System.out.print(i+" ");
            for(int j=0;j<world.length;j++){
                System.out.print("[ "+world[i][j]+" ]");
           }
            System.out.println();
        }
        System.out.println();

    }





}
