package bin.fxController;

import bin.Board.Board;
import bin.Rules.ClassicRules;
import bin.Rules.LongJumpRules;
import bin.Rules.Rules;
import bin.Trylma;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SoloController {

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
        MenuItem one = new MenuItem("1");
        MenuItem two = new MenuItem("2");
        MenuItem three = new MenuItem("3");
        MenuItem five = new MenuItem("5");

        playersMB.getItems().add(one);
        playersMB.getItems().add(two);
        playersMB.getItems().add(three);
        playersMB.getItems().add(five);
        playersMB.setText("1");

        one.setOnAction(event -> playersMB.setText("1"));
        two.setOnAction(event -> playersMB.setText("2"));
        three.setOnAction(event -> playersMB.setText("3"));
        five.setOnAction(event -> playersMB.setText("5"));
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

    public void playButtonPress() throws IOException {

        int players = Integer.parseInt(playersMB.getText());
        int size = Integer.parseInt(sizeMB.getText());

        Rules rules;
        if (rulesMB.getText().equals("classic")) {
            rules = new ClassicRules();
        } else if (rulesMB.getText().equals("long jump")) {
            rules = new LongJumpRules();
        } else {
            rules = new ClassicRules();
        }

        Trylma.board = new Board(players+1, size, rules, 0);
        Trylma.color = -1;

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

        GameController gc = loader.getController();

        if (players == 1) {
            Trylma.board.addBot(3);
        } else if (players == 2) {
            Trylma.board.addBot(2);
            Trylma.board.addBot(4);
        } else if (players == 3) {
            Trylma.board.addBot(1);
            Trylma.board.addBot(3);
            Trylma.board.addBot(4);
        } else {
            for (int i = 0; i < 5; i++) {
                Trylma.board.addBot(i+1);
            }
        }


        gc.setPane(rootPane);

        Scene gameScene = new Scene(aPane);

        Trylma.window.setScene(gameScene);

    }

}
