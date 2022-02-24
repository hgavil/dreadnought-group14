package Ships;

import Map.Square;

public class Cruiser extends Spaceship {
    String name = "Ships.Cruiser";
    int numberOfShots = 3;
    int health = 3;

	public int getSpecialAttack() {
		System.out.println("Cluster bombs away!\n");
		Square[][] attack = getPosition();
		for(int i = 0; i < 3; i++) { // checking x positions...
			for(int j = 0; i < 3; j++) { // checking y positions...
				if(attack[getXPos() + i][getYPos() + j].Hit() == false) { // checking for a hit
					attack[getXPos() + i][getYPos()+ j].changeHit();
					return 1; 
				}
			}
		}
		return 1;
	}

    public String getName() {
        return name;
    }


}
