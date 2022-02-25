package Ships;

import Map.Square;

public class Carrier extends Spaceship {
    String name = "Ships.Carrier";
    int numberOfShots = 3;
    int health = 4;

	public int getSpecialAttack() {

		//System.out.println("Ion beam is fully charged!\n");
		//Square[][] attack = getPosition();
		//for(int i = 1; i <= 5; i++) { // beam targets the next 5 squares in the direction that the ships x position was facing
			//if(attack[getXPos()+i][getYPos()].Hit() == false) { // checking for a hit
				//attack[getXPos()+i][getYPos()].changeHit(); // hit 
				//return 1; // successful attack
			//}
		//}		
		return 0; // unsucessful attack
	}
    
    public String getName() {
        return name;
    }


}
