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
    boolean Occupied(){
        return occupied;
    }

    // changes occupied to value given
    void changeOccupied(boolean val){
        occupied = val;
    }

    // returns player id or -1 if sprite
    int Item (){
        return item;
    }

    // change item number to player id or sprite(-1)
    void changeItem(int val){
        item = val;
    }

    // return status of hit
    boolean Hit(){
        return hit;
    }

    // change value of hit to true
    void changeHit(){
        hit = true;
    }
}