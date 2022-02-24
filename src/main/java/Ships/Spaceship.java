package Ships;

import Map.Square;

public abstract class Spaceship {
	private String name;
	private int numberOfShots;
	private int x = 0; // ships current x position
	private int y = 0; // ships current y position
	private Square[][] position = new Square[x][y];
	private int health = 0;

	public abstract int getSpecialAttack();

	public void changeHealth(int modifier) {
		health += modifier;
	}

	public String getName() {
		return name;
	}

	public int getNumShots() {
		return numberOfShots;
	}

	public Square[][] getPosition() {
		return position;
	}


	public void setXPos(int modifier) { // set the x position of the ship
		position[x][y].changeOccupied(false); // old position no longer occupied
		int x = modifier;
		Square[][] nuPos = new Square[x][y];
		setPosition(nuPos);
		getPosition()[x][y].changeOccupied(true); // new position is occupied, update
	}

	public void setYPos(int modifier) { // set the y position of the ship
		position[x][y].changeOccupied(false); // old position no longer occupied
		int y = modifier;
		Square[][] nuPos = new Square[x][y];
		setPosition(nuPos);
		getPosition()[x][y].changeOccupied(true); // new position is occupied, update
	}

	public void setXYPos(int modX, int modY) {
		getPosition()[x][y].changeOccupied(false);
		x = modX;
		y = modY;
		Square[][] nuPos = new Square[x][y];
		setPosition(nuPos);
		nuPos[x][y].changeOccupied(true);
	}

	public int getXPos() {
		return x;
	}

	public int getYPos() {
		return y;
	}

	public void setPosition(Square[][] modifier) {
		position = modifier;
	}

	public void setName(String modifier) {
		name = modifier;
	}

	public void setNumberofShots(int modifier) {
		numberOfShots = modifier;


	}
}
