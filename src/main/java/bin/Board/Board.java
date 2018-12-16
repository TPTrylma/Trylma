package bin.Board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import bin.Bot;
import bin.Rules.*;


public class Board {

    private int[] players;

    private Field fieldArr[][];

    private Rules rules;

    private int size;
    private int maxP;

    private int playingP;
    private int curP;

    private Checker touchedChecker;
    List<Checker> checkers = new ArrayList<>();
    Bot bot;
    public Board(int p, int size, Rules rules) {
        this.rules = rules;
        this.size = size;
        maxP = p;
        playingP = p;

        fieldArr = new Field[size*6+1][size*4+1];
        players = new int[6];
        setPlayers();
        setCurP();

        System.out.println("cur p " + curP);

        createFields(size);
        addCheckers(p, size);
        bot = new Bot(checkers, 3);
    }

    public Field[][] getArr() {
        return fieldArr;
    }

    public int getCurPlayer() {
        return players[curP];
    }

    public void nextPlayer() {

        check4Winner();

        if (playingP == 1) {
            System.out.println("GAME OVER");
            //BLOCK!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
            return;
        }

        curP++;
        if (curP >= 6){
            curP = 0;
        }
        //if (players[curP] == -1) {
            while (players[curP] == -1) {
                curP++;
                if (curP >= 6) {
                    curP = 0;
                }
            }
        //}

        System.out.println("cur p " + players[curP]);
        if (getTouchedChecker() != null) {
            setTouchedChecker(null);
        }
        if(curP==3) {
            bot.move();
        }
    }

    public Field getField(int x, int y){
        return fieldArr[x][y];
    }


    private void createFields(int size){

        int start;
        // top + bottom
        for (int i = 0; i < size; i++) {
            start = size*3 -i;
            for (int j = 0; j < i + 1; j++) {
                fieldArr[start + j*2][i] = new Field(start + j*2, i, this);
                fieldArr[start + j*2][4*size-i] = new Field(start + j*2, 4*size-i,  this);
            }
        }

        // mid
        for (int i = 0; i < 2*size + 1; i++) {
            start = size;
            fieldArr[start + i*2][2*size] = new Field(start + i*2, 2*size, this);
        }

        // rest
        for (int i = 0; i < size; i++) {
            start = i;
            for (int j = 0; j < 3*size +1 - i; j++) {
                fieldArr[start + j*2][i+size] = new Field(start + j*2, i+size, this);
                fieldArr[start + j*2][3*size-i] = new Field(start + j*2, 3*size-i, this);
            }
        }
    }

    private void addCheckers(int n, int size){
        for (int i = 0; i < size*4+1; i++) {
            for (int j = 0; j < size*6+1; j++) {
                if (fieldArr[j][i] != null) {
                    if (i > 3 * size) {
                        fieldArr[j][i].setInitChecker(j, i, 0);
                    }

                    if (n == 2) {
                        if (i < size) {
                            fieldArr[j][i].setInitChecker(j, i, 3);
                            checkers.add(fieldArr[j][i].getChecker());
                        }
                    }
                    if (n == 3) {
                        if (j+i <= size*3 - 2) {
                            fieldArr[j][i].setInitChecker(j, i, 2);
                        }
                        if (j-i >= size*3 + 2) {
                            fieldArr[j][i].setInitChecker(j, i, 4);
                        }
                    }
                    if (n == 4) {
                        if (i-j >= size + 2) {
                            fieldArr[j][i].setInitChecker(j, i, 1);
                        }
                        if (i < size) {
                            fieldArr[j][i].setInitChecker(j, i, 3);
                            checkers.add(fieldArr[j][i].getChecker());
                        }
                        if (j-i >= size * 3 + 2) {
                            fieldArr[j][i].setInitChecker(j, i, 4);
                        }
                    }
                    if (n == 6) {
                        if (i-j >= size + 2) {
                            fieldArr[j][i].setInitChecker(j, i, 1);
                        }
                        if (i+j <= size * 3 - 2) {
                            fieldArr[j][i].setInitChecker(j, i, 2);
                        }
                        if (i < size) {
                            fieldArr[j][i].setInitChecker(j, i, 3);
                            checkers.add(fieldArr[j][i].getChecker());
                        }
                        if (j-i >= size * 3 + 2) {
                            fieldArr[j][i].setInitChecker(j, i, 4);
                        }
                        if (i+j >= size * 7 + 2) {
                            fieldArr[j][i].setInitChecker(j, i, 5);
                        }
                    }
                }
            }
        }
    }

