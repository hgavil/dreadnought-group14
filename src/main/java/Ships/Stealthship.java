package Ships;

public class Stealthship extends Spaceship {
    String name = "Ships.Stealthship";
    int numberOfShots = 2;
    int health = 2;

	public int getSpecialAttack() {
		System.out.println("Reserve ammunition deployed!\n");
		numberOfShots += 4;
		return 1; // cannot fail, always return 1
	}
	
    public String getName() {
        return name;
    }


}
