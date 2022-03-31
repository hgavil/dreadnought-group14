// the main class

import java.io.IOException;
import java.util.HashMap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tools.SceneBuilder;

public class Main extends Application {
    HashMap<String, Scene> sceneMap = new HashMap<String,Scene>();

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Dreadnought");
        stage.setResizable(false);

        SceneBuilder sceneBuilder = new SceneBuilder();
        GridPane gameGrid = new GridPane();


        // load scene
        FXMLLoader welcomeLoader = new FXMLLoader(Main.class.getResource("gameStart.fxml"));
        Parent welcomePane = welcomeLoader.load();
        Scene welcomeScreen = new Scene(welcomePane, 600, 500);

        FXMLLoader selectShipLoader = new FXMLLoader(Main.class.getResource("selectvbox.fxml"));
        Parent selectShipsPane = selectShipLoader.load();

        Scene selectShips = sceneBuilder.selectShipScene(selectShipsPane, gameGrid);

        //VBox testbox = new VBox(selectShipsPane);

        //Scene selectShips = new Scene(testbox, 1600, 950);

        sceneMap.put("welcome", welcomeScreen);
        sceneMap.put("ships", selectShips);

        StartController controller = (StartController) welcomeLoader.getController();
        controller.setScenes(sceneMap);

        // load the first screen
        stage.setScene(welcomeScreen);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
        /* initialization phase */

//        // create game instance for the game to begin
//        Game game = new Game();
//
//        // create player instances
//        Player p1 = new Player(1);
//        Player p2 = new Player(2);
//
//        // create map
//        Terrain gameMap = new Terrain();
//
//        Scanner in = new Scanner(System.in);
//
//
//        /* setup phase */
//
//        // console prints introductory message
//        System.out.println("Welcome to Dreadnought.");
//        System.out.println("In this game, two players will hide three ships on the game board, and take turns guessing where the other player's ships are.");
//        System.out.println("To win, eliminate all of your opponent's ships.");
//        System.out.println("To begin, Player 1 will set up their ships first.");
//
//        // run setup method
//        game.setupGame(p1, p2, in, gameMap);
//
//
//        /* game phase */
//
//        // console prints game start messages and turn swap messages
//        Round match = new Round(p1, p2, gameMap, in);
//        match.getWinner();



        /* game end phase */

        // console prints an ending message and exits


    }
}
