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
import java.util.HashMap;
import java.util.Scanner;


public class StartController {

    // game variables
    static Game game;
    static Player p1, p2;
    static Terrain gameMap;
    static BoardButton[][] buttonGrid;
    enum ModeSettings {SETUP, INGAME};
    static ModeSettings currentMode;
    static Player currentPlayer;
    static Round match;
    static GridPane gameGrid;
    static Stage stage;
    static GridPane playerOneShips;
    static GridPane playerTwoShips;

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


        stage.setScene(sceneMap.get("ships"));
        stage.centerOnScreen();

        currentMode = ModeSettings.SETUP;
        currentPlayer = p1;
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
            stage.setScene(sceneMap.get("ships"));
          }
          else{
            currentPlayer = p1;
            playerid.setText("1");
            stage.setScene(sceneMap.get("main"));
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

      
      return board;
    } // end of createGameBoard

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
        java.lang.System.exit(0);
      // player 2 lost
      else if (p2.Ships().size() == 0)
        java.lang.System.exit(0);

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

        ships.add(s, 0, i+1);
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
				
			}
    		
    	};
    	
    	
    

}
