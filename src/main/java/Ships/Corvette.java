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
		System.out.println("Corvette thrusters are primed!\n");
		if(getXPos()+3 > 10 || getYPos()+3 > 10) {
			System.out.println("Move cannot be completed, distance traveled would exceed board space");
			return 0;
		}
		setXYPos(getXPos()+3,getYPos()+3);
		// move can be completed, so do so.
		return 1; //successful
	}


}
