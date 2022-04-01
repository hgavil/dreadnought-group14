// main game logic

import Map.Square;
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
    private Player p1, p2;
    private Terrain gameMap;

    Game() {

    }


    public void matchMaking(User user) {
        throw new Error("Not yet implemented");
    }

    public Game(Player p1, Player p2, Terrain gameMap) {
        this.p1 = p1;
        this.p2 = p2;
        this.gameMap = gameMap;

    }

    public void setupGame(Player p1, Player p2, Scanner in, Terrain gameMap) {


        // player 1 picks (temp) 3 ships that they want to use, and where they will be placed on the map
        // 1. pick ship
        // 2. choose coordinates
        System.out.println("Player 1, please choose your ships!");
        chooseShips(p1, in, gameMap);

        // now player 2 does it
        hideScreen();

        System.out.println("Player 2, please choose your ships!");
        chooseShips(p2 ,in, gameMap);

        hideScreen();

        System.out.println("Both players have selected their ships! The game will now begin.");

    }

    public void chooseShips (Player player, Scanner in, Terrain gameMap) {
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
                        newShip.setName(shipTypes[userInput-1]);
                        in.nextLine();
                        safeInput = true;
                    }
                    // if the player didn't enter an int, try again
                } else {
                    System.out.println("Please enter the Ship #.");
                    in.nextLine();
                }
            } while (!safeInput);


            // check if the thing is occupied
            boolean spaceFree = false;
            do {

                // choose x coords
                System.out.println("Choose your ship's x coordinate from 0-9.");
                do {
                    if (setCoordinates(in, 1, newShip)) safeInput = true;
                } while (!safeInput);


                // reset the checks
                safeInput = false;

                // choose y coords
                System.out.println("Choose your ship's y coordinate from 0-9.");
                do {
                    if (setCoordinates(in, 2, newShip)) safeInput = true;
                } while (!safeInput);

                // put the space on the map
                Square mapSpace = gameMap.getMap().getSpace()[newShip.getXPos()][newShip.getYPos()];
                if (!mapSpace.Occupied()) {
                    mapSpace.changeOccupied(true);
                    newShip.setOwner(player.getName());
                    mapSpace.changeItem(player.getName());
                    spaceFree = true;
                }
                else {
                    System.out.println("That space is occupied! Try again.");
                }
            } while (!spaceFree);


            // add the ship to the player's list of ships.
            System.out.println("Your ship is a: " + newShip.getName() + " at coordinates (" + newShip.getXPos() + ", " + newShip.getYPos() + ").");
            player.addShip(newShip);

            System.out.println();


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

    public void hideScreen() {
        // print out a barrier so players cant see what the other one chose
        for (int i = 0; i < 50; i++) {
            System.out.println("-");
        }
    }
}
