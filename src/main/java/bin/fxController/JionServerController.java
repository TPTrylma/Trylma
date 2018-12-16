package bin.fxController;

import bin.Trylma;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;

public class JionServerController {

    private static Scene gameScene;

    public static GameController gc;

    private int size;
    private int players;
    private String rules;

    @FXML
    private TextField ipField;

    @FXML
    private TextField portField;

    public void backButtonPress() {
        Trylma.window.setScene(Trylma.menu);
    }

    public void joinButtonPress() throws IOException {
        Trylma.run(ipField.getText(), Integer.parseInt(portField.getText()));
    }

    public void addScene() {

    }

}
