package bin.fxController;

import bin.Board.Board;
import bin.Rules.LongJumpRules;
import bin.Trylma;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.File;
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
        Trylma.board = new Board(players, size, new LongJumpRules());

        Pane paneChecker = new Pane();
        Pane paneField = new Pane();
        AnchorPane rootPane = new AnchorPane();
        for(int i =0; i<=size*4; i++){
            for(int j=0; j<=size*6; j++){
                if(Trylma.board.getArr()[j][i]!=null) {
                    paneField.getChildren().add(Trylma.board.getArr()[j][i]);
                }
                try{
                    if(Trylma.board.getArr()[j][i].getChecker()!=null) paneChecker.getChildren().add(Trylma.board.getArr()[j][i].getChecker());
                }catch (Exception e){}
            }
        }
        rootPane.getChildren().addAll(paneField, paneChecker);

        URL url = new File("src/main/resources/fxml/game.fxml").toURL();
        FXMLLoader loader = new FXMLLoader(url);

        AnchorPane aPane = loader.load();
        GameController gc = loader.getController();

        gc.setPane(rootPane);

        gameScene = new Scene(aPane);

        Trylma.window.setScene(gameScene);

    }

}
