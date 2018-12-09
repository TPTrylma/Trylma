package bin.fxController;

import bin.Board.Board;
import bin.Rules.ClassicRules;
import bin.Rules.LongJumpRules;
import bin.Trylma;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class TestMenuController {

    @FXML
    private TextField playersField;

    @FXML
    private TextField sizeField;

    @FXML
    private Button startButton;

    public void startButtonPressed() throws IOException {
        int players = Integer.parseInt(playersField.getText());
        int size = Integer.parseInt(sizeField.getText());
        Board board = new Board(players, size, new ClassicRules());
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 1920, 1080);
        Trylma.window.setTitle("Trylma");
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
        Trylma.window.setScene(scene);
    }
}
