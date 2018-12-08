import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Board extends Application {

    List<String> players;

    Field fieldArr[][];

    Rules rules;

    int size;
    int maxP;
    int playingP;
    int curP;
    @Override
    public void start(Stage primaryStage){
        Parameters p = getParameters();
        List<String> l = p.getUnnamed();
        size=Integer.parseInt(l.get(1));
        maxP=Integer.parseInt(l.get(0));
        playingP=Integer.parseInt(l.get(0));
        curP = 1;
        players = new ArrayList<String>();
        fieldArr = new Field[size*6+1][size*4+1];
        this.rules = rules;

        createFields(size);
        addCheckers(maxP, size);
        Pane pane = new Pane();
        for(int i =0; i<=size*4; i++){
            for(int j=0; j<=size*6; j++){
                if(fieldArr[j][i]!=null) {
                    pane.getChildren().add(fieldArr[j][i]);
                }
                try{
                    if(fieldArr[j][i].getChecker()!=null) pane.getChildren().add(fieldArr[j][i].getChecker());
                }catch (Exception e){}
            }
        }
        Scene scene = new Scene(pane, 1920, 1080);
        primaryStage.setTitle("Trylma");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    void setRules(Rules rules){
        this.rules = rules;
    }

    String getCurPlayer(){
        return players.get(curP);
    }

    String getNextPlayer(){
        curP += 1;
        if (curP >= playingP){
            curP = 0;
        }
        return players.get(curP);
    }

    Field getField(int x, int y){
        return fieldArr[x][y];
    }

    void playerWin(int n){
        players.remove(n);
        playingP--;
    }

    private void createFields(int size){

        int start;
        // top + bottom
        for (int i = 0; i < size; i++) {
            start = size*3 -i;
            for (int j = 0; j < i + 1; j++) {
                fieldArr[start + j*2][i] = new Field(start + j*2, i, "n", this);
                fieldArr[start + j*2][4*size-i] = new Field(start + j*2, 4*size-i, "n", this);
            }
        }
        // mid
        for (int i = 0; i < 2*size + 1; i++) {
            start = size;
            fieldArr[start + i*2][2*size] = new Field(start + i*2, 2*size, "n", this);
        }
        // rest
        for (int i = 0; i < size; i++) {
            start = i;
            for (int j = 0; j < 3*size +1 - i; j++) {
                fieldArr[start + j*2][i+size] = new Field(start + j*2, i+size, "n", this);
                fieldArr[start + j*2][3*size-i] = new Field(start + j*2, 3*size-i, "n",this);
            }
        }
    }

    private void addCheckers(int n, int size){
        for (int i = 0; i < size*4+1; i++) {
            for (int j = 0; j < size*6+1; j++) {
                if (fieldArr[j][i] != null) {
                    if (i > 3 * size) {
                        fieldArr[j][i].setInitChecker(j, i, "g");
                    }

                    if (n == 2) {
                        if (i < size) {
                            fieldArr[j][i].setInitChecker(j, i, "k");
                        }
                    }
                    if (n == 3) {
                        if (j+i <= size*3 - 2) {
                            fieldArr[j][i].setInitChecker(j, i, "k");
                        }
                        if (j-i >= size*3 + 2) {
                            fieldArr[j][i].setInitChecker(j, i, "b");
                        }
                    }
                    if (n == 4) {
                        if (i-j >= size + 2) {
                            fieldArr[j][i].setInitChecker(j, i, "k");
                        }
                        if (i < size) {
                            fieldArr[j][i].setInitChecker(j, i, "b");
                        }
                        if (j-i >= size * 3 + 2) {
                            fieldArr[j][i].setInitChecker(j, i, "o");
                        }
                    }
                    if (n == 6) {
                        if (i-j >= size + 2) {
                            fieldArr[j][i].setInitChecker(j, i, "k");
                        }
                        if (i+j <= size * 3 - 2) {
                            fieldArr[j][i].setInitChecker(j, i, "b");
                        }
                        if (i < size) {
                            fieldArr[j][i].setInitChecker(j, i, "o");
                        }
                        if (j-i >= size * 3 + 2) {
                            fieldArr[j][i].setInitChecker(j, i, "r");
                        }
                        if (i+j >= size * 7 + 2) {
                            fieldArr[j][i].setInitChecker(j, i, "p");
                        }
                    }
                }
            }
        }
    }

    void move(int fromX, int fromY, int toX, int toY){
        Field from = getField(fromX, fromY);
        Field to = getField(toX, toY);
        rules.move(from, to);
    }

    private void visualMove(Field from, Field to){
        Checker tmp = from.getChecker();
        from.setChecker(null);
        to.setChecker(tmp);
    }

}
