// main game logic

import tools.Pair;

public class Game {

    private Pair<String, Float> scoreboard;
    private int gameMode;

    Game() {

    }


    public void matchMaking(User user) {
        throw new Error("Not yet implemented");
    }

    public void setupGame(Player p1, Player p2) {

        for (int i = 1; i < 4; i++) {
            System.out.println("Choose Ship #" + i + " from the list below.");

        }
        // player 1 picks (temp) 3 ships that they want to use, and where they will be placed on the map
        // 1. pick ship
        // 2. choose coordinates

        // now player 2 does it

        // create a new match
        // create the first round
    }

}
