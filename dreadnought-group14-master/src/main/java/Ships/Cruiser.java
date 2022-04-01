package Ships;

import Map.Square;
import Map.Map;

public class Cruiser extends Spaceship {
    String name = "Ships.Cruiser";
    int numberOfShots = 3;
    int health = 3;

	public int getSpecialAttack(Map m) {
		System.out.println("Cluster bombs away!\n");
		for(int i = 1; i < 3; i++) { // checking x positions...
			for(int j = 1; i < 3; j++) { // checking y positions...
				if(getXPos()+i > 10 || getYPos()+j > 10) {
					System.out.println("Attack out of bounds, unsuccessful.. special attack refunded.");
					return 0;
				}
				if(m.getSpace()[getXPos() + i][getYPos() + j].Hit() == false && m.getSpace()[getXPos()+i][getYPos()].Item() > 0) { // checking for a hit
					m.getSpace()[getXPos() + i][getYPos()+ j].changeHit();
					setspecialUsed();
					return 1; 
				}
			}
		}
		return 0;
	}


    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getHealth(){
      return health;
    }

    @Override
    public void changeHealth() {
      health--;
    }
}
