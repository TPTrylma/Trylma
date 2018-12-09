package Rules;

import Board.*;

public abstract class Rules {


    public abstract int move(Field from, Field to);

    void visualMove(Field from, Field to){
        Checker tmp = from.getChecker();
        from.setChecker(null);
        to.setChecker(tmp);
        System.out.println(from.getPosX() + " " + from.getPosY() + "  ->  " + to.getPosX() + " " + to.getPosY());
    }

}