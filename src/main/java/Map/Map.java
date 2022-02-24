package Map;

import java.util.Random;
import java.util.concurrent.*;

public class Map {
  int SIZE = 10; // size of map 10x10x10

  Square[][] space;
  // Square[][][] space

  /*
  public Map(){
    space = new Square[SIZE][SIZE][SIZE];
    int i;

    for(i=0; i<10; i++){
      for(int j=0; j<10; j++){
        for(int z=0; z<10; z++){
          space[i][j][z] = new Square();
        }
      }
    }

    // generate 100 random sprites
    int rNum1, rNum2, rNum3;
    i = 0;

    // fill up the board with rocks
    while (i < 100){
      // nextInt is normally exclusive of the top value,
      // so add 1 to make it inclusive
      // ThreadLocalRandom.current().nextInt(min, max + 1)
      rNum1 = ThreadLocalRandom.current().nextInt(0, 10);
      rNum2 = ThreadLocalRandom.current().nextInt(0, 10);
      rNum3 = ThreadLocalRandom.current().nextInt(0, 10);

      if (space[rNum1][rNum2].Occupied() == false){
        space[rNum1][rNum2].changeOccupied(true);
        space[rNum1][rNum2].changeItem(-1);
        i = i+1;
      }
      // else repeat to fill up 100 squares
    }
  }
  */

  // modified to be 2D
  public Map(){
    space = new Square[SIZE][SIZE];
    int i;
    
    for(i=0; i<10; i++){
      for(int j=0; j<10; j++){
        space[i][j] = new Square();
      }
    }

    // generate 100 random sprites
    int rNum1, rNum2;
    i = 0;
    while (i < 10){
      // nextInt is normally exclusive of the top value,
      // so add 1 to make it inclusive
      // ThreadLocalRandom.current().nextInt(min, max + 1)
      rNum1 = ThreadLocalRandom.current().nextInt(0, 10);
      rNum2 = ThreadLocalRandom.current().nextInt(0, 10);

      if (space[rNum1][rNum2].Occupied() == false){
        space[rNum1][rNum2].changeOccupied(true);
        space[rNum1][rNum2].changeItem(-1);
        i = i+1;
      }
      // else repeat to fill up 10 squares
    }
  }

  // show 2D board using basic ASCII character
  // '|', '-': wall
  // a number shows which player
  // 'S': sprite
  // an empty space means theres nothing
  // ----------
  // |1|0|
  void show2D(){
    for (int i=0; i<12; i++){
      ;
    }
  }

}

