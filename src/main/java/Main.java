// the main class

import Map.Terrain;

public class Main {

    public static void main(String[] args) {
        /* initialization phase */

        // create game instance for the game to begin
        Game game = new Game();

        // create player instances
        Player p1 = new Player();
        Player p2 = new Player();

        // create map
        Terrain gameMap = new Terrain();


        /* setup phase */
        /* all console prints done in main, the rest is done in the game class */

        // console prints introductory message
        System.out.println("Welcome to Dreadnought.");
        System.out.println("In this game, two players will hide three ships on the game board, and take turns guessing where the other player's ships are.");
        System.out.println("To win, eliminate all of your opponent's ships.");
        System.out.println("To begin, Player 1 will set up their ships first.");

        // run setup method
        game.setupGame(p1, p2);


        // console prints directions for the player
        System.out.println();
        System.out.println("Player 1, pick your ships.");
        // player 1 picks (temp) 3 ships that they want to use, and where they will be placed on the map
        // 1. pick ship
        // 2. choose coordinates

        // now player 2 does it



        /* game phase */

        // console prints game start messages and turn swap messages



        /* game end phase */

        // console prints an ending message and exits


    }
}
