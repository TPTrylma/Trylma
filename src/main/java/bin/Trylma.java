package bin;

import bin.Board.Board;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;


public class Trylma extends Application {

    public static Stage window;

    public static Board board;

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        URL url = new File("src/main/resources/fxml/testMenu.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

