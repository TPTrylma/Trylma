package bin;

import bin.Board.Board;
import bin.fxController.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;


public class Trylma extends Application {

    public static Stage window;

    public static Board board;

    public static String name;

    public static BufferedReader in;
    public static PrintWriter out;

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        URL url = new File("src/main/resources/fxml/testMenu.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setTitle("Trylma");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void run() throws IOException {
        String serverAddress = "192.168.1.197";
        int port = 6666;

        Socket socket = new Socket(serverAddress, port);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);


        new Thread(() -> {
            while (true) {
                try {
                    String line = in.readLine();

                    if (line.startsWith("SUBMITNAME")) {
                        out.println(name);
                    } else if (line.startsWith("MESSAGE")) {
                        //GameController.chat.appendText(line.substring(8) + "\n");
                        System.out.println(line.substring(8) + "\n");
                    }
                } catch (IOException e) {}
            }
        }).start();

    }

    public static void main(String[] args) {

        name = "Taszczerrr";

        launch(args);
    }
}

