import java.util.ArrayList;
import java.util.List;

public class Board {

    List<String> list;

    int maxP;
    int p;
    int curP;

    Board(int players){
        list = new ArrayList<String>();
        for (int i = 0; i < players; i++) {
            list.add("player " + i);
        }
        maxP = players;
        p = players;
        curP = 1; //random
    }

    String getCurPlayer(){
        return list.get(curP);
    }

    String getNextPlayer(){
        curP += 1;
        if (curP == p){
            curP = 0;
        }
        return list.get(curP);
    }

    void playerWin(int n){
        list.remove(n);
        p--;
    }

}
