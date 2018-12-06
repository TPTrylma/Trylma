import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Board {

    List<String> players;

    Field fieldArr[][];

    int maxP;
    int playingP;
    int curP;

    Board(int n, int size) {
        players = new ArrayList<String>();
        fieldArr = new Field[size*6+1][size*4+1];
        maxP = n;
        playingP = n;
        curP = 1; //random

        createFields(size);
        addCheckers(n, size);
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
                fieldArr[start + j*2][i] = new Field(start + j*2, i, "n");
                fieldArr[start + j*2][4*size-i] = new Field(start + j*2, 4*size-i, "n");
            }
        }
        // mid
        for (int i = 0; i < 2*size + 1; i++) {
            start = size;
            fieldArr[start + i*2][2*size] = new Field(start + i*2, 2*size, "n");
        }
        // rest
        for (int i = 0; i < size; i++) {
            start = i;
            for (int j = 0; j < 3*size +1 - i; j++) {
                fieldArr[start + j*2][i+size] = new Field(start + j*2, i+size, "n");
                fieldArr[start + j*2][3*size-i] = new Field(start + j*2, 3*size-i, "n");
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
                            fieldArr[j][i].setInitChecker(j, i, "y");
                        }
                    }
                    if (n == 3) {
                        if (j+i <= size*3 - 2) {
                            fieldArr[j][i].setInitChecker(j, i, "y");
                        }
                        if (j-i >= size*3 + 2) {
                            fieldArr[j][i].setInitChecker(j, i, "b");
                        }
                    }
                    if (n == 4) {
                        if (i-j >= size + 2) {
                            fieldArr[j][i].setInitChecker(j, i, "y");
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
                            fieldArr[j][i].setInitChecker(j, i, "y");
                        }
                        if (i+j <= size * 3 - 2) {
                            fieldArr[j][i].setInitChecker(j, i, "b");
                        }
                        if (i < size) {
                            fieldArr[j][i].setInitChecker(j, i, "o");
                        }
                        if (j-i >= size * 3 + 2) {
                            fieldArr[j][i].setInitChecker(j, i, "e");
                        }
                        if (i+j >= size * 7 + 2) {
                            fieldArr[j][i].setInitChecker(j, i, "w");
                        }
                    }
                }
            }
        }
    }

    int move(int fromX, int fromY, int toX, int toY){
        Field from = fieldArr[fromX][fromY];
        Field to = fieldArr[toX][toY];
        if (from.getChecker() == null) {
            System.out.println("tuta pusto");
            return 0;
        }
        //if (from.getChecker().getColor() != curP) ...
        if (to.getChecker() != null) {
            System.out.println("zanjato");
            return 0;
        }

        int rules = 0;  //get From static in main or properties

        if (rules == 0) {
            if ((abs(from.getPosX()-to.getPosX()) + abs(from.getPosY()-to.getPosY()) == 2 && abs(from.getPosY() - to.getPosY()) <= 1)) {
                visualMove(from, to);
                return 1;
            }
            //jump
            else if ((abs(from.getPosX()-to.getPosX()) + abs(from.getPosY()-to.getPosY()) == 4 && abs(from.getPosY() - to.getPosY()) <= 2)) {
                if (fieldArr[(from.getPosX()+to.getPosX())/2][(from.getPosY()+to.getPosY())/2].getChecker() != null) {
                    visualMove(from, to);
                    return 1;
                }
            }
        }
        System.out.println("daleko");
        return 0;
    }

    private void visualMove(Field from, Field to){
        Checker tmp = from.getChecker();
        from.setChecker(null);
        to.setChecker(tmp);
    }

}
