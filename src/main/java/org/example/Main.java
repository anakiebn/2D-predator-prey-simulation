package org.example;

import com.sun.security.jgss.GSSUtil;

public class Main {

    static World world=new World(3,3,1,2);
    static char[][] initialWorld=world.getWorld();
    public static void main(String[] args) {



        Organism ant=new Ant(initialWorld);
        Organism bug=new Doodlebug(initialWorld);
        Game game=new Game(world,ant,bug);
        game.display(initialWorld);
        game.Play();


    }

}