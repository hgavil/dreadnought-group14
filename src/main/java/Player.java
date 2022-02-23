import Ships.Spaceship;
import java.util.List;
import java.util.Vector;
import tools.Pair;


public class Player {

    private String name;
    private List<Spaceship> remainingShips;
    private Pair<Boolean, Vector<Vector<Integer>>> shots;
    private int score;


    // player shoots the given location
    boolean shoot(Vector<Vector<Integer>> location) {
        return true;
    }

}
