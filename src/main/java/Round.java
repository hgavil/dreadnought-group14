import tools.Pair;

import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import Map.Terrain;

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
	 String playerName = " ";
	 int Max = 0;
	 for(Player i : players) {
		// if(i.score ) need a getter to get player's name and score
	 }
	 return("Winner Selected");
 }

  public Round(Player p1, Player p2, Terrain t, Scanner in){
    turn = 0;
    //player 1/ turn 0 starts 

    while (p1.Ships().size() != 0 && p2.Ships().size() != 0){

      // player chooses to attack or do a special move
      // player 1
      if (turn == 0){
        chooseAttack(p1, t, in);
      }
      else { // player 2
        ;
      }

      // alternate and repeat
      nextTurn();
    }
  }

  private Boolean chooseAttack(Player p, Terrain t, Scanner in){
    Boolean safeInput = false;
    int userInput = -1;
    int x, y;
    System.out.println("Choose 1 for normal attack or 2 for special move\n");
    System.out.println("Choose 1 for now since special move has not been implemented\n");
    do{
      if (in.hasNextInt()){
        // read the player's input
        userInput = in.nextInt();

        // if it's something other than 1 or 2, continue
        if (userInput != 1 && userInput != 2)
          continue;
        
        if(userInput == 1){
          do{
            x = chooseXY(0, in);
            y = chooseXY(0, in);

          } while (t.map.space[x][y].Occupied())
        }
        else{
          System.out.println("Special move to be implemented\n");
        }
      }
      else{
        System.out.println("Please choose 1 or 2");
        in.nextLine();
      }
    } while(!safeInput);
    return false;
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
