package bin.Rules;

import bin.Board.Board;
import bin.Board.Field;

import java.util.ArrayList;
import java.util.List;

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
                if (isOneBetweenInMid(from, to)) {
                    visualMove(from, to);
                    return 1;
                }
                List<Field> fieldsBetween = new ArrayList<Field>();
            }
        }


        System.out.println("wrong move");
        return 0;
    }

    private boolean isOneBetweenInMid(Field from, Field to) {

        Board board = from.getBoard();

        int fromX = from.getPosX();
        int fromY = from.getPosY();
        int toX = to.getPosX();
        int toY = to.getPosY();

        int midX = (fromX + toX)/2;
        int midY = (fromY + toY)/2;

        int[] next;


        while ((fromX != toX) || (fromY != toY)) {
            next = whatNext(fromX, fromY, toX, toY);
            fromX = next[0];
            fromY = next[1];
            if (fromX == toX && fromY == toY) {
                break;
            }

            if (fromX == midX && fromY == midY) {
                if (board.getField(fromX, fromY).getChecker() == null) {
                    return false;
                }
            }

            if (fromX != midX || fromY != midY) {
                if (board.getField(fromX, fromY).getChecker() != null) {
                    return false;
                }
            }
        }

        return true;
    }

    private int[] whatNext(int fromX, int fromY, int toX, int toY) {
        int[] tmp = new int[2];
        if (fromX == toX) {
            tmp[0] = fromX;
            if (fromY > toY) {
                tmp[1] = fromY -2;
            } else {
                tmp[1] = fromY +2;
            }
        } else if (fromY == toY) {
            tmp[1] = fromY;
            if (fromX > toX) {
                tmp[0] = fromX -2;
            } else {
                tmp[0] = fromX +2;
            }
        } else {
            if (fromX > toX) {
                tmp[0] = fromX -1;
            } else {
                tmp[0] = fromX +1;
            }
            if (fromY > toY) {
                tmp[1] = fromY -1;
            } else {
                tmp[1] = fromY +1;
            }
        }
        return tmp;
    }

}