import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

        fillingData(size);
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

    void fillingData(int size) {
        String file = "src/main/java/boards/board_" + maxP + "_" + size + ".txt";
        try {
            Scanner scanner = new Scanner(new File(file));

            for (int i = 0; i < maxP; i++) {
                players.add(scanner.next());
            }

            while (scanner.hasNext()){
                fields.add(new Field(scanner.nextInt(), scanner.nextInt(), scanner.next()));
            }
            scanner.close();
        } catch (IOException e){
        }
    }

}
