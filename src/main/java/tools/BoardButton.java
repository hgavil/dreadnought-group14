package tools;
import javafx.scene.control.Button;

public class BoardButton extends Button{
	private int row, col, p, theme;
	private Boolean clicked = false;
  String btnWidth = "-fx-min-width: "+ 70 +";";
  String btnHeight = "-fx-min-height: "+ 70 +";";
  Button b;
  int health;
	
	public BoardButton(int i, int j) {
		b = new Button();
		row = i;
		col = j;
		p = -1; // -1 until it is clicked
		theme = 0;
		setTheme();
	}

  public void disable(){
    setDisable(true);
  }

  public void enable(){
    setDisable(false);
  }

  public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public int health(){
    return health;
  }

  public void gotHit(){
    health--;
  }
	
	// get which player clicked this button
	public int player() {
		return p;
	}
	
	public void setPlayer(int player) {
		p = player;
	}
	
  /* 
    4 red     - hit and dead
    3 orange  - hit but not dead
    2 green   - hit but sprite
    1 black   - hit but nothing here
    0         - unkown, base color
  */
  public void changeTheme(int val){
    theme = val;
    setTheme();
  }

	void setTheme() {
		if (theme == 4)
      setStyle("-fx-color: red;"+btnWidth+btnHeight);
    else if (theme == 3)
      setStyle("-fx-color: orange;"+btnWidth+btnHeight);
    else if (theme == 2)
      setStyle("-fx-color: green;"+btnWidth+btnHeight);
    else if (theme == 1)
      setStyle("-fx-color: black;"+btnWidth+btnHeight);
    else
      setStyle("-fx-color: lightgray;"+btnWidth+btnHeight);
	}
}
