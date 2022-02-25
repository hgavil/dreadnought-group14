import tools.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import Map.Terrain;
import Ships.Spaceship;

public class Round {
private List<Player> players;
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
 public String getWinner() {
	 int playerName = 0;
	 int Max = 0;
	 for(Player i : players) {
		 if(i.getScore() > Max) {
			 Max = i.getScore();
			 playerName = i.getName();
		 }
	 }
	 return("Winner Selected! Player: " + playerName + " is the winner with a score of " + Max);
 }

  public Round(Player p1, Player p2, Terrain t, Scanner in){
    turn = 0;
    int hit = 0;    // to store who we hit: player, nothing, or sprite
    int attack = 0; // which type of attack
    int x = -1;        // x and y of where we attacked
    int y = -1;
    //player 1/ turn 0 starts 

    while (p1.Ships().size() != 0 && p2.Ships().size() != 0){

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
        y = chooseXY(0, in);
        while (t.getMap().getSpace()[x][y].Hit()){
          System.out.println(x+", "+y+" has been hit, please choose another pair\n");
          x = chooseXY(0, in);
          y = chooseXY(0, in);
          // store what we hit
          hit = t.getMap().getSpace()[x][y].Item();
        }
      }
      else{
        System.out.println("Special move to be implemented\n");
        hit = 0;
      }

      // if we hit a player, remove ship from list and update map that we hit the spot
      if (hit == 1){ // player 1
        for(int i=0; i<p1.Ships().size(); i++){
          if (p1.Ships().get(i).getXPos() == x && p1.Ships().get(i).getYPos() == y){
            p1.Ships().remove(i);
            break;
          }
        }
      }
      else if (hit == 2){ // player 2
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
      // player 2 won
    }
    else{
      // player 1 won
    }
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
