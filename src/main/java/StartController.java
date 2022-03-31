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
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
    static Button[][] buttonGrid;
    enum ModeSettings {SETUP, INGAME};
    static ModeSettings currentMode;
    static Player currentPlayer;
    static Round match;
    static GridPane gameGrid;
    static Stage stage;

    static HashMap<String, Scene> sceneMap = new HashMap<String,Scene>();
    static SceneBuilder sceneBuilder;

    static Text directions;
    static Text playerid;

    static TextArea gameLog;

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

        gameGrid = createBoard();
        VBox shipSelectorVBox = createShipSelectorVBox(selectShipsPane);
        disableGameGrid();

        Scene selectShips = sceneBuilder.selectShipScene(shipSelectorVBox, gameGrid);
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

        enableGameGrid();
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

        enableGameGrid();
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

        enableGameGrid();

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

        enableGameGrid();

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

        enableGameGrid();

    }



    private void disableGameGrid() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttonGrid[i][j].setDisable(true);
            }
        }
    }

    private void enableGameGrid() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttonGrid[i][j].setDisable(false);
            }
        }
    }


    private GridPane createBoard() {

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

                                // change the scene
                                VBox gameLogVBox = createGameLog();
                                Scene mainGame = sceneBuilder.selectShipScene(gameLogVBox, gameGrid);
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

        int btnDimension = 70;

        int i,j=0;
        for (i=0; i<10; i++) {
            for (j=0; j<10; j++) {
                Button b = new BoardButton(i, j);
                b.setOnAction(buttonHandler);
                buttonGrid[i][j] = b;
                b.setStyle("-fx-color: lightgray;"+"-fx-min-width: "+btnDimension+";"+"-fx-min-height: "+btnDimension+";");
                board.add(b, j, i);
            }
        }

        return board;
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
        Text currentTurnText = new Text("CURRENT TURN: Player ");
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


}
