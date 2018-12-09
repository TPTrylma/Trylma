package fxController;

import Board.Board;
import Rules.LongJumpRules;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class TestMenuController {

    @FXML
    private TextField playersField;

    @FXML
    private TextField sizeField;

    @FXML
    private Button startButton;

    public void startButtonPressed() throws IOException {

        Board board = new Board(Integer.parseInt(playersField.getText()), Integer.parseInt(sizeField.getText()), new LongJumpRules());

    }
}
