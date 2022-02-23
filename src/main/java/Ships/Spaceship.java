package Ships;

import java.util.Vector;

public abstract class Spaceship {
    private String name;
    private int numberOfShots;
    private int health;
    private Vector position;

    public abstract int getSpecialAttack();

    public void changeHealth(int modifier) {
        health += modifier;
    }

    public String getName() {
        return name;
    }

    public void setNumberofShots(int modifier) {
        numberOfShots = modifier;
    }

}