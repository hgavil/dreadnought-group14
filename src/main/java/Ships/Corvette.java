package Ships;

import Map.Square;

public class Corvette extends Spaceship {
    String name = "Ships.Corvette";
    int numberOfShots = 1;
    int health = 2;

	//Corvette activates slip space thrusters for a one-time free 3 units of movement...
	public int getSpecialAttack() {
		System.out.println("Corvette thrusters are primed!\n");
		//Square[][] curPos = new Square[getXPos()][getYPos()];
		//curPos[getXPos()][getYPos()].changeOccupied(false); // old position no longer occupied
		//int val = getYPos();
		//val += 3;
		//if(val > 10) {
			//System.out.print("boost cannot be completed!\n");
			//curPos[getXPos()][getYPos()].changeOccupied(true); // move can't be completed, so old positon is stil occupied
			//return 0; // unsuccesful
		//}
		//setYPos(val); // update y position...
		//setPosition(curPos); // update positon
		//getPosition()[getXPos()][getYPos()].changeOccupied(true); // update new position to be occupied
		
		return 1; //successful
	}


}
