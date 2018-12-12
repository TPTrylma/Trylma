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

            if (from.getChecker() != from.getBoard().getTouchedChecker() && from.getBoard().getTouchedChecker() != null) {
                System.out.println("Wrong checker");
                visualMove(from, from);
                return 0;
            }

            if ((Math.abs(from.getPosX() - to.getPosX()) + Math.abs(from.getPosY() - to.getPosY()) == 2 && Math.abs(from.getPosY() - to.getPosY()) <= 1)) {
                if (from.getBoard().getTouchedChecker() != null) {
                    System.out.println("only jump!");
                    visualMove(from, from);
                    return 0;
                }
                visualMove(from, to);
                return 1;
            }

            //jump
            else if ((Math.abs(from.getPosX() - to.getPosX()) + Math.abs(from.getPosY() - to.getPosY()) == 4 && Math.abs(from.getPosY() - to.getPosY()) <= 2)) {
                if (from.getBoard().getField((from.getPosX() + to.getPosX()) / 2, (from.getPosY() + to.getPosY()) / 2).getChecker() != null) {
                    if (from.getBoard().getTouchedChecker() == null) {
                        from.getBoard().setTouchedChecker(from.getChecker());
                    }
                    visualMove(from, to);
                    return 2;
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
