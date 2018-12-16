package bin.fxController;

import bin.Board.Board;
import bin.Rules.ClassicRules;
import bin.Rules.LongJumpRules;
import bin.Rules.Rules;
import bin.Server;
import bin.Trylma;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static java.lang.Thread.sleep;

public class CreateServerController {

    @FXML
    private TextField ipField;

    @FXML
    private TextField portField;

    @FXML
    private MenuButton sizeMB;

    @FXML
    private MenuButton playersMB;

    @FXML
    private MenuButton rulesMB;

    @FXML
    public void initialize() throws Exception{
        setSizeMenuButton();
        setPlayersMenuButton();
        setRulesMenuButton();
    }

    private void setSizeMenuButton() {
        MenuItem one = new MenuItem("1");
        MenuItem two = new MenuItem("2");
        MenuItem three = new MenuItem("3");
        MenuItem four = new MenuItem("4");
        MenuItem five = new MenuItem("5");

        sizeMB.getItems().add(one);
        sizeMB.getItems().add(two);
        sizeMB.getItems().add(three);
        sizeMB.getItems().add(four);
        sizeMB.getItems().add(five);
        sizeMB.setText("4");

        one.setOnAction(event -> sizeMB.setText("1"));
        two.setOnAction(event -> sizeMB.setText("2"));
        three.setOnAction(event -> sizeMB.setText("3"));
        four.setOnAction(event -> sizeMB.setText("4"));
        five.setOnAction(event -> sizeMB.setText("5"));
    }

    private void setPlayersMenuButton() {
        MenuItem two = new MenuItem("2");
        MenuItem three = new MenuItem("3");
        MenuItem four = new MenuItem("4");
        MenuItem six = new MenuItem("6");

        playersMB.getItems().add(two);
        playersMB.getItems().add(three);
        playersMB.getItems().add(four);
        playersMB.getItems().add(six);
        playersMB.setText("2");

        two.setOnAction(event -> playersMB.setText("2"));
        three.setOnAction(event -> playersMB.setText("3"));
        four.setOnAction(event -> playersMB.setText("4"));
        six.setOnAction(event -> playersMB.setText("6"));
    }

    private void setRulesMenuButton() {
        MenuItem classic = new MenuItem("classic");
        MenuItem longJump = new MenuItem("long jump");

        rulesMB.getItems().add(classic);
        rulesMB.getItems().add(longJump);
        rulesMB.setText("classic");

        classic.setOnAction(event -> rulesMB.setText("classic"));
        longJump.setOnAction(event -> rulesMB.setText("long jump"));
    }

    public void backButtonPress() {
        Trylma.window.setScene(Trylma.menu);
    }

    public void createAndPlayButtonPress() throws IOException {
        int players = Integer.parseInt(playersMB.getText());
        int size = Integer.parseInt(sizeMB.getText());

///*        Rules rules;
//        if (rulesMB.getText().equals("classic")) {
//            rules = new ClassicRules();
//        } else if (rulesMB.getText().equals("long jump")) {
//            rules = new LongJumpRules();
//        } else {
//            rules = new ClassicRules();
//        }*/

//        Trylma.board = new Board(players, size, rules);
//
//        Pane paneChecker = new Pane();
//        Pane paneField = new Pane();
//        AnchorPane rootPane = new AnchorPane();
//
//        for(int i =0; i<=size*4; i++){
//            for(int j=0; j<=size*6; j++){
//                if(Trylma.board.getArr()[j][i]!=null) {
//                    paneField.getChildren().add(Trylma.board.getArr()[j][i]);
//                }
//                try{
//                    if(Trylma.board.getArr()[j][i].getChecker()!=null) paneChecker.getChildren().add(Trylma.board.getArr()[j][i].getChecker());
//                }catch (Exception e){}
//            }
//        }
//        rootPane.getChildren().addAll(paneField, paneChecker);

//        URL url = new File("src/main/resources/fxml/game.fxml").toURL();
//        FXMLLoader loader = new FXMLLoader(url);
//
//        AnchorPane aPane = loader.load();

        //GameController gc = loader.getController();

        //gc.setPane(rootPane);

//        gameScene = new Scene(aPane);
//
//        Trylma.window.setScene(gameScene);

        new Thread(() -> {
            try {
                new Server(players, size, rulesMB.getText(), Integer.parseInt(portField.getText()));
            } catch (IOException e) {}
        }).start();
        try {
            sleep(200);
        } catch (Exception e) {}


        Trylma.run(ipField.getText(), Integer.parseInt(portField.getText()));
    }
}
