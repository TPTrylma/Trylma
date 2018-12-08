package Rules;

import Board.Field;

public class LongJumpRules extends Rules {


    public int move(Field from, Field to) {
        if (from.getChecker() == null) {
            System.out.println("Field is empty");
            return 0;
        }

        //if (from.getChecker().getColor() != curP) ...

        if (to.getChecker() != null) {
            System.out.println("new fild is occupied");
            return 0;
        }

        if ((Math.abs(from.getPosX()-to.getPosX()) + Math.abs(from.getPosY()-to.getPosY()) == 2 && Math.abs(from.getPosY() - to.getPosY()) <= 1)) {
            visualMove(from, to);
            return 1;
        }

        //long jump
        if (Math.abs((from.getPosX()-to.getPosX()) + Math.abs(from.getPosY()-to.getPosY())) % 4 == 0) {
            if (Math.abs(from.getPosX() - to.getPosX()) == 0 || Math.abs(from.getPosY() - to.getPosY()) == 0 ||
                    Math.abs(from.getPosX() - to.getPosX()) - Math.abs(from.getPosY() - to.getPosY()) == 0) {
                if (from.getBoard().getField((from.getPosX()+to.getPosX())/2, (from.getPosY()+to.getPosY())/2).getChecker() != null) {
                    visualMove(from, to);
                    return 1;
                }
            }
        }


        System.out.println("wrong move");
        return 0;
    }
}
