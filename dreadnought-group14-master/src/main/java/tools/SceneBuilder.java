package tools;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    public Scene selectShipScene(Parent leftPane, GridPane gameGrid, GridPane playerButtons) {
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
        centerContents = new HBox(leftPane, gameGrid, playerButtons);

        // borderpane
        gameWindow = new BorderPane();
        gameWindow.setTop(titleBar);
        gameWindow.setCenter(centerContents);

        Scene scene = new Scene(gameWindow, 1600, 900);
        return scene;

    }
//    public GridPane createBoard() {
//        GridPane board = new GridPane();
//        board.setPadding(new Insets(40));
//        board.setHgap(10);
//        board.setVgap(10);
//
//        int btnDimension = 70;
//
//        int i,j=0;
//        for (i=0; i<10; i++) {
//            for (j=0; j<10; j++) {
//                BoardButton b = new BoardButton(i,j);
//                b.setOnAction(e->{
//                  System.out.println("row:"+b.getRow()+", col:"+b.getCol());
//                });
//                board.add(b, j, i);
//            }
//        }
//
//        return board;
//    }

}
