import java.util.ArrayList;
import java.util.List;

public class Board {

    List<String> players;

    List<Field> fields;

    int maxP;
    int playingP;
    int curP;

    Board(int n, int size) {
        players = new ArrayList<String>();
        fields = new ArrayList<Field>();
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
                fields.add(new Field(start + j*2, i, "n"));
                fields.add(new Field(start + j*2, 4*size-i, "n"));
            }
        }
        // mid
        for (int i = 0; i < 2*size + 1; i++) {
            start = size;
            fields.add(new Field(start + i*2, 2*size, "n"));
        }
        // rest
        for (int i = 0; i < size; i++) {
            start = i;
            for (int j = 0; j < 3*size +1 - i; j++) {
                fields.add(new Field(start + j*2, i+size, "n"));
                fields.add(new Field(start + j*2, 4*size-(i+size), "n"));
            }
        }
    }

    private void addCheckers(int n, int size){
        for (Field field : fields) {
            if (field.getPosY() > 3*size) {
                field.setInitChecker(field.getPosX(), field.getPosY(), "green");
            }

            if (n == 2) {
                if (field.getPosY() < size) {
                    field.setInitChecker(field.getPosX(), field.getPosY(), "yellow");
                }
            }
            if (n == 3) {
                if (field.getPosX() + field.getPosY() <= size*3 - 2) {
                    field.setInitChecker(field.getPosX(), field.getPosY(), "yellow");
                }
                if (field.getPosX() - field.getPosY() >= size*3 + 2) {
                    field.setInitChecker(field.getPosX(), field.getPosY(), "blue");
                }
            }
            if (n == 4) {
                if (field.getPosY() - field.getPosX() >= size + 2) {
                    field.setInitChecker(field.getPosX(), field.getPosY(), "yellow");
                }
                if (field.getPosY() < size) {
                    field.setInitChecker(field.getPosX(), field.getPosY(), "blue");
                }
                if (field.getPosX() - field.getPosY() >= size*3 + 2) {
                    field.setInitChecker(field.getPosX(), field.getPosY(), "orange");
                }
            }
            if (n == 6){
                if (field.getPosY() - field.getPosX() >= size + 2) {
                    field.setInitChecker(field.getPosX(), field.getPosY(), "yellow");
                }
                if (field.getPosX() + field.getPosY() <= size*3 - 2) {
                    field.setInitChecker(field.getPosX(), field.getPosY(), "blue");
                }
                if (field.getPosY() < size) {
                    field.setInitChecker(field.getPosX(), field.getPosY(), "orange");
                }
                if (field.getPosX() - field.getPosY() >= size*3 + 2) {
                    field.setInitChecker(field.getPosX(), field.getPosY(), "grey");
                }
                if (field.getPosX() + field.getPosY() >= size*7 +2) {
                    field.setInitChecker(field.getPosX(), field.getPosY(), "white");
                }
            }
        }
    }


}
