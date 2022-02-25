// the main class

import Map.Terrain;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /* initialization phase */

        // create game instance for the game to begin
        Game game = new Game();

        // create player instances
        Player p1 = new Player(1);
        Player p2 = new Player(2);

        // create map
        Terrain gameMap = new Terrain();

        Scanner in = new Scanner(System.in);


        /* setup phase */

        // console prints introductory message
        System.out.println("Welcome to Dreadnought.");
        System.out.println("In this game, two players will hide three ships on the game board, and take turns guessing where the other player's ships are.");
        System.out.println("To win, eliminate all of your opponent's ships.");
        System.out.println("To begin, Player 1 will set up their ships first.");

        // run setup method
        game.setupGame(p1, p2, in, gameMap);


        /* game phase */

        // console prints game start messages and turn swap messages
        Round match = new Round(p1, p2, gameMap, in);
        match.getWinner();



        /* game end phase */

        // console prints an ending message and exits


    }
}
