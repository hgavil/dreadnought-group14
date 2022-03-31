package tools;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class SceneBuilder {
    // selectShip
    BorderPane gameWindow;
    HBox titleBar;
    Text title;
    HBox centerContents;

    public Scene selectShipScene(Parent leftPane, GridPane gameGrid) {
        // dreadnought title
        title = new Text("DREADNOUGHT");
        title.setFont(Font.font("Barlow Condensed SemiBold", 50));
        title.setTextAlignment(TextAlignment.CENTER);

        // titlebar hbox
        titleBar = new HBox(title);
        titleBar.setAlignment(Pos.CENTER);
        titleBar.prefHeight(50.0);
        HBox.setHgrow(titleBar, Priority.ALWAYS);
        titleBar.setStyle("-fx-background-color: slateblue;");


        // center contents
        centerContents = new HBox(leftPane, gameGrid);

        // borderpane
        gameWindow = new BorderPane();
        gameWindow.setTop(titleBar);
        gameWindow.setCenter(centerContents);

        Scene scene = new Scene(gameWindow, 1600, 900);
        return scene;

    }

}
