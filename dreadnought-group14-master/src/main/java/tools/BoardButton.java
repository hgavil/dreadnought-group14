package tools;
import javafx.scene.control.Button;

public class BoardButton extends Button{
	private int row, col, p, theme;
	private Boolean clicked;
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
    clicked = false;
	}

  /*
    1       - corvette
    2       - cruiser
    3       - dreadnought
    4       - stealthship
    default - carrier
  */
  public BoardButton(int ship){
    b = new Button();
    switch (ship){
      case 1: 
        setText("Corvette");
        break;
      case 2:
        setText("Cruiser");
        break;
      case 3:
        setText("Dreadnought");
        break;
      case 4:
        setText("Stealthship");
        break;
      default:
        setText("Carrier");
        break;
    };
		theme = 5;
		setTheme();
    clicked = false;
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
    else if (theme == 0)
      setStyle("-fx-color: lightgray;"+btnWidth+btnHeight);
    else
    setStyle("-fx-color: lightgray; -fx-min-width: 100; -fx-min-height: 50");
	}

  // 
  public Boolean Clicked() {
		return clicked;
	}
	
	public void unclick() {
		clicked = false;
	}
}
