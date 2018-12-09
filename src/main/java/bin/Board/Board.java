package bin.Board;

import java.util.ArrayList;
import java.util.List;
import bin.Rules.*;


public class Board {

    private List<String> players;

    public Field fieldArr[][];

    private Rules rules;

    private int size;
    private int maxP;
    private int playingP;
    private int curP;

    public Board(int p, int size, Rules rules) {
        this.rules = rules;
        this.size = size;
        maxP = p;

        fieldArr = new Field[size*6+1][size*4+1];
        players = new ArrayList<String>();

        createFields(size);
        addCheckers(p, size);

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

    public Field getField(int x, int y){
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

    public void move(int fromX, int fromY, int toX, int toY){
        Field from = getField(fromX, fromY);
        Field to = getField(toX, toY);
        rules.move(from, to);

        //CHECK FOR WINNER!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    }

}
