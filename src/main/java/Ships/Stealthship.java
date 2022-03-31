package Ships;
import Map.Map;
public class Stealthship extends Spaceship {
    String name = "Ships.Stealthship";
    int numberOfShots = 2;
    int health = 2;

	public int getSpecialAttack(Map m) {
		System.out.println("Reserve ammunition deployed!\n");
		numberOfShots += 4;
		return 1; // cannot fail, always return 1
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
