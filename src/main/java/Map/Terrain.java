package Map;

import Map.Map;
import Ships.Spaceship;

import java.util.ArrayList;

public class Terrain {
    ArrayList<Spaceship> spaceships;
    Map map;

    public Terrain() {
        this.spaceships = new ArrayList<>();
        this.map = new Map();
    }
}