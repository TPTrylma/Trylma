package bin.fxController;

import bin.Board.Board;
import bin.Rules.ClassicRules;
import bin.Rules.LongJumpRules;
import bin.Trylma;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TestMenuController {

    static Scene gameScene;

    @FXML
    private TextField playersField;

    @FXML
    private TextField sizeField;

    @FXML
    private Button startButton;


    public void startButtonPressed() throws Exception {

        int players = Integer.parseInt(playersField.getText());
        int size = Integer.parseInt(sizeField.getText());
        Board board = new Board(players, size, new ClassicRules());
        Pane pane = new Pane();
        //Scene scene = new Scene(pane, 1000, 1000);

        //Trylma.window.setTitle("Trylma");
        for(int i =0; i<=size*4; i++){
            for(int j=0; j<=size*6; j++){
                if(board.fieldArr[j][i]!=null) {
                    pane.getChildren().add(board.fieldArr[j][i]);
                }
                try{
                    if(board.fieldArr[j][i].getChecker()!=null) pane.getChildren().add(board.fieldArr[j][i].getChecker());
                }catch (Exception e){}
            }
        }

        URL url = new File("src/main/resources/fxml/game.fxml").toURL();
        FXMLLoader loader = new FXMLLoader(url);
        AnchorPane aPane = loader.load();
        GameController gc = loader.getController();

        gc.setPane(pane);

        gameScene = new Scene(aPane);

        Trylma.window.setScene(gameScene);
    }

}
