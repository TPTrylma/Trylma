package bin.fxController;

import bin.Trylma;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MenuController {

    private static Scene joinServerScene;
    private static Scene createServerScene;

    @FXML
    private TextField nameField;

    @FXML
    public void initialize() throws Exception{
        createCreateServerScene();
        createJoinServerScene();
    }

    private void createCreateServerScene() throws IOException {

        URL url = new File("src/main/resources/fxml/createServer.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        createServerScene = new Scene(root);

        createServerScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                Trylma.window.setScene(Trylma.menu);
            }
            event.consume();
        });
    }

    private void createJoinServerScene() throws IOException {

        URL url = new File("src/main/resources/fxml/joinServer.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        joinServerScene = new Scene(root);

        joinServerScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                Trylma.window.setScene(Trylma.menu);
            }
            event.consume();
        });
    }

    public void exitButtonPress() {
        Trylma.window.close();
    }

    public void createServerPress() {
        Trylma.name = nameField.getText();
        Trylma.window.setScene(createServerScene);
    }

    public void joinServerPress() {
        Trylma.name = nameField.getText();
        Trylma.window.setScene(joinServerScene);
    }

}
