package Ships;

import Map.Square;
import Map.Map;

public class Cruiser extends Spaceship {
    String name = "Ships.Cruiser";
    int numberOfShots = 3;
    int health = 3;

	public int getSpecialAttack(Map m) {
		System.out.println("Cluster bombs away!\n");
		for(int i = 0; i < 3; i++) { // checking x positions...
			for(int j = 0; i < 3; j++) { // checking y positions...
				if(m.getSpace()[getXPos() + i][getYPos() + j].Hit() == false) { // checking for a hit
					m.getSpace()[getXPos() + i][getYPos()+ j].changeHit();
					return 1; 
				}
			}
		}
		return 0;
	}

    public String getName() {
        return name;
    }


}
