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
        Field field=null;
        Checker checker = chooseChecker();
        if(checker!=null) {
            if (jump(checker) != null) {
                field = jump(checker);
                board.move(checker.posX, checker.posY, field.getPosX(), field.getPosY());
                board.nextPlayer();
            } else {
                field = checkAround(checker, 1);
                if (field == null) board.nextPlayer();
                else board.move(checker.posX, checker.posY, field.getPosX(), field.getPosY());
            }
            System.out.println(checker.posX + " " + checker.posY + " " + field.getPosX() + " " + field.getPosY());
        }
        if(field!=null) {
            checker.posX = field.getPosX();
            checker.posY = field.getPosY();
        }
    }
    private Checker chooseChecker(){
        for(Checker checker : checkers){
            if(!checkFront(board.getField(checker.posX, checker.posY), checker)) continue;
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
                if((board.getField(checker.posX+2, checker.posY)).getChecker()==null && checkAround(checker,1)!=null) return checker;
            }catch (Exception e){}
            try {
                if((board.getField(checker.posX-2, checker.posY)).getChecker()==null && checkAround(checker,1)!=null) return checker;
            }catch (Exception e){}
        }
        System.out.println("UPS!");
        return null;
    }
    public boolean checkFront(Field field, Checker checker){
        try {
            if(board.getField(field.getPosX()+1, field.getPosY()+1).getChecker()==null) return true;
        }catch (Exception e){}
        try {
            if(board.getField(field.getPosX()-1, field.getPosY()+1).getChecker()==null) return true;
        }catch (Exception e){}
        try {
            if(jump(checker)!=null) return true;
        }catch (Exception e){}
        return false;
    }
    private Field checkAround(Checker checker, int n){
        try{if((board.getField(checker.posX+n, checker.posY+n)).getChecker()==null && checkPosition(board.getField(checker.posX+n, checker.posY+n))) {
            return board.getField(checker.posX+n, checker.posY+n);
        }}catch (Exception e){}
        try {
        if((board.getField(checker.posX-n, checker.posY+n)).getChecker()==null && checkPosition(board.getField(checker.posX-n, checker.posY+n))) {
            return board.getField(checker.posX-n, checker.posY+n);
        }}catch (Exception e){}
        try {
        if((board.getField(checker.posX+n*2, checker.posY)).getChecker()==null && checkPosition(board.getField(checker.posX+n*2, checker.posY))) {
            return board.getField(checker.posX+n*2, checker.posY);
        }}catch (Exception e){}
        try {
            if((board.getField(checker.posX-n*2, checker.posY)).getChecker()==null && checkPosition(board.getField(checker.posX-n*2, checker.posY))) {
                return board.getField(checker.posX-n*2, checker.posY);
            }
        }catch (Exception e){}
        return null;
    }
    private boolean jumpCheck(Checker checker, Field field){
        if(field.getChecker()!=null) return true;
        return false;
    }
    private Field jump(Checker checker){
        try{
            if((board.getField(checker.posX+2, checker.posY+2)).getChecker()==null && checkAround(checker,2)!=null &&
                    jumpCheck(checker, board.getField(checker.posX+1, checker.posY+1)) && checkPosition(board.getField(checker.posX+2, checker.posY+2)))
                return board.getField(checker.posX+2, checker.posY+2);
        }catch (Exception e){}
        try{
            if((board.getField(checker.posX-2, checker.posY+2)).getChecker()==null && checkAround(checker,2)!=null &&
                    jumpCheck(checker, board.getField(checker.posX-1, checker.posY+1)) && checkPosition(board.getField(checker.posX-2, checker.posY+2)))
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
                    jumpCheck(checker, board.getField(checker.posX+1, checker.posY)) && checkPosition(board.getField(checker.posX+2, checker.posY)))
                return board.getField(checker.posX+2, checker.posY);
        }catch (Exception e){}
        try {
            if((board.getField(checker.posX-2, checker.posY)).getChecker()==null && checkAround(checker,2)!=null &&
                    jumpCheck(checker, board.getField(checker.posX-1, checker.posY)) && checkPosition(board.getField(checker.posX-2, checker.posY)))
                return board.getField(checker.posX-2, checker.posY);
        }catch (Exception e){}
        System.out.println("Bad");
        return null;
    }
    private boolean checkPosition(Field field){
        if(field.getPosX()<=board.getSize()*4 && field.getPosX()>=board.getSize()*2) return true;
        return false;
    }
}
