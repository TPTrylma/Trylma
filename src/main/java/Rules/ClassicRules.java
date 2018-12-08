package Rules;

import Board.*;

import static java.lang.Math.abs;

public class ClassicRules extends Rules {

    public int move(Field from, Field to) {

        if (from.getChecker() == null) {
            System.out.println("tuta pusto");
            return 0;
        }
        //if (from.getChecker().getColor() != curP) ...
        if (to.getChecker() != null) {
            System.out.println("zanjato");
            return 0;
        }

        if ((Math.abs(from.getPosX()-to.getPosX()) + Math.abs(from.getPosY()-to.getPosY()) == 2 && Math.abs(from.getPosY() - to.getPosY()) <= 1)) {
            visualMove(from, to);
            return 1;
        }
        //jump
        else if ((Math.abs(from.getPosX()-to.getPosX()) + Math.abs(from.getPosY()-to.getPosY()) == 4 && Math.abs(from.getPosY() - to.getPosY()) <= 2)) {
            if (from.getBoard().getField((from.getPosX()+to.getPosX())/2, (from.getPosY()+to.getPosY())/2).getChecker() != null) {
                visualMove(from, to);
                return 1;
            }
        }

        System.out.println("daleko");
        return 0;
    }
}