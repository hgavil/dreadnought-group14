package Ships;

import Map.Square;

import Map.Map;

public abstract class Spaceship {
	private String name;
	private int numberOfShots;
	private int x = 0; // ships current x position
	private int y = 0; // ships current y position
	private int health = 0;
	private int owner = 0;
	private boolean specialUsed = false;
	public abstract int getSpecialAttack(Map m);
	
	public void changeHealth() {
		health += 1;
	}

  public int getHealth(){
    return health;
  }
	
	public String getName() {
		return name;
	}
	
	public int getNumShots() {
		return numberOfShots;
	}
		
	public void setXPos(int modifier) { // set the x position of the ship

		this.x = modifier;
	}
	
	public void setYPos(int modifier) { // set the y position of the ship
		this.y = modifier;

	}
	
	public void setXYPos(int modX,int modY) {
		this.x = modX;
		this.y = modY;

	}
	public void setspecialUsed() {
		this.specialUsed = true;
	}
	
	public int getXPos() {
		return x;
	}
	
	public int getYPos() {
		return y;
	}

	public void setName(String modifier) {
		name = modifier;
	}
	
	public void setNumberofShots(int modifier) {
		numberOfShots = modifier;
         }
	
	public void setOwner(int modifier){
		this.owner = modifier;
	}
	
	public int getOwner(){
		return owner;
	}
	
	public boolean getspecialUsed() {
		return specialUsed;
	}
}
