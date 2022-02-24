import tools.Pair;

import java.util.List;
import java.util.Scanner;
import java.util.Vector;

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

  public Round(Player p1, Player p2, Scanner in){
    turn = 0;
    //player 1/ turn 0 starts 

    while (p1.Ships().size() != 0 && p2.Ships().size() != 0){

      // player chooses to attack or do a special move
      // player 1
      if (turn == 0){
        chooseAttack(p1, in);
      }
      else { // player 2
        ;
      }

      // alternate and repeat
      nextTurn();
    }
  }

  private Boolean chooseAttack(Player p, Scanner in){
    Boolean safeInput = false;
    int userInput = -1;
    System.out.println("Choose 1 for normal attack or 2 for special move\n");
    do{
      if (in.hasNextInt()){
        // read the player's input
        userInput = in.nextInt();

        // if it's something other than 1 or 2, continue
        if (userInput != 1 && userInput != 2)
          continue;
        
        ;
      }
      else{
        System.out.println("Please choose 1 or 2");
        in.nextLine();
      }
    } while(!safeInput);
    return false;
  }
}
