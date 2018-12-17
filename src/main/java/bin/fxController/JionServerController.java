package bin.fxController;

import bin.Trylma;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;

public class JionServerController {


    @FXML
    private TextField ipField;

    @FXML
    private TextField portField;

    public void backButtonPress() {
        Trylma.window.setScene(Trylma.menu);
    }

    public void joinButtonPress() throws IOException {
        if (ipField.getText().equals("") || portField.getText().equals("")) {
            return;
        }
        Trylma.run(ipField.getText(), Integer.parseInt(portField.getText()));
    }

}
