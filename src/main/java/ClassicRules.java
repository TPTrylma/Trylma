import static java.lang.Math.abs;

public class ClassicRules extends Rules {

    int move(Field from, Field to) {

        if (from.getChecker() == null) {
            System.out.println("tuta pusto");
            return 0;
        }
        //if (from.getChecker().getColor() != curP) ...
        if (to.getChecker() != null) {
            System.out.println("zanjato");
            return 0;
        }

        if ((abs(from.getPosX()-to.getPosX()) + abs(from.getPosY()-to.getPosY()) == 2 && abs(from.getPosY() - to.getPosY()) <= 1)) {
            visualMove(from, to);
            return 1;
        }
        //jump
        else if ((abs(from.getPosX()-to.getPosX()) + abs(from.getPosY()-to.getPosY()) == 4 && abs(from.getPosY() - to.getPosY()) <= 2)) {
            if (from.getBoard().getField((from.getPosX()+to.getPosX())/2, (from.getPosY()+to.getPosY())/2) != null) {
                visualMove(from, to);
                return 1;
            }
        }

        System.out.println("daleko");
        return 0;
    }
}
