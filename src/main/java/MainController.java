import Map.Terrain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Scanner;


public class MainController {
    HashMap<String, Scene> sceneMap = new HashMap<String,Scene>();


    public void setScenes(HashMap<String, Scene> sceneMap) {
        this.sceneMap = sceneMap;
    }


    @FXML
    protected void onStartButton(ActionEvent event) {
        // begin the game setup

        // create game instance for the game to begin
        Game game = new Game();

        // create player instances
        Player p1 = new Player(1);
        Player p2 = new Player(2);

        // create map
        Terrain gameMap = new Terrain();

        // change scene
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(sceneMap.get("ships"));

    }
}
