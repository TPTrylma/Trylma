package bin.fxController;

import bin.Trylma;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;


public class GameController {

    @FXML
    private Pane trylma;

    @FXML
    private Button finishButton;


    public void setPane(Pane pane) {
        trylma.getChildren().add(pane);
    }

    public void finishPressed() {
        Trylma.board.nextPlayer();
    }

}
