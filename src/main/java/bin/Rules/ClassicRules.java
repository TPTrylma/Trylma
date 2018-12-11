package bin.Rules;

import bin.Board.*;

public class ClassicRules extends Rules {

    public int move(Field from, Field to) {
        try {
            if (from.getChecker() == null) {
                System.out.println("Field is empty");
                visualMove(from, from);
                return 0;
            }

            if (!from.getChecker().getColor().equals(from.getBoard().getCurPlayer())) {
                System.out.println("not your turn (or checker)");
                visualMove(from, from);
                return 0;
            }

            if (to.getChecker() != null) {
                System.out.println("new fild is occupied");
                visualMove(from, from);
                return 0;
            }

            if ((Math.abs(from.getPosX() - to.getPosX()) + Math.abs(from.getPosY() - to.getPosY()) == 2 && Math.abs(from.getPosY() - to.getPosY()) <= 1)) {
                visualMove(from, to);
                return 1;
            }

            //jump
            else if ((Math.abs(from.getPosX() - to.getPosX()) + Math.abs(from.getPosY() - to.getPosY()) == 4 && Math.abs(from.getPosY() - to.getPosY()) <= 2)) {
                if (from.getBoard().getField((from.getPosX() + to.getPosX()) / 2, (from.getPosY() + to.getPosY()) / 2).getChecker() != null) {
                    visualMove(from, to);
                    return 1;
                }
            }
        }catch (NullPointerException e){
            visualMove(from, from);
        }
        System.out.println("wrong move");
        visualMove(from, from);
        return 0;
    }
}
