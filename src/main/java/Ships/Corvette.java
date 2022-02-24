package Ships;

public class Corvette extends Spaceship {
    String name = "Ships.Corvette";
    int numberOfShots = 1;
    int health = 2;

	//Corvette activates slip space thrusters for a one-time free 3 units of movement...
	public int getSpecialAttack() {
		System.out.println("Corvette thrusters are primed!\n");
		Square[][][] curPos = new Square[getXPos()][getYPos()][0];
		int val = getYPos();
		val += 3;
		if(val > 10) {
			System.out.print("boost cannot be completed!\n");
			return 0; // unsuccesful
		}
		setYPos(val);
		return 1; //successful
	}
    public String getName() {
        return name;
    }


}
