package bin.fxController;

import bin.Trylma;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class GameController {

    @FXML
    private Pane trylma;

    @FXML
    private TextField text;

    @FXML
    public TextArea chat;


    public void setPane(Pane pane) {
        trylma.getChildren().add(pane);
    }

    public void finishPressed() {
        Trylma.board.nextPlayer();
    }

    @FXML
    public void onEnter(ActionEvent ae){

        if (text.getText().equals("")) {
            return;
        }
        Trylma.out.println("MESSAGE " + text.getText());
        //chat.appendText(text.getText() + "\n");
        text.setText("");

    }

}
