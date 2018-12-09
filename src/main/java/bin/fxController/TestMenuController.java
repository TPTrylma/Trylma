package bin.fxController;

import bin.Board.Board;
import bin.Rules.ClassicRules;
import bin.Rules.LongJumpRules;
import bin.Trylma;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class TestMenuController {
    @FXML
    private TextField playersField;

    @FXML
    private TextField sizeField;

    @FXML
    private Button startButton;

    public void startButtonPressed(){
        int players = Integer.parseInt(playersField.getText());
        int size = Integer.parseInt(sizeField.getText());
        Board board = new Board(players, size, new ClassicRules());
        Pane paneChecker = new Pane();
        Pane paneField = new Pane();
        AnchorPane rootPane = new AnchorPane();
        Scene scene = new Scene(rootPane, 1920, 1080);
        Trylma.window.setTitle("Trylma");
        for(int i =0; i<=size*4; i++){
            for(int j=0; j<=size*6; j++){
                if(board.fieldArr[j][i]!=null) {
                    paneField.getChildren().add(board.fieldArr[j][i]);
                }
                try{
                    if(board.fieldArr[j][i].getChecker()!=null) paneChecker.getChildren().add(board.fieldArr[j][i].getChecker());
                }catch (Exception e){}
            }
        }
        rootPane.getChildren().addAll(paneField, paneChecker);
        board.move(15, 3, 14, 4);
        Trylma.window.setScene(scene);
    }
}