    private void setPlayers() {
        for (int i = 0; i < 6; i++) {
            players[i] = -1;
        }
        if (maxP == 2) {
            players[0] = 0;
            players[3] = 3;
        }
        if (maxP == 3) {
            players[0] = 0;
            players[2] = 2;
            players[4] = 4;
        }
        if (maxP == 4 ) {
            players[0] = 0;
            players[1] = 1;
            players[3] = 3;
            players[4] = 4;
        }
        if (maxP == 6) {
            for (int i = 0; i < 6; i++) {
                players[i] = i;
            }
        }
    }

    private void setCurP() {

        Random rand = new Random();
        int a = rand.nextInt(6);
        while (players[a] == -1) {
            a = rand.nextInt(6);
        }
        curP = a;

    }

    public void move(int fromX, int fromY, int toX, int toY){
        Field from = getField(fromX, fromY);
        Field to = getField(toX, toY);

        if (rules.move(from, to) == 1) {
            nextPlayer();
        }

    }

    public void setTouchedChecker(Checker touchedChecker) {
        this.touchedChecker = touchedChecker;
    }

    public Checker getTouchedChecker() {
        return touchedChecker;
    }

    private void check4Winner() {

        if (getCurPlayer() == 0) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size * 6 + 1; j++) {
                    if (fieldArr[j][i] != null) {
                        if (fieldArr[j][i].getChecker() == null || fieldArr[j][i].getChecker().getColor() != 0) {
                            return;
                        }
                    }
                }
            }
            players[0] = -1;
            System.out.println("Player " + 0 + " finished the game");
            playingP--;
        }

        if (getCurPlayer() == 3) {
            for (int i = size*3+1; i <= size*4; i++) {
                for (int j = 0; j < size * 6 + 1; j++) {
                    if (fieldArr[j][i] != null) {
                        if (fieldArr[j][i].getChecker() == null || fieldArr[j][i].getChecker().getColor() != 3) {
                            return;
                        }
                    }
                }
            }
            players[3] = -1;
            System.out.println("Player " + 3 + " finished the game");
            playingP--;
        }

        if (getCurPlayer() == 1) {
            for (int i = size; i <= size*2; i++) {
                for (int j = size*3+2+i; j < size*7 + 1-i; j++) {
                    if (fieldArr[j][i] != null) {
                        if (fieldArr[j][i].getChecker() == null || fieldArr[j][i].getChecker().getColor() != 1) {
                            return;
                        }
                    }
                }
            }
            players[1] = -1;
            System.out.println("Player " + 1 + " finished the game");
            playingP--;
        }

        if (getCurPlayer() == 5) {
            for (int i = size; i <= size*2; i++) {
                for (int j = 0; j < size*3-i; j++) {
                    if (fieldArr[j][i] != null) {
                        if (fieldArr[j][i].getChecker() == null || fieldArr[j][i].getChecker().getColor() != 5) {
                            return;
                        }
                    }
                }
            }
            players[5] = -1;
            System.out.println("Player " + 5 + " finished the game");
            playingP--;
        }

        if (getCurPlayer() == 2) {
            for (int i = size*2 +1; i <= size*3; i++) {
                for (int j = size*7 +2-i; j < size*6 +1; j++) {
                    if (fieldArr[j][i] != null) {
                        if (fieldArr[j][i].getChecker() == null || fieldArr[j][i].getChecker().getColor() != 2) {
                            return;
                        }
                    }
                }
            }
            players[2] = -1;
            System.out.println("Player " + 2 + " finished the game");
            playingP--;
        }

        if (getCurPlayer() == 4) {
            for (int i = size*2 +1; i <= size*3; i++) {
                for (int j = 0; j < -size-1+i; j++) {
                    if (fieldArr[j][i] != null) {
                        if (fieldArr[j][i].getChecker() == null || fieldArr[j][i].getChecker().getColor() != 4) {
                            return;
                        }
                    }
                }
            }
            players[4] = -1;
            System.out.println("Player " + 4 + " finished the game");
            playingP--;
        }

    }
    public int getSize(){return size;}
}
