package bin;

import bin.Board.Checker;
import bin.Board.Field;

import java.util.List;

import static bin.Trylma.board;

public class Bot {
    List<Checker> checkers;
    String color;

    public Bot(List<Checker> checkers, String color){
        this.checkers=checkers;
        this.color=color;
    }
    public void move(){
        Field field;
        Checker checker = chooseChecker();
        if(jump(checker)!=null){
            field = jump(checker);
            board.move(checker.posX, checker.posY, field.getPosX(), field.getPosY());
            board.nextPlayer();
        }else {
            field = checkAround(checker, 1);
            board.move(checker.posX, checker.posY, field.getPosX(), field.getPosY());
        }
        System.out.println(checker.posX+" "+ checker.posY+" "+ field.getPosX()+" "+ field.getPosY());
        checker.posX=field.getPosX();
        checker.posY=field.getPosY();
    }
    private Checker chooseChecker(){
        for(Checker checker : checkers){
            try{
                if((board.getField(checker.posX+1, checker.posY+1)).getChecker()==null && checkAround(checker,1)!=null) return checker;
            }catch (Exception e){}
            try{
                if((board.getField(checker.posX-1, checker.posY+1)).getChecker()==null && checkAround(checker,1)!=null) return checker;
            }catch (Exception e){}
            try{
                if((board.getField(checker.posX-1, checker.posY-1)).getChecker()==null && checkAround(checker,1)!=null) return checker;
            }catch (Exception e){}
            try {
                if((board.getField(checker.posX+1, checker.posY-1)).getChecker()==null && checkAround(checker,1)!=null) return checker;
            }catch (Exception e){}
            try {
                if((board.getField(checker.posX+1, checker.posY)).getChecker()==null && checkAround(checker,1)!=null) return checker;
            }catch (Exception e){}
            try {
                if((board.getField(checker.posX-1, checker.posY)).getChecker()==null && checkAround(checker,1)!=null) return checker;
            }catch (Exception e){}
        }
        System.out.println("UPS!");
        return checkers.get(0);
    }
    private Field checkAround(Checker checker, int n){
        if((board.getField(checker.posX+n, checker.posY+n)).getChecker()==null) {
            return board.getField(checker.posX+n, checker.posY+n);
        }
        if((board.getField(checker.posX-n, checker.posY+n)).getChecker()==null) {
            return board.getField(checker.posX-n, checker.posY+n);
        }
        return null;
    }
    private boolean jumpCheck(Checker checker, Field field){
        if(field.getChecker()!=null) return true;
        return false;
    }
    private Field jump(Checker checker){
        try{
            if((board.getField(checker.posX+2, checker.posY+2)).getChecker()==null && checkAround(checker,2)!=null
                    && jumpCheck(checker, board.getField(checker.posX+1, checker.posY+1)))
                return board.getField(checker.posX+2, checker.posY+2);
        }catch (Exception e){}
        try{
            if((board.getField(checker.posX-2, checker.posY+2)).getChecker()==null && checkAround(checker,2)!=null &&
                    jumpCheck(checker, board.getField(checker.posX-1, checker.posY+1)))
                return board.getField(checker.posX-2, checker.posY+2);
        }catch (Exception e){}
        /*try{
            if((board.getField(checker.posX-2, checker.posY-2)).getChecker()==null && checkAround(checker,2)!=null &&
                    jumpCheck(checker, board.getField(checker.posX-1, checker.posY-1)))
                return board.getField(checker.posX-2, checker.posY-2);
        }catch (Exception e){}*/
        /*try {
            if((board.getField(checker.posX+2, checker.posY-2)).getChecker()==null && checkAround(checker,2)!=null &&
                    jumpCheck(checker, board.getField(checker.posX+1, checker.posY-1)))
                return board.getField(checker.posX+2, checker.posY-2);
        }catch (Exception e){}*/
        try {
            if((board.getField(checker.posX+2, checker.posY)).getChecker()==null && checkAround(checker,2)!=null &&
                    jumpCheck(checker, board.getField(checker.posX+1, checker.posY)))
                return board.getField(checker.posX+2, checker.posY);
        }catch (Exception e){}
        try {
            if((board.getField(checker.posX-2, checker.posY)).getChecker()==null && checkAround(checker,2)!=null &&
                    jumpCheck(checker, board.getField(checker.posX-1, checker.posY)))
                return board.getField(checker.posX-2, checker.posY);
        }catch (Exception e){}
        System.out.println("Bad");
        return null;
    }
}
