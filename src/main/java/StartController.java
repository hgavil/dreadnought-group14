
import Map.Square;
import Map.Terrain;
import Ships.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import tools.BoardButton;
import tools.SceneBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class StartController {

    static int count = 0;
    static int winner = 1;

    // game variables
    static Game game;
    static Player p1, p2;
    static Terrain gameMap;
    static BoardButton[][] buttonGrid;
    static BoardButton[][] specialAttackButtons = new BoardButton[2][3]; // [players][ships/player]
    enum ModeSettings {SETUP, INGAME};
    static ModeSettings currentMode;
    static Player currentPlayer;
    static Round match;
    static GridPane gameGrid;
    static Stage stage;
    static GridPane playerOneShips;
    static GridPane playerTwoShips;

    public Button exitButton;
    public Text gameEndText = new Text();
    public boolean isGameEnd = false;

    static HashMap<String, Scene> sceneMap = new HashMap<String,Scene>();
    static SceneBuilder sceneBuilder;

    static Text directions;
    static Text playerid;

    static TextArea gameLog;
    static Text currentTurnText;

    public void setTools(HashMap<String, Scene> sceneMap, SceneBuilder sceneBuilder) {
        this.sceneMap = sceneMap;
        this.sceneBuilder = sceneBuilder;
        buttonGrid = new BoardButton[10][10];
    }

    static Spaceship selectingShip;


    @FXML // SHIP SELECTION BUTTONS -- used during ship selection process
    protected Button carrierbutton, corvettebutton, cruiserbutton, dreadnoughtbutton, stealthshipbutton;

    @FXML
    protected void onStartButton(ActionEvent event) throws IOException {
        // begin the game setup

        // create game instance for the game to begin
        this.game = new Game();

        // create player instances
        this.p1 = new Player(1);
        this.p2 = new Player(2);

        // create map
        this.gameMap = new Terrain();

        // change scene
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        FXMLLoader selectShipLoader = new FXMLLoader(Main.class.getResource("selectvbox.fxml"));
        Parent selectShipsPane = selectShipLoader.load();

        gameGrid = createShipBoard();
        VBox shipSelectorVBox = createShipSelectorVBox(selectShipsPane);
        disableGameGrid();

        Scene selectShips = sceneBuilder.selectShipScene(shipSelectorVBox, gameGrid, new GridPane());
        sceneMap.put("ships", selectShips);

        FXMLLoader endScreenLoader = new FXMLLoader(Main.class.getResource("endScreen.fxml"));
        Parent endScreenParent = endScreenLoader.load();
        Scene endScene = new Scene(endScreenParent);
        sceneMap.put("end", endScene);


        stage.setScene(sceneMap.get("ships"));
        stage.centerOnScreen();

        currentMode = ModeSettings.SETUP;
        currentPlayer = p1;
    }

    private void showEndScreen() throws IOException {

//        FXMLLoader endScreenLoader = new FXMLLoader(Main.class.getResource("endScreen.fxml"));
//        Parent endScreenParent = endScreenLoader.load();
//        Scene endScene = new Scene(endScreenParent);
        stage.setScene(sceneMap.get("end"));
        stage.centerOnScreen();
    }

    // SHIP SELECTION //
    // 1. create the ship and assign its properties
    // 2. disable the ship selection buttons and enable the grid buttons

    @FXML
    protected void onCarrier() {
        Spaceship ship = new Carrier();
        ship.setName("Carrier");

        // assign the ship to the correct player
        if (currentPlayer == p1) {
            ship.setOwner(this.p1.getName());
        } else ship.setOwner(this.p2.getName());

        selectingShip = ship;
        directions.setText("Current Ship: Carrier");

        enableSetupGameGrid();
    }

    @FXML
    protected void onCorvette() {
        Spaceship ship = new Corvette();
        ship.setName("Corvette");

        // assign the ship to the correct player
        if (currentPlayer == p1) {
            ship.setOwner(p1.getName());
        } else ship.setOwner(p2.getName());

        selectingShip = ship;
        directions.setText("Current Ship: Corvette");

        enableSetupGameGrid();
    }

    @FXML
    protected void onCruiser() {
        Spaceship ship = new Cruiser();
        ship.setName("Cruiser");

        // assign the ship to the correct player
        if (currentPlayer == p1) {
            ship.setOwner(p1.getName());
        } else ship.setOwner(p2.getName());

        selectingShip = ship;
        directions.setText("Current Ship: Cruiser");

        enableSetupGameGrid();

    }

    @FXML
    protected void onDreadnought() {
        Spaceship ship = new Dreadnought();
        ship.setName("Dreadnought");

        // assign the ship to the correct player
        if (currentPlayer == p1) {
            ship.setOwner(p1.getName());
        } else ship.setOwner(p2.getName());

        selectingShip = ship;
        directions.setText("Current Ship: Dreadnought");

        enableSetupGameGrid();

    }

    @FXML
    protected void onStealthship() {
        Spaceship ship = new Stealthship();
        ship.setName("Stealthship");

        // assign the ship to the correct player
        if (currentPlayer == p1) {
            ship.setOwner(p1.getName());
        } else ship.setOwner(p2.getName());

        selectingShip = ship;
        directions.setText("Current Ship: Stealthship");

        enableSetupGameGrid();

    }



    private void disableGameGrid() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttonGrid[i][j].disable();
            }
        }
    }

    private void enableSetupGameGrid() {
      Square mapSpace;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
              mapSpace = gameMap.getMap().getSpace()[buttonGrid[i][j].getRow()][buttonGrid[i][j].getCol()];
              if (mapSpace.Occupied())
                ; // keep disabled buttons disabled
              else
                buttonGrid[i][j].enable();
            }
        }
    }

    private void enableGameGrid() {
      for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
            buttonGrid[i][j].enable();
        }
      }
    }


    private GridPane createShipBoard() {

        // event handler for gamegrid buttons
        EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                BoardButton button = (BoardButton) e.getSource(); // get the current button
                Square mapSpace = gameMap.getMap().getSpace()[button.getRow()][button.getCol()];

                // HANDLING DURING SETUP:
                if (currentMode == ModeSettings.SETUP) {
                    // if the space isn't occupied,
                    if (!mapSpace.Occupied()) {
                        // set the ship's coordinates to this location
                        selectingShip.setXYPos(button.getRow(), button.getCol());
                        // give the player the ship
                        currentPlayer.addShip(selectingShip);
                        // change the map space to be occupied by that player
                        mapSpace.changeItem(currentPlayer.getName());
                        // is now occupied
                        mapSpace.changeOccupied(true);

                        // check if the current player has selected all of their ships
                        if (currentPlayer.Ships().size() < 3) {
                            // if they haven't, disable the game grid and let them choose another ship
                            disableGameGrid();
                            directions.setText("Ship placed.");
                        }
                        else {
                            // if they have, first check if it was player 1 or player 2
                            if (currentPlayer == p1) {
                                // if it's player 1, move on to player 2
                                currentPlayer = p2;
                                directions.setText("Player 2, pick your ships!");

                                disableGameGrid();
                            }
                            else {
                                // if it was player 2, start the game
                                
                                // enable all buttons
                                enableGameGrid();

                                // change the scene
                                gameGrid = createGameBoard();
                                GridPane playerShips = createPlayerShips();
                                VBox gameLogVBox = createGameLog();
                                stage.setScene(sceneMap.get("one"));
                                Scene mainGame = sceneBuilder.selectShipScene(gameLogVBox, gameGrid, playerShips);
                                sceneMap.put("main", mainGame);
                                stage.setScene(sceneMap.get("main"));

                                currentMode = ModeSettings.INGAME;
                                currentPlayer = p1;

//                                // call a new round
//                                /*temporary*/Scanner in = new Scanner(System.in);
//                                /*temporary*/match = new Round(p1, p2, gameMap, in);
//                                /*uncomment when round is reworked*/ //match = new Round(p1, p2, gameMap);
//                                match.getWinner();
                            }
                        }
                    }
                    else {
                        directions.setText("Space occupied! Try again.");
                    }
                }

                // HANDLING DURING INGAME:
                else if (currentMode == ModeSettings.INGAME) {

                }

            }
        };

        GridPane board = new GridPane();
        board.setPadding(new Insets(40));
        board.setHgap(10);
        board.setVgap(10);

        int i,j=0;
        for (i=0; i<10; i++) {
            for (j=0; j<10; j++) {
                BoardButton b = new BoardButton(i, j);
                b.setOnAction(buttonHandler);
                buttonGrid[i][j] = b;
                board.add(b, j, i);
            }
        }



        return board;
    }

    private GridPane createGameBoard() {
      // event handler for createGameLog buttons
      EventHandler<ActionEvent> gameButtonHandler = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e) {
          BoardButton button = (BoardButton) e.getSource(); // get the current button
          int row = button.getRow();
          int col = button.getCol();
          BoardButton b = buttonGrid[row][col];
          boolean hitThemselves = false;

          hitThemselves = attackPosition(row, col, b);
          // keep same person
          if (hitThemselves)
            return;
          
          // alternate players everytime they dont try to hit themselves
          if (currentPlayer.getName() == 1){
            currentPlayer = p2;
            playerid.setText("2");
            // keep buttons that are used disabled
            for (int i=0; i<specialAttackButtons[1].length; i++){
              // disable if used
              if (Boolean.TRUE.equals(specialAttackButtons[0][i].Clicked()))
                specialAttackButtons[1][i].setDisable(true);
            	else {
                specialAttackButtons[1][i].enable();
            	}
              specialAttackButtons[0][i].disable();
            }

            highlight(p2, p1, buttonGrid);
          }
          else{
            currentPlayer = p1;
            playerid.setText("1");
            // keep buttons that are used disabled
            for (int i=0; i<specialAttackButtons[0].length; i++){
              // disable if used
              if (Boolean.TRUE.equals(specialAttackButtons[0][i].Clicked()))
                specialAttackButtons[0][i].setDisable(true);
            	else {
                specialAttackButtons[0][i].enable();
            	}
              specialAttackButtons[1][i].disable();
            }

            highlight(p1, p2, buttonGrid);
          }
        }
      };
      
      GridPane board = new GridPane();
      board.setPadding(new Insets(40));
      board.setHgap(10);
      board.setVgap(10);

      int i,j=0;
      for (i=0; i<10; i++) {
        for (j=0; j<10; j++) {
          BoardButton b = new BoardButton(i, j);
          b.setOnAction(gameButtonHandler);
          buttonGrid[i][j] = b;
          board.add(b, j, i);
        }
      }

      highlight(p1, p2, buttonGrid);
           
      return board;
    } // end of createGameBoard


    // try to attackk a certain position, if the position has the oen players id return false
    private boolean attackPosition(int row, int col, BoardButton b){
      Square mapSpace = gameMap.getMap().getSpace()[row][col];
      boolean hitThemselves = false;
      
      // current space is not occupied
      if (!mapSpace.Occupied()) {
        b.changeTheme(1);
        b.disable();
      }
      // hit but a sprite
      else if (mapSpace.Item() == -1) {
        b.changeTheme(2);
        b.disable();
      }

      // hit a player
      // would hit themselves
      else if (mapSpace.Item() == currentPlayer.getName()){
        gameLog.appendText("\nYou are here, please select a different spot");
        hitThemselves = true;
      }
      // player 1 shot
      else if (currentPlayer.getName() == 1) {
        // cheack other player ships to match with coordinates
        for (int i=0; i<p2.Ships().size(); i++){
          if (p2.Ships().get(i).getXPos() == row && p2.Ships().get(i).getYPos() == col){
            // remove one health
            p2.Ships().get(i).changeHealth();
            // System.out.println("Health of ship is now:"+p2.Ships().get(i).getHealth());

            // if health == 0, remove
            if (p2.Ships().get(i).getHealth() == 0){
              p2.Ships().remove(i);
              b.changeTheme(4);
              b.disable();
            }
            else
              b.changeTheme(3); // hit but not dead
          }
        }
      }
      // player 2 shot
      else {
        // cheack other player ships to match with coordinates
        for (int i=0; i<p1.Ships().size(); i++){
          if (p1.Ships().get(i).getXPos() == row && p1.Ships().get(i).getYPos() == col){
            // remove one health
            p1.Ships().get(i).changeHealth();
            // System.out.println("Health of ship is now:"+p1.Ships().get(i).getHealth());

            // if health == 0, remove
            if (p1.Ships().get(i).getHealth() == 0){
              p1.Ships().remove(i);
              b.changeTheme(4);
              b.disable();
            }
            else
              b.changeTheme(3); // hit but not dead
          }
        }
      }
      // end of hit player

        // check if either player lost
        // player 1 lost
        if (p1.Ships().size() == 0)
        {
            winner = 2;
            gameEndText.setText("The game has ended. Player 2 wins!");
            isGameEnd = true;
//              java.lang.System.exit(0);
        }
        // player 2 lost
        else if (p2.Ships().size() == 0)
        {
            winner = 1;
            gameEndText.setText("The game has ended. Player 1 wins!");
            isGameEnd = true;
//              java.lang.System.exit(0);
        }

        // if the game has ended then display the game end screen
        if (isGameEnd) {
            try {
                showEndScreen();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
      return hitThemselves;
    }

    // gridpane of player 1 ships in terms of buttons
    private GridPane createPlayerShips(){
      GridPane ships = new GridPane();
      String name;
      BoardButton s;
      int i;
      ships.setPadding(new Insets(40));
      ships.setHgap(10);
      ships.setVgap(10);
      Pane pane = new Pane();
      pane.setStyle("-fx-min-height: 70;");
      Label lp1 = new Label("Player 1 Ships");
      Label lp2 = new Label("Player 2 Ships");

      ships.add(lp1, 0, 0);
      for (i=0; i<p1.Ships().size(); i++){
        name = p1.Ships().get(i).getName();
        if ("Ships.Carrier".equals(name))
          s = new BoardButton(0);
        else if ("Ships.Corvette".equals(name))
          s = new BoardButton(1);
        else if ("Ships.Cruiser".equals(name))
          s = new BoardButton(2);
        else if ("Ships.Dreadnought".equals(name))
          s = new BoardButton(3);
        else if ("Ships.Stealthship".equals(name))
          s = new BoardButton(4);
        else{
          s = new BoardButton(0);
          System.out.println("Something went wrong with ship buttons");
        }
        specialAttackButtons[0][i] = s;
        ships.add(s, 0, i+1);
        if ("Ships.Carrier".equals(name))
            specialAttackButtons[0][i].setOnAction((event) -> { // lambda event handler for player 1's carrier ship
            	System.out.println("carrier clicked");
            });
        else if ("Ships.Corvette".equals(name))
            specialAttackButtons[0][i].setOnAction((event) -> { // lambda event handler for player 1's corvette ship
            int j = 0;
            for(Ships.Spaceship e : p1.Ships()) {
             if("Ships.Corvette".equals(e.getName())) {
            	 if(e.getspecialUsed()) {
     				gameLog.clear();
     				gameLog.appendText("special used for this ship already!");
     				specialAttackButtons[0][j].setDisable(true);
            	 }
            	 else {
            		gameLog.clear();
            		gameLog.appendText("Corvette thrusters are primed and ready!");
            		if(e.getXPos() + 3 >= 10) { // checking for niche case where x axis cannot add 3 but y axis can
            			if(e.getYPos() + 3 >= 10) {
            				gameLog.clear();
            				gameLog.appendText("special attack cannot be completed, not enough space present");
            				e.setspecialUsed();
            				specialAttackButtons[0][j].setDisable(true);
            			}
            			else {
            				System.out.println("Y axis can recieve special attack");
            				gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeOccupied(false);
            				gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeItem(0);
            				System.out.println(e.getXPos());
            				System.out.println(e.getYPos());
            				e.setXYPos(e.getXPos(), e.getYPos()+ 3);
            				gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeOccupied(true);
            				gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeItem(e.getOwner());
            				e.setspecialUsed();
            				specialAttackButtons[0][j].setDisable(true);
            				System.out.println(e.getXPos());
            				System.out.println(e.getYPos());
            				break;
            			}
            		}
            		else if(e.getYPos() + 3 >= 10) { // checking for niche case where y axis cannot add 3 but x axis can
            			if(e.getXPos() + 3 >= 10) {
            				gameLog.clear();
            				gameLog.appendText("special attack cannot be completed, not enough space present");
            				e.setspecialUsed();
            				specialAttackButtons[0][j].setDisable(true);
            			}
            			else {
            				System.out.println("X axis can recieve special attack");
            				gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeOccupied(false);
            				gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeItem(0);
            				System.out.println(e.getXPos());
            				System.out.println(e.getYPos());
            				e.setXYPos(e.getXPos()+3, e.getYPos());
            				gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeOccupied(true);
            				gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeItem(e.getOwner());
            				e.setspecialUsed();
            				specialAttackButtons[0][j].setDisable(true);
            				System.out.println(e.getXPos());
            				System.out.println(e.getYPos());
            				break;
            			}
            		}
            		else { // if we got here, that means both X and Y can add 3 to them...
        				System.out.println("both axis can recieve special attack");
            			gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeOccupied(false);
        				gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeItem(0);
        				System.out.println(e.getXPos());
        				System.out.println(e.getYPos());
        				e.setXYPos(e.getXPos()+3, e.getYPos()+3);
        				gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeOccupied(true);
        				gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeItem(e.getOwner());
        				e.setspecialUsed();
        				specialAttackButtons[0][j].setDisable(true);
        				System.out.println(e.getXPos());
        				System.out.println(e.getYPos());
        				break;
            		}
            	 }
             }
             j++;
             }
             });
          else if ("Ships.Cruiser".equals(name))
              specialAttackButtons[0][i].setOnAction((event) -> { // lambda event handler for player 1's cruiser ship
              	System.out.println("cruiser clicked");
              });
          else if ("Ships.Dreadnought".equals(name))
              specialAttackButtons[0][i].setOnAction((event) -> { // lambda event handler for player 1's dreadnought ship
              	//System.out.println("dreadnought clicked");
            	 int j = 0;
            	 for(Ships.Spaceship e : p1.Ships()) {
            		 if("Ships.Dreadnought".equals(e.getName())) {
            			 if(e.getspecialUsed()) {
            				 gameLog.clear();
            				 gameLog.appendText("special used for this ship already!");
            				 specialAttackButtons[0][j].setDisable(true);
            			 }
            			 else {
            				 gameLog.clear();
            				 gameLog.appendText("Deploying dreadnought's armor plating!");
            				 p1.Ships().get(j).getSpecialAttack(gameMap.getMap());
               				 System.out.println(p1.Ships().get(j).getHealth());
               				 specialAttackButtons[0][j].setDisable(true);
               				 break;
            			 }
            		 }
            		 j++;
            	 }
            	
              });
          else if ("Ships.Stealthship".equals(name))
              specialAttackButtons[0][i].setOnAction((event) -> { // lambda event handler for player 1's stealthship ship
             	 int j = 0;
             	 for(Ships.Spaceship e : p1.Ships()) {
             		 if("Ships.Stealthship".equals(e.getName())) {
             			 if(e.getspecialUsed()) {
             				 gameLog.clear();
             				 gameLog.appendText("special used for this ship already!");
             				 specialAttackButtons[0][j].setDisable(true);
             			 }
             			 else {
             				 gameLog.clear();
             				 gameLog.appendText("Stealthship ammo reserves deployed!");
             				 p1.Ships().get(j).getSpecialAttack(gameMap.getMap());
             				   System.out.println(p2.Ships().get(j).getNumShots());
                				 specialAttackButtons[0][j].setDisable(true);
                				 break;
             			 }
             		 }
             		 j++;
             	 }
              });
          else{
            //s = new BoardButton(0);
            System.out.println("Something went wrong with ship buttons");
        }

      }
      ships.add(pane, 0, p1.Ships().size()+1);
      ships.add(lp2, 0, p1.Ships().size()+2);
      for (i=0; i<p2.Ships().size(); i++){
        name = p2.Ships().get(i).getName();
        if ("Ships.Carrier".equals(name))
          s = new BoardButton(0);
        else if ("Ships.Corvette".equals(name))
          s = new BoardButton(1);
        else if ("Ships.Cruiser".equals(name))
          s = new BoardButton(2);
        else if ("Ships.Dreadnought".equals(name))
          s = new BoardButton(3);
        else if ("Ships.Stealthship".equals(name))
          s = new BoardButton(4);
        else{
          s = new BoardButton(0);
          System.out.println("Something went wrong with ship buttons");
        }
        specialAttackButtons[1][i] = s;
        if ("Ships.Carrier".equals(name))
            specialAttackButtons[1][i].setOnAction((event) -> { // lambda event handler for player 2's carrier ship 
            	System.out.println("carrier clicked");
            });
          else if ("Ships.Corvette".equals(name))
              specialAttackButtons[1][i].setOnAction((event) -> { // lambda event handler for player 2's corvette ship
                      int j = 0;
                      for(Ships.Spaceship e : p2.Ships()) {
                       if("Ships.Corvette".equals(e.getName())) {
                      	 if(e.getspecialUsed()) {
               				gameLog.clear();
               				gameLog.appendText("special used for this ship already!");
               				specialAttackButtons[1][j].setDisable(true);
                      	 }
                      	 else {
                      		gameLog.clear();
                      		gameLog.appendText("Corvette thrusters are primed and ready!");
                      		if(e.getXPos() + 3 >= 10) { // checking for niche case where x axis cannot add 3 but y axis can
                      			if(e.getYPos() + 3 >= 10) {
                      				gameLog.clear();
                      				gameLog.appendText("special attack cannot be completed, not enough space present");
                      				e.setspecialUsed();
                      				specialAttackButtons[1][j].setDisable(true);
                      			}
                      			else {
                      				System.out.println("Y axis can recieve special attack");
                      				gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeOccupied(false);
                      				gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeItem(0);
                      				System.out.println(e.getXPos());
                      				System.out.println(e.getYPos());
                      				e.setXYPos(e.getXPos(), e.getYPos()+ 3);
                      				gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeOccupied(true);
                      				gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeItem(e.getOwner());
                      				e.setspecialUsed();
                      				specialAttackButtons[1][j].setDisable(true);
                      				System.out.println(e.getXPos());
                      				System.out.println(e.getYPos());
                      				break;
                      			}
                      		}
                      		else if(e.getYPos() + 3 >= 10) { // checking for niche case where y axis cannot add 3 but x axis can
                      			if(e.getXPos() + 3 >= 10) {
                      				gameLog.clear();
                      				gameLog.appendText("special attack cannot be completed, not enough space present");
                      				e.setspecialUsed();
                      				specialAttackButtons[1][j].setDisable(true);
                      			}
                      			else {
                      				System.out.println("X axis can recieve special attack");
                      				gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeOccupied(false);
                      				gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeItem(0);
                      				System.out.println(e.getXPos());
                      				System.out.println(e.getYPos());
                      				e.setXYPos(e.getXPos()+3, e.getYPos());
                      				gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeOccupied(true);
                      				gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeItem(e.getOwner());
                      				e.setspecialUsed();
                      				specialAttackButtons[1][j].setDisable(true);
                      				System.out.println(e.getXPos());
                      				System.out.println(e.getYPos());
                      				break;
                      			}
                      		}
                      		else { // if we got here, that means both X and Y can add 3 to them...
                  				System.out.println("both axis can recieve special attack");
                      			gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeOccupied(false);
                  				gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeItem(0);
                  				System.out.println(e.getXPos());
                  				System.out.println(e.getYPos());
                  				e.setXYPos(e.getXPos()+3, e.getYPos()+3);
                  				gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeOccupied(true);
                  				gameMap.getMap().getSpace()[e.getXPos()][e.getYPos()].changeItem(e.getOwner());
                  				e.setspecialUsed();
                  				specialAttackButtons[1][j].setDisable(true);
                  				System.out.println(e.getXPos());
                  				System.out.println(e.getYPos());
                  				break;
                      		}
                      	 }
                       }
                       j++;
                       }
                       });
          else if ("Ships.Cruiser".equals(name))
              specialAttackButtons[1][i].setOnAction((event) -> { // lambda event handler for player 2's cruiser ship
              	System.out.println("cruiser clicked");
              });
          else if ("Ships.Dreadnought".equals(name))
              specialAttackButtons[1][i].setOnAction((event) -> { // lambda event handler for player 2's cruiser ship
             	 int j = 0;
             	 for(Ships.Spaceship e : p2.Ships()) {
             		 if("Ships.Dreadnought".equals(e.getName())) {
             			 if(e.getspecialUsed()) {
             				 gameLog.clear();
             				 gameLog.appendText("special used for this ship already!");
             				 specialAttackButtons[1][j].setDisable(true);
             			 }
             			 else {
             				 gameLog.clear();
             				 gameLog.appendText("Deploying dreadnought's armor plating!");
             				 p2.Ships().get(j).getSpecialAttack(gameMap.getMap());
                				 System.out.println(p2.Ships().get(j).getHealth());
                				 specialAttackButtons[1][j].setDisable(true);
                				 break;
             			 }
             		 }
             		 j++;
             	 }
              });
          else if ("Ships.Stealthship".equals(name))
              specialAttackButtons[1][i].setOnAction((event) -> { // lambda event handler for player 2's stealthship ship
              	 int j = 0;
              	 for(Ships.Spaceship e : p2.Ships()) {
              		 if("Ships.Stealthship".equals(e.getName())) {
              			 if(e.getspecialUsed()) {
              				 gameLog.clear();
              				 gameLog.appendText("special used for this ship already!");
              				 specialAttackButtons[1][j].setDisable(true);
              			 }
              			 else {
              				 gameLog.clear();
              				 gameLog.appendText("Stealthship ammo reserves deployed!");
              				 p2.Ships().get(j).getSpecialAttack(gameMap.getMap());
                 		     System.out.println(p2.Ships().get(j).getNumShots());
                 			 specialAttackButtons[1][j].setDisable(true);
                 			 break;
              			 }
              		 }
              		 j++;
              	 }
              });
          else{
            //s = new BoardButton(0);
            System.out.println("Something went wrong with ship buttons");
        }
        specialAttackButtons[1][i].disable();
        ships.add(s, 0, i+p1.Ships().size()+3);
      }
      return ships;
    }

    private VBox createShipSelectorVBox(Parent shipsPane) {
        directions = new Text("SELECT YOUR SHIPS");
        directions.setTextAlignment(TextAlignment.CENTER);
        directions.setWrappingWidth(242.05999755859375);

        directions.setFont(Font.font("Barlow Condensed SemiBold", 35));

        Text currentlySelectingText = new Text("CURRENTLY SELECTING: Player ");
        currentlySelectingText.setFont(Font.font("Barlow Condensed Regular", 15));

        playerid = new Text("1");
        playerid.setFont(Font.font("Barlow Condensed Regular", 15));

        HBox playerText = new HBox(currentlySelectingText, playerid);
        playerText.setAlignment(Pos.CENTER);
        playerText.setPrefHeight(36.0);
        playerText.setPrefWidth(600.0);

        VBox shipSelectorVBox = new VBox(directions, shipsPane, playerid);
        VBox.setMargin(directions, new Insets(50,0,5,0));
        VBox.setMargin(playerid, new Insets(50,0,0,0));
        shipSelectorVBox.setAlignment(Pos.CENTER);
        shipSelectorVBox.setSpacing(50.0);
        shipSelectorVBox.setPrefHeight(819.0);
        shipSelectorVBox.setPrefWidth(491.0);
        return shipSelectorVBox;
    }

    private VBox createGameLog() {
        Text title = new Text("GAME LOG");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(Font.font("Barlow Condensed SemiBold", 35.0));

        gameLog = new TextArea();
        gameLog.setPrefHeight(644.0);
        gameLog.setPrefWidth(491.0);
        gameLog.insertText(0, "Game start! Player 1 goes first!");
        gameLog.setDisable(true);

        HBox bottomText;
        currentTurnText = new Text("CURRENT TURN: Player ");
        title.setFont(Font.font("Barlow Condensed SemiBold", 15));
        playerid = new Text("1");
        playerid.setFont(Font.font("Barlow Condensed Regular", 15));
        bottomText = new HBox(currentTurnText, playerid);
        bottomText.setAlignment(Pos.CENTER);

        VBox gameLogVBox = new VBox(title, gameLog, bottomText);
        VBox.setMargin(title, new Insets(0,0,5,0));
        gameLogVBox.setAlignment(Pos.CENTER);
        gameLogVBox.setSpacing(5.0);

        return gameLogVBox;
    }
    
    	/* 
    	EventHandler<ActionEvent> dreadnoughtHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				for(int i = 0; i < currentPlayer.Ships().size(); i++) {
					if(currentPlayer.Ships().get(i).getName() == "Ships.Dreadnought") {
						if(currentPlayer.Ships().get(i).getspecialUsed() == true) {
							System.out.println("Special attack already used for this Dreadnought!");
						}
						else {
							currentPlayer.Ships().get(i).getSpecialAttack(gameMap.getMap());
						}
					}
				}
			}
    		
    	};
    	*/
    	EventHandler<ActionEvent> cruiserHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				for(int i = 0; i < currentPlayer.Ships().size(); i++) {
					if(currentPlayer.Ships().get(i).getName() == "Ships.Cruiser") {
						if(currentPlayer.Ships().get(i).getspecialUsed() == true) {
							System.out.println("Special attack already used for this Cruiser!");
						}
						else {
							int result = currentPlayer.Ships().get(i).getSpecialAttack(gameMap.getMap());
							if(result == 1) {
					        	System.out.println("Succesful special attack!");
					        	for(int f = 0; i < 10; i++) {
					        		for(int j = 0; j < 10; j++) {
					        			if(gameMap.getMap().getSpace()[f][j].Hit()) {
					        				BoardButton b = buttonGrid[f][j];
					        				attackPosition(f,j,b);
					        			}
					        		}
					        	}            
					        }
					        else {
					        	System.out.println("Unsuccesful special attack!");
					        }
					      }
							
						}
					}
          BoardButton button = (BoardButton) e.getSource(); // get the current button
          button.Click();
				}
			
    		
    	};
    	EventHandler<ActionEvent> corvetteHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				for(int i = 0; i < currentPlayer.Ships().size(); i++) {
					if(currentPlayer.Ships().get(i).getName() == "Ships.Corvette") {
						if(currentPlayer.Ships().get(i).getspecialUsed() == true) {
							System.out.println("special attack already used for this Corvette!");
						}
						else {
							currentPlayer.Ships().get(i).getSpecialAttack(gameMap.getMap());
						}
					}
				}
        BoardButton button = (BoardButton) e.getSource(); // get the current button
        button.Click();
			}
    		
    	};
    	EventHandler<ActionEvent> stealthshipHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for(int i = 0; i < currentPlayer.Ships().size(); i++) {
					if(currentPlayer.Ships().get(i).getName() == "Ships.Stealthship") {
						if(currentPlayer.Ships().get(i).getspecialUsed() == true) {
							System.out.println("special attack already used for this Stealthship!");
						}
						else {
							currentPlayer.Ships().get(i).getSpecialAttack(gameMap.getMap());
						}
					}
				}
        BoardButton button = (BoardButton) arg0.getSource(); // get the current button
        button.Click();
			}
    		
    	};
    	EventHandler<ActionEvent> carrierHandler = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for(int i = 0; i < currentPlayer.Ships().size(); i++) {
					if(currentPlayer.Ships().get(i).getName() == "Ships.Carrier") {
						if(currentPlayer.Ships().get(i).getspecialUsed() == true) {
							System.out.println("special attack already used for this Carrier!");
						}
						else {
							int result = currentPlayer.Ships().get(i).getSpecialAttack(gameMap.getMap());
							if(result == 1) {
					        	System.out.println("Succesful special attack!");
					        	for(int f = 0; i < 10; i++) {
					        		for(int j = 0; j < 10; j++) {
					        			if(gameMap.getMap().getSpace()[f][j].Hit()) {
					        				BoardButton b = buttonGrid[f][j];
					        				attackPosition(f,j,b);
					        			}
					        		}
					        	}            
					        }
					        else {
					        	System.out.println("Unsuccesful special attack!");
					        }
						}
					}
				}
				
        BoardButton button = (BoardButton) arg0.getSource(); // get the current button
        button.Click();
			}
    		
    	};

    @FXML
    public void onExitButton(ActionEvent actionEvent) {

        if (winner == 1)
            gameEndText.setText("The game has ended. Player 1 wins! Press button once more to exit.");
        else if (winner == 2)
            gameEndText.setText("The game has ended. Player 2 wins! Press button once more to exit.");
        count++;
        if (count == 2)
            java.lang.System.exit(0);
    }
    
    // highlight current player buttons and unhighlight the others
    // pH = player to be highlighted, pUH = player to be unhighlighted
    void highlight(Player pH, Player pUH, BoardButton[][] grid){
      int x;
      int y;
      ArrayList<Spaceship> ships = pUH.Ships();
      // unhighlight
      for(int i=0; i<ships.size(); i++){
        x = ships.get(i).getXPos();
        y = ships.get(i).getYPos();
        grid[x][y].setTheme();  // resets to original theme before highlight
      }

      ships = pH.Ships();
      // highlight pH's ships
      for(int i=0; i<ships.size(); i++){
        x = ships.get(i).getXPos();
        y = ships.get(i).getYPos();
        grid[x][y].highLightPlayerShip();
      }
    }
}
