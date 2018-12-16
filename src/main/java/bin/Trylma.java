package bin;

import bin.Board.Board;
import bin.Rules.ClassicRules;
import bin.Rules.LongJumpRules;
import bin.Rules.Rules;
import bin.fxController.GameController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;

import static java.lang.Thread.sleep;


public class Trylma extends Application {

    public static Stage window;

    public static Scene menu;
    private static Scene gameScene;

    public static Board board;

    public static String name;

    public static GameController gc;

    public static int color;

    public static int size;

    public static int players;

    public static Rules rules;

    public static BufferedReader in;
    public static PrintWriter out;

    @Override
    public void start(Stage primaryStage) throws Exception {

        window = primaryStage;
        URL url = new File("src/main/resources/fxml/menu.fxml").toURL();
        Parent root = FXMLLoader.load(url);
        window.setTitle("Trylma");
        menu = new Scene(root);
        window.setScene(menu);
        window.show();

    }

    public static void run(String serverAddress, int port) throws IOException {

        Socket socket = new Socket(serverAddress, port);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        new Thread(() -> {
            while (true) {
                try {
                    String line = in.readLine();
                    System.out.println(line);
                    if (line.startsWith("SUBMITNAME")) {
                        out.println(name);
                    } else if (line.startsWith("COLOR")) {
                        color = Integer.parseInt(line.substring(6));
                    } else if (line.startsWith("SIZE")) {
                        size = Integer.parseInt(line.substring(5));
                    } else if (line.startsWith("RULES")) {
                        if (line.substring(6).equals("classic")) {
                            rules = new ClassicRules();
                        } else {
                            rules = new LongJumpRules();
                        }
                    } else if (line.startsWith("PLAYERS")) {
                        players = Integer.parseInt(line.substring(8));
                        board = new Board(players, size, rules);

                        Trylma.board = new Board(players, size, rules);

                        Pane paneChecker = new Pane();
                        Pane paneField = new Pane();
                        AnchorPane rootPane = new AnchorPane();

                        for(int i =0; i<=size*4; i++){
                            for(int j=0; j<=size*6; j++){
                                if(Trylma.board.getArr()[j][i]!=null) {
                                    paneField.getChildren().add(Trylma.board.getArr()[j][i]);
                                }
                                try{
                                    if(Trylma.board.getArr()[j][i].getChecker()!=null) paneChecker.getChildren().add(Trylma.board.getArr()[j][i].getChecker());
                                }catch (Exception e){}
                            }
                        }
                        rootPane.getChildren().addAll(paneField, paneChecker);

                        URL url = new File("src/main/resources/fxml/game.fxml").toURL();
                        FXMLLoader loader = new FXMLLoader(url);

                        AnchorPane aPane = loader.load();

                        gc = loader.getController();

                        gc.setPane(rootPane);

                        gameScene = new Scene(aPane);
                        Platform.runLater(
                                () -> {
                                    window.setScene(gameScene);
                                }
                        );


                    }else if (line.startsWith("MESSAGE")) {
                        gc.chat.appendText(line.substring(8) + "\n");
                    }

                } catch (IOException e) {}
            }
        }).start();

    }

    public static void main(String[] args) {
        launch(args);
    }
}

