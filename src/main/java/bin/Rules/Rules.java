package bin.Rules;

import bin.Board.*;

public abstract class Rules {


    public abstract int move(Field from, Field to);

    void visualMove(Field from, Field to){
        Checker tmp = from.getChecker();
        System.out.println(from.getPosX()+" "+from.getPosY()+" "+from.getChecker());
        from.setChecker(null);
        to.setChecker(tmp);
        tmp.move(to.getPosX(), to.getPosY());
        System.out.println(from.getPosX() + " " + from.getPosY() + "  ->  " + to.getPosX() + " " + to.getPosY()+" " +to.getChecker());
    }

}