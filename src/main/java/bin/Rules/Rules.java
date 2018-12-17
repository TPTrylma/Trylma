package bin.Rules;

import bin.Board.Checker;
import bin.Board.Field;

public abstract class Rules {


    public abstract int move(Field from, Field to);

    void visualMove(Field from, Field to){
        Checker tmp = from.getChecker();
        from.setChecker(null);
        to.setChecker(tmp);

        tmp.move(to.getPosX(), to.getPosY());
    }

}