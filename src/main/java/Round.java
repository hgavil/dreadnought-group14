import tools.Pair;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import Map.Terrain;
import Ships.Spaceship;

public class Round {
private ArrayList<Player> players;
private Pair<Boolean,Vector<Vector<Integer>>> hitMap;
private float time;
private int turn;

 public void updateMap() {
	
 }

 public void nextTurn() {
	if(turn == 0) {
		turn = 1;
	}
	else {
		turn = 0;
	}
 }
 
 public boolean checkPlayers() { 
	 return true;
 }
 public void getWinner() {
	 int playerName = 0;
	 int Max = 0;
	 for(Player i : players) {
		 if(i.getWins() > Max) {
			 Max = i.getWins();
			 playerName = i.getName();
		 }
	 }
   System.out.println("Winner Selected! Player: " + playerName + " is the winner with " + Max + "wins.\n");
	 return;
 }

  public Round(Player p1, Player p2, Terrain t, Scanner in){
    turn = 0;       // player 1 is turn 0, player 2 is turn 1
    int hit = 0;    // to store who we hit: player, nothing, or sprite
    int attack = 0; // which type of attack
    int x = -1;        // x and y of where we attacked
    int y = -1;

    //player 1/ turn 0 starts 
    players = new ArrayList<Player>();

    players.add(p1);
    players.add(p2);

    for(Player i : players)
      System.out.println("Welcome player "+i.getName()+"!\n");

    while (p1.Ships().size() != 0 && p2.Ships().size() != 0){
      t.getMap().show2D();

      // player chooses to attack or do a special move
      // player 1
      if (turn == 0){
        System.out.println("Player 1 please choose a move\n");
        attack = chooseAttack(p1, in);
      }
      else { // player 2
        System.out.println("Player 2 please choose a move\n");
        attack = chooseAttack(p2, in);
      }

      if(attack == 1){
        x = chooseXY(0, in);
        y = chooseXY(1, in);
        hit = t.getMap().getSpace()[x][y].Item();
        // dont want to hit and already hit spot or ourselves
        while (t.getMap().getSpace()[x][y].Hit() || hit == turn+1){
          if (hit == turn+1)
            System.out.println(x+", "+y+" your ship is here. What kind of big brain play are you going for? Please choose another pair\n");
          else
            System.out.println(x+", "+y+" has been hit, please choose another pair\n");
          x = chooseXY(0, in);
          y = chooseXY(1, in);
          // store what we hit
          hit = t.getMap().getSpace()[x][y].Item();
        }
        System.out.println("You hit "+hit+" \n");
      }
      else{
        System.out.println("Select which ship you'd like to perform a special attack\n");
        ArrayList<Spaceship> availableShips = players.get(turn-1).Ships();
        int selection = getUserInput(in);
        System.out.println("Player: " + turn + " has selected the ship: " + availableShips.get(selection).getName());
        int result = availableShips.get(selection).getSpecialAttack(t.getMap());
        if(result == 1) {
        	System.out.println("Succesful special attack!");
        	for(int i = 0; i < 10; i++) {
        		for(int j = 0; j < 10; j++) {
        			if(t.getMap().getSpace()[i][j].Hit()) {
        				hit = t.getMap().getSpace()[i][j].Item();
        			}
        		}
        	}            
        }
        else {
        	System.out.println("Unsuccesful special attack!");
        }
        hit = 0;
      }

      // if we hit a player, remove ship from list and update map that we hit the spot
      if (hit == 1){ // player 1
        addScore(turn, p1, p2);
        for(int i=0; i<p1.Ships().size(); i++){
          if (p1.Ships().get(i).getXPos() == x && p1.Ships().get(i).getYPos() == y){
            p1.Ships().remove(i);
            break;
          }
        }
      }
      else if (hit == 2){ // player 2
        addScore(turn, p1, p2);
        for(int i=0; i<p2.Ships().size(); i++){
          if (p2.Ships().get(i).getXPos() == x && p2.Ships().get(i).getYPos() == y){
            p2.Ships().remove(i);
            break;
          }
        }
      }
      else{ // non player
        ; // no need to remove ship
      }
      // update spot hit
      t.getMap().getSpace()[x][y].changeHit();

      // alternate player and repeat
      nextTurn();
    }

    if (p1.Ships().size() == 0){
      p2.addToWins();// player 2 won
    }
    else{
      p1.addToWins();// player 1 won
    }
  }

  public void addScore(int turn, Player p1, Player p2){
    if (turn == 0)
      p1.addToScore();
    else
      p2.addToScore();
  }

  private int chooseAttack(Player p, Scanner in){
    Boolean safeInput = false;
    int userInput = -1;
    System.out.println("Choose 1 for normal attack or 2 for special move\n");
    System.out.println("Choose 1 for now since special move has not been implemented\n");
    do{
      if (in.hasNextInt()){
        // read the player's input
        userInput = in.nextInt();

        // if it's something other than 1 or 2, redo
        if (userInput == 1 || userInput == 2)
          safeInput = true;
        // safe to continue
        else
          System.out.println("Please choose 1 or 2");
        
      }
      else{
        System.out.println("Please choose 1 or 2");
        in.nextLine();
      }
    } while(!safeInput);

    // return which attack
    return userInput;
  }

  // val holds 0 for x and 1 for y
  public int chooseXY(int val, Scanner in){
    int userInput = -1;
    // choose x
    do{
      if (val == 0)
        System.out.println("Pick x coordinate from 0-9");
      else  
        System.out.println("Pick y coordinate from 0-9");
      userInput = getUserInput(in);
    } while (userInput < 0 || userInput > 9);
    return userInput;
  }

  public int getUserInput(Scanner in){
    int num;
    while(!in.hasNextInt()){
      System.out.println("Please input a number\n");
      in.nextLine();
    }

    num = in.nextInt();
    return num;
  }
}


