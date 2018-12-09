package bin.fxController;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class GameController {

    @FXML
    private Pane trylma;


    public void setPane(Pane pane) {
        trylma.getChildren().add(pane);
    }

}
