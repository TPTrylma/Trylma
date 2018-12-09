package bin.fxController;

import bin.Trylma;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.control.TextField;


public class GameController {

    @FXML
    private Pane trylma;

    @FXML
    private TextField fromX;

    @FXML
    private TextField fromY;

    @FXML
    private TextField toX;

    @FXML
    private TextField toY;


    public void setPane(Pane pane) {
        trylma.getChildren().add(pane);
    }

    public void movePressed() {
        int fX = Integer.parseInt(fromX.getText());
        int fY = Integer.parseInt(fromY.getText());
        int tX = Integer.parseInt(toX.getText());
        int tY = Integer.parseInt(toY.getText());
        Trylma.board.move(fX, fY, tX, tY);
    }

}
