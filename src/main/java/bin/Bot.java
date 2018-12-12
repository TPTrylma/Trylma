package bin;

import bin.Board.Checker;
import bin.Board.Field;

import java.util.List;

import static bin.Trylma.board;

public class Bot {
    List<Checker> checkers;
    String color;

    public Bot(List<Checker> checkers, String color) {
        this.checkers=checkers;
        this.color=color;
    }
    
    public void move() {
        Field field=null;
        Checker checker = chooseChecker();
        if(checker!=null) {
            if (jump(checker) != null) {
                field = jump(checker);
                board.move(checker.getPosX(), checker.getPosY(), field.getPosX(), field.getPosY());
                board.nextPlayer();
            } else {
                field = checkAround(checker, 1);
                if (field == null) board.nextPlayer();
                else board.move(checker.getPosX(), checker.getPosY(), field.getPosX(), field.getPosY());
            }
            System.out.println(checker.getPosX() + " " + checker.getPosY() + " " + field.getPosX() + " " + field.getPosY());
        }
        if(field!=null) {
            checker.setPosX(field.getPosX());
            checker.setPosY(field.getPosY());
        }
    }
    
    private Checker chooseChecker(){
        for(Checker checker : checkers){
            if(!checkFront(board.getField(checker.getPosX(), checker.getPosY()), checker)) continue;
            try{
                if((board.getField(checker.getPosX()+1, checker.getPosY()+1)).getChecker()==null && checkAround(checker,1)!=null) return checker;
            }catch (Exception e){}
            try{
                if((board.getField(checker.getPosX()-1, checker.getPosY()+1)).getChecker()==null && checkAround(checker,1)!=null) return checker;
            }catch (Exception e){}
            try{
                if((board.getField(checker.getPosX()-1, checker.getPosY()-1)).getChecker()==null && checkAround(checker,1)!=null) return checker;
            }catch (Exception e){}
            try {
                if((board.getField(checker.getPosX()+1, checker.getPosY()-1)).getChecker()==null && checkAround(checker,1)!=null) return checker;
            }catch (Exception e){}
            try {
                if((board.getField(checker.getPosX()+2, checker.getPosY())).getChecker()==null && checkAround(checker,1)!=null) return checker;
            }catch (Exception e){}
            try {
                if((board.getField(checker.getPosX()-2, checker.getPosY())).getChecker()==null && checkAround(checker,1)!=null) return checker;
            }catch (Exception e){}
        }
        System.out.println("UPS!");
        return null;
    }
    
    public boolean checkFront(Field field, Checker checker) {
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
    
    private Field checkAround(Checker checker, int n) {
        try{if((board.getField(checker.getPosX()+n, checker.getPosY()+n)).getChecker()==null && checkPosition(board.getField(checker.getPosX()+n, checker.getPosY()+n))) {
            return board.getField(checker.getPosX()+n, checker.getPosY()+n);
        }}catch (Exception e){}
        try {
        if((board.getField(checker.getPosX()-n, checker.getPosY()+n)).getChecker()==null && checkPosition(board.getField(checker.getPosX()-n, checker.getPosY()+n))) {
            return board.getField(checker.getPosX()-n, checker.getPosY()+n);
        }}catch (Exception e){}
        try {
        if((board.getField(checker.getPosX()+n*2, checker.getPosY())).getChecker()==null && checkPosition(board.getField(checker.getPosX()+n*2, checker.getPosY()))) {
            return board.getField(checker.getPosX()+n*2, checker.getPosY());
        }}catch (Exception e){}
        try {
            if((board.getField(checker.getPosX()-n*2, checker.getPosY())).getChecker()==null && checkPosition(board.getField(checker.getPosX()-n*2, checker.getPosY()))) {
                return board.getField(checker.getPosX()-n*2, checker.getPosY());
            }
        }catch (Exception e){}
        return null;
    }
    
    private boolean jumpCheck(Checker checker, Field field) {
        if(field.getChecker()!=null) return true;
        return false;
    }
    
    private Field jump(Checker checker){
        try{
            if((board.getField(checker.getPosX()+2, checker.getPosY()+2)).getChecker()==null && checkAround(checker,2)!=null &&
                    jumpCheck(checker, board.getField(checker.getPosX()+1, checker.getPosY()+1)) && checkPosition(board.getField(checker.getPosX()+2, checker.getPosY()+2)))
                return board.getField(checker.getPosX()+2, checker.getPosY()+2);
        }catch (Exception e){}
        try{
            if((board.getField(checker.getPosX()-2, checker.getPosY()+2)).getChecker()==null && checkAround(checker,2)!=null &&
                    jumpCheck(checker, board.getField(checker.getPosX()-1, checker.getPosY()+1)) && checkPosition(board.getField(checker.getPosX()-2, checker.getPosY()+2)))
                return board.getField(checker.getPosX()-2, checker.getPosY()+2);
        }catch (Exception e){}
        /*try{
            if((board.getField(checker.getPosX()-2, checker.getPosY()-2)).getChecker()==null && checkAround(checker,2)!=null &&
                    jumpCheck(checker, board.getField(checker.getPosX()-1, checker.getPosY()-1)))
                return board.getField(checker.getPosX()-2, checker.getPosY()-2);
        }catch (Exception e){}*/
        /*try {
            if((board.getField(checker.getPosX()+2, checker.getPosY()-2)).getChecker()==null && checkAround(checker,2)!=null &&
                    jumpCheck(checker, board.getField(checker.getPosX()+1, checker.getPosY()-1)))
                return board.getField(checker.getPosX()+2, checker.getPosY()-2);
        }catch (Exception e){}*/
        try {
            if((board.getField(checker.getPosX()+2, checker.getPosY())).getChecker()==null && checkAround(checker,2)!=null &&
                    jumpCheck(checker, board.getField(checker.getPosX()+1, checker.getPosY())) && checkPosition(board.getField(checker.getPosX()+2, checker.getPosY())))
                return board.getField(checker.getPosX()+2, checker.getPosY());
        }catch (Exception e){}
        try {
            if((board.getField(checker.getPosX()-2, checker.getPosY())).getChecker()==null && checkAround(checker,2)!=null &&
                    jumpCheck(checker, board.getField(checker.getPosX()-1, checker.getPosY())) && checkPosition(board.getField(checker.getPosX()-2, checker.getPosY())))
                return board.getField(checker.getPosX()-2, checker.getPosY());
        }catch (Exception e){}
        System.out.println("Bad");
        return null;
    }
    
    private boolean checkPosition(Field field) {
        if(field.getPosX()<=board.getSize()*4 && field.getPosX()>=board.getSize()*2) return true;
        return false;
    }
    
}
