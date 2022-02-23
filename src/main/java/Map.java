import java.util.Random;
import java.util.concurrent.*;

public class Map {
    int SIZE = 10; // size of map 10x10x10

    Square[][][] space;

    public Map(){
        space = new Square[SIZE][SIZE][SIZE];
        // generate 100 random sprites
        int rNum1, rNum2, rNum3;
        int i = 0;
        while (i < 100){
            // nextInt is normally exclusive of the top value,
            // so add 1 to make it inclusive
            // ThreadLocalRandom.current().nextInt(min, max + 1)
            rNum1 = ThreadLocalRandom.current().nextInt(0, 11);
            rNum2 = ThreadLocalRandom.current().nextInt(0, 11);
            rNum3 = ThreadLocalRandom.current().nextInt(0, 11);

            if (space[rNum1][rNum2][rNum3].Occupied() == false){
                space[rNum1][rNum2][rNum3].changeOccupied(true);
                space[rNum1][rNum2][rNum3].changeItem(-1);
                i = i+1;
            }
            // else repeat to fill up 100 squares
        }
    }
}

