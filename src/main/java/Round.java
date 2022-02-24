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
}
