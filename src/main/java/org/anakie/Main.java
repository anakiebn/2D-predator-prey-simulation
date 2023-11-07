package org.anakie;

public class Main {

    // new World() is the default constructor which defines the default parameters of the world,
    //it's 4x4 grid with 2 initial bugs and 3 initial ants
//    private static World world=new World();

    // world 2 has user defined grid and with initial organisms defined
    // you can use it
    private static World world=new World(5,5,2
            ,5);
    private static char[][] initialWorld=world.getWorld();

    public static void main(String[] args) {

        Organism ant=new Ant(initialWorld);
        Organism bug=new Doodlebug(initialWorld);
        Game game=new Game(world,ant,bug);
        System.out.println("\n...... Initial world [ The beginning ] ....... ");
        game.display(initialWorld);
        game.Play();
    }



}