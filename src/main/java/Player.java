import Ships.Spaceship;
import java.util.ArrayList;
import java.util.Vector;
import tools.Pair;


public class Player {

  private Integer name;
  private ArrayList<Spaceship> remainingShips;
  private Pair<Boolean, Vector<Vector<Integer>>> shots;
  private int score;

  public Player(Integer Name){
    this.name = Name;
    this.remainingShips = new ArrayList<Spaceship>();
  }

  // player shoots the given location
  boolean shoot(Vector<Vector<Integer>> location) {

    return true;
  }
  
  ArrayList<Spaceship> Ships() {
    return remainingShips;
  }

  public void addShip(Spaceship ship) {
    remainingShips.add(ship);
  }

  public Integer getName() {
      return name;
  }

  public Integer getScore() {
    return score;
  }

}
