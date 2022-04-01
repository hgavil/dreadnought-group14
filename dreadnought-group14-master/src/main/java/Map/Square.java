package Map;

public class Square {
    // occupied will hold if this spot has something
    private boolean occupied;
    // item will hold player id, or -1 is it's a sprite
    private int item;
    // if this spot has been hit before
    private boolean hit;

    public Square(){
        occupied = false;
        item = 0;
        hit = false;
    }

    // return status of occupied
    public boolean Occupied(){
        return occupied;
    }

    // changes occupied to value given
    public void changeOccupied(boolean val){
        occupied = val;
    }

    // returns player id or -1 if sprite
    public int Item (){
        return item;
    }

    // change item number to player id or sprite(-1)
    public void changeItem(int val){
        item = val;
    }

    // return status of hit
    public boolean Hit(){
        return hit;
    }

    // change value of hit to true
    public void changeHit(){
        hit = true;
    }
}