package Ships;

import Map.Square;
import Map.Map;
public class Corvette extends Spaceship {
    String name = "Ships.Corvette";
    int numberOfShots = 1;
    int health = 2;

	//Corvette activates slip space thrusters for a one-time free 3 units of movement...
	public int getSpecialAttack(Map m) {
		System.out.println("Corvette thrusters are primed!\n");
		if(getXPos()+3 > 10 || getYPos()+3 > 10) {
			System.out.println("Move cannot be completed, distance traveled would exceed board space");
			return 0;
		}
		if(m.getSpace()[getXPos()+3][getYPos()+3].Occupied() == true) {
			System.out.println("special attack cannot be completed, returning");
			return 0;
		}
		m.getSpace()[getXPos()][getYPos()].changeOccupied(false);
		m.getSpace()[getXPos()][getYPos()].changeItem(0);
		setXYPos(getXPos()+3,getYPos()+3);
		m.getSpace()[getXPos()][getYPos()].changeOccupied(true);
		m.getSpace()[getXPos()][getYPos()].changeItem(getOwner());
		// move can be completed, so do so.
		setspecialUsed();
		return 1; //successful
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
