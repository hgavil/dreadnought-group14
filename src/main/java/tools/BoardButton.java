package tools;
import javafx.scene.control.Button;

public class BoardButton extends Button{
	private int row, col, p, theme;
	private Boolean clicked = false;
  int btnDimension = 70;
	
	public BoardButton(int i, int j) {
		Button b = new Button();
		setStyle("-fx-color: lightgray;"+"-fx-pref-width: "+btnDimension+";"+"-fx-pref-height: "+btnDimension+";");
		row = i;
		col = j;
		p = -1; // -1 until it is clicked
		theme = 0;
		
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	Boolean isClicked() {
		return clicked;
	}
	
	// clicked button and don't need to change it
	void setClicked(int player) {
		clicked = true;
		setPlayer(player);
	}
	
	void unclick() {
		clicked = false;
		p = -1;
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
    0 darkblue - unkown
  */
	void setColor(int num) {
		if (theme == 4)
      setStyle("-fx-color: red;"+"-fx-pref-width: "+btnDimension+";"+"-fx-pref-height: "+btnDimension+";");
    else if (num == 3)
      setStyle("-fx-color: orange;"+"-fx-pref-width: "+btnDimension+";"+"-fx-pref-height: "+btnDimension+";");
    else if (num == 2)
      setStyle("-fx-color: green;"+"-fx-pref-width: "+btnDimension+";"+"-fx-pref-height: "+btnDimension+";");
    else if (num == 1)
      setStyle("-fx-color: black;"+"-fx-pref-width: "+btnDimension+";"+"-fx-pref-height: "+btnDimension+";");
    else
      setStyle("-fx-color: #00008b;"+"-fx-pref-width: "+btnDimension+";"+"-fx-pref-height: "+btnDimension+";");
	}
}
