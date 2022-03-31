import Map.Terrain;
import Ships.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;


public class StartController {

    HashMap<String, Scene> sceneMap = new HashMap<String,Scene>();
    public void setScenes(HashMap<String, Scene> sceneMap) {
        this.sceneMap = sceneMap;
    }

    // game variables
    static Game game;
    static Player p1, p2;
    static Terrain gameMap;

    @FXML // PLAYERID -- keeps track of which player's turn it is
    protected Text playerid;

    @FXML // DIRECTIONS -- tells the player what to do
    protected Text directions;

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
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

        FXMLLoader selectShipLoader = new FXMLLoader(Main.class.getResource("selectvbox.fxml"));
        Parent selectShipsPane = selectShipLoader.load();
        

        stage.setScene(sceneMap.get("ships"));
        stage.centerOnScreen();

    }

    // SHIP SELECTION //
    // 1. create the ship and assign its properties
    // 2. disable the ship selection buttons and enable the grid buttons

    @FXML
    protected void onCarrier() {
        Spaceship ship = new Carrier();
        ship.setName("Carrier");

        System.out.println(playerid.getText());

        // assign the ship to the correct player
        if (playerid.getText() == "1") {
            ship.setOwner(this.p1.getName());
        } else ship.setOwner(this.p2.getName());

        disableShipButtons();
    }

    @FXML
    protected void onCorvette() {
        Spaceship ship = new Corvette();
        ship.setName("Corvette");

        // assign the ship to the correct player
        if (playerid.getText() == "1") {
            ship.setOwner(p1.getName());
        } else ship.setOwner(p2.getName());

        disableShipButtons();
    }

    @FXML
    protected void onCruiser() {
        Spaceship ship = new Cruiser();
        ship.setName("Cruiser");

        // assign the ship to the correct player
        if (playerid.getText() == "1") {
            ship.setOwner(p1.getName());
        } else ship.setOwner(p2.getName());

        disableShipButtons();
    }

    @FXML
    protected void onDreadnought() {
        Spaceship ship = new Dreadnought();
        ship.setName("Dreadnought");

        // assign the ship to the correct player
        if (playerid.getText() == "1") {
            ship.setOwner(p1.getName());
        } else ship.setOwner(p2.getName());

        disableShipButtons();
    }

    @FXML
    protected void onStealthship() {
        Spaceship ship = new Stealthship();
        ship.setName("Stealthship");

        // assign the ship to the correct player
        if (playerid.getText() == "1") {
            ship.setOwner(p1.getName());
        } else ship.setOwner(p2.getName());

        disableShipButtons();
    }

    private void disableShipButtons() {
        carrierbutton.setDisable(true);
        corvettebutton.setDisable(true);
        cruiserbutton.setDisable(true);
        dreadnoughtbutton.setDisable(true);
        stealthshipbutton.setDisable(true);
    }



}
