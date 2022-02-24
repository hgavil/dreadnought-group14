package Ships;

public class Dreadnought extends Spaceship {
    String name = "Ships.Dreadnought";
    int health = 4;
    int numberOfShots = 4;

	public int getSpecialAttack() {
		// Dreadnought activates its defense mechanisms and deploys a high durability armor that increases the ships health pool by 4...
		System.out.print("Activating armor plating!\n");
		health += 4;
		return 1; // cannot fail, always return 1
	}

    public String getName() {
        return name;
    }

}
