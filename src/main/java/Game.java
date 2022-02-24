// main game logic

import Map.Terrain;
import Ships.*;
import tools.Pair;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    private String[] shipTypes = {"Carrier", "Corvette", "Cruiser", "Dreadnought", "Stealthship"};
    private Pair<String, Float> scoreboard;
    private int gameMode;
    private final int NUMSHIPS = 3;
    private final int BOARDSIZE = 10;

    Game() {

    }


    public void matchMaking(User user) {
        throw new Error("Not yet implemented");
    }

    public void setupGame(Player p1, Player p2, Scanner in, Terrain gameMap) {

        chooseShips(p1, in);
        // player 1 picks (temp) 3 ships that they want to use, and where they will be placed on the map
        // 1. pick ship
        // 2. choose coordinates

        // now player 2 does it

        // create a new match
        // create the first round
    }

    public void chooseShips (Player player, Scanner in) {
        int userInput;
        boolean safeInput;

        for (int i = 0; i < NUMSHIPS; i++) {

            Spaceship newShip = null;

            // display the ship options
            System.out.println("Enter the Ship # from the list below for Ship " + (i + 1));
            listShips();

            // take the user input for the ship type
            safeInput = false;
            do {
                // check if player correctly entered an int value
                if (in.hasNextInt()) {
                    // read the player's choice
                    userInput = in.nextInt();

                    // make sure it is a valid choice. if not, try it again
                    if (userInput < 1 || userInput > 5) {
                        System.out.println("Please enter the Ship #.");
                        in.nextLine();
                    }
                    // if it was, continue on
                    else {
                        System.out.println("You chose a " + shipTypes[userInput-1] + "!");
                        newShip = createShip(userInput);    // initialize the new ship as the type of ship it is
                        in.nextLine();
                        safeInput = true;
                    }
                    // if the player didn't enter an int, try again
                } else {
                    System.out.println("Please enter the Ship #.");
                    in.nextLine();
                }
            } while (!safeInput);

            // reset the checks
            safeInput = false;
            userInput = -1;

            System.out.println("Choose your ship's x coordinate from 0-9.");
            // take the user input for the x coordinate
            do {

            } while (!safeInput);

            // reset the checks
            safeInput = false;
            userInput = -1;

            System.out.println("Choose your ship's y coordinate from 0-9.");
            while (!setCoordinates(in, 1, newShip));

        }
    }

    public void listShips() {
        System.out.println(" 1. Carrier: (info about ship)");
        System.out.println(" 2. Corvette: (info about ship)");
        System.out.println(" 3. Cruiser: (info about ship)");
        System.out.println(" 4. Dreadnought: (info about ship)");
        System.out.println(" 5. Stealthship: (info about ship)");
    }

    public boolean setCoordinates(Scanner in, int axis, Spaceship newShip) {
        int userInput = -1;
        // check if player correctly input an int value
        if (in.hasNextInt()) {
            // read the player's input
            userInput = in.nextInt();

            // make sure it's a valid choice
            if (userInput < 0 || userInput > BOARDSIZE-1) {
                System.out.println("Please choose a valid x coordinate between 0 and 9.");
                in.nextLine();
                return false;
            }

            // if it's valid, make note of it
            else {
                if (axis == 1) newShip.setXPos(userInput);     // set the new ship's xpos to be the user input
                else newShip.setYPos(userInput);

                in.nextLine();
                return true;
            }

            // if the user didn't input an int, try again
        } else {
            System.out.println("Please choose a valid x coordinate between 0 and 9.");
            in.nextLine();
        }
        return false;
    }

    public Spaceship createShip(int shipType) {
        Spaceship ship = null;
        switch (shipType) {
            case 1:
                ship = new Carrier();
                break;
            case 2:
                ship = new Corvette();
                break;
            case 3:
                ship = new Cruiser();
                break;
            case 4:
                ship = new Dreadnought();
                break;
            case 5:
                ship = new Stealthship();
                break;

        }
        return ship;
    }

}
