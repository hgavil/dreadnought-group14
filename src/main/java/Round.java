import tools.Pair;

import java.util.List;
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

  public Round(Player p1, Player p2){
    turn = 0;
    //player 1/ turn 0 starts 

    while (p1.Ships().size() != 0 && p2.Ships().size() != 0){

      // player chooses to attack or do a special move
      // player 1
      if (turn == 0){
        chooseAttack(p1);
      }
      else { // player 2
        ;
      }

      // alternate and repeat
      nextTurn();
    }
  }

  private Boolean chooseAttack(Player p){
    Boolean safeInput = false;
    do{
      System.out.println("Choose 1 for normal attack or 2 for special move\n");
    } while(!safeInput);
    return false;
  }
}
