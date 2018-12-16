package bin;

import bin.Board.Checker;
import bin.Board.Field;

import java.util.ArrayList;
import java.util.List;

import static bin.Trylma.board;

public class Bot {
    List<Checker> checkers;
    int position;

    public Bot(List<Checker> checkers, int position){
        this.checkers=checkers;
        this.position=position;
    }
    public void move(){
        int checker_jump=0;
        Checker checker = chooseChecker();
        System.out.println(checker);
        Field field;
        if(checker!=null) {
            while(jump(checker) != null) {
                field = jump(checker);
                board.move(checker.getX(), checker.getY(), field.getPosX(), field.getPosY());
                checker.setX(field.getPosX());
                checker.setY(field.getPosY());
                checker_jump++;
            }
            if(checker_jump>0) {board.nextPlayer();return;}
            field = checkAround(checker, 1);
            if (field == null) board.nextPlayer();
            else {
                board.move(checker.getX(), checker.getY(), field.getPosX(), field.getPosY());
                checker.setX(field.getPosX());
                checker.setY(field.getPosY());
            }

        } else {
            board.nextPlayer();
        }
    }
    private List<Checker> randCheckers(){
        List<Checker> c = new ArrayList<>();
        for(Checker checker : checkers){
            if(jump(checker) != null) c.add(checker);
        }
        for (Checker checker : checkers){
            if(!c.contains(checker)) c.add(checker);
        }
        return c;
    }
    private Checker chooseChecker(){
        for(Checker checker : randCheckers()){
            if(!checkFront(board.getField(checker.getX(), checker.getY()), checker)) {System.out.println("herovo"); continue;}
            try{
                if((board.getField(checker.getX()+1, checker.getY()+1)).getChecker()==null && checkAround(checker,1)!=null) return checker;
            }catch (Exception e){}
            try{
                if((board.getField(checker.getX()-1, checker.getY()+1)).getChecker()==null && checkAround(checker,1)!=null)
                    System.out.println(checkAround(checker,1));
                    return checker;
            }catch (Exception e){}
            try{
                if((board.getField(checker.getX()-1, checker.getY()-1)).getChecker()==null && checkAround(checker,1)!=null) return checker;
            }catch (Exception e){}
            try {
                if((board.getField(checker.getX()+1, checker.getY()-1)).getChecker()==null && checkAround(checker,1)!=null) return checker;
            }catch (Exception e){}
            try {
                if((board.getField(checker.getX()+2, checker.getY())).getChecker()==null && checkAround(checker,1)!=null) return checker;
            }catch (Exception e){}
            try {
                if((board.getField(checker.getX()-2, checker.getY())).getChecker()==null && checkAround(checker,1)!=null) return checker;
            }catch (Exception e){}
        }
        return null;
    }
    public boolean checkFront(Field field, Checker checker){
        try {
            if(board.getField(field.getPosX()+1, field.getPosY()+1).getChecker()==null)
                return true;
        }catch (Exception e){}
        try {
            if(board.getField(field.getPosX()-1, field.getPosY()+1).getChecker()==null)
                return true;
        }catch (Exception e){}
        try {
            if(jump(checker)!=null) return true;
        }catch (Exception e){}
        return false;
    }
    private Field checkAround(Checker checker, int n){
        try{if((board.getField(checker.getX()+n, checker.getY()+n)).getChecker()==null && checkPosition(board.getField(checker.getX()+n, checker.getY()+n))) {
            return board.getField(checker.getX()+n, checker.getY()+n);
        }}catch (Exception e){}
        try {
        if((board.getField(checker.getX()-n, checker.getY()+n)).getChecker()==null && checkPosition(board.getField(checker.getX()-n, checker.getY()+n))) {
            return board.getField(checker.getX()-n, checker.getY()+n);
        }}catch (Exception e){}
        try {
        if((board.getField(checker.getX()+n*2, checker.getY())).getChecker()==null && checkPosition(board.getField(checker.getX()+n*2, checker.getY()))) {
            return board.getField(checker.getX()+n*2, checker.getY());
        }}catch (Exception e){}
        try {
            if((board.getField(checker.getX()-n*2, checker.getY())).getChecker()==null && checkPosition(board.getField(checker.getX()-n*2, checker.getY()))) {
                return board.getField(checker.getX()-n*2, checker.getY());
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
            if((board.getField(checker.getX()+2, checker.getY()+2)).getChecker()==null && checkAround(checker,2)!=null &&
                    jumpCheck(checker, board.getField(checker.getX()+1, checker.getY()+1)) && checkPosition(board.getField(checker.getX()+2, checker.getY()+2)))
                return board.getField(checker.getX()+2, checker.getY()+2);
        }catch (Exception e){}
        try{
            if((board.getField(checker.getX()-2, checker.getY()+2)).getChecker()==null && checkAround(checker,2)!=null &&
                    jumpCheck(checker, board.getField(checker.getX()-1, checker.getY()+1)) && checkPosition(board.getField(checker.getX()-2, checker.getY()+2)))
                return board.getField(checker.getX()-2, checker.getY()+2);
        }catch (Exception e){}
        /*try{
            if((board.getField(checker.getX()-2, checker.posY-2)).getChecker()==null && checkAround(checker,2)!=null &&
                    jumpCheck(checker, board.getField(checker.getX()-1, checker.posY-1)))
                return board.getField(checker.getX()-2, checker.posY-2);
        }catch (Exception e){}*/
        /*try {
            if((board.getField(checker.getX()+2, checker.posY-2)).getChecker()==null && checkAround(checker,2)!=null &&
                    jumpCheck(checker, board.getField(checker.getX()+1, checker.posY-1)))
                return board.getField(checker.getX()+2, checker.posY-2);
        }catch (Exception e){}*/
        try {
            if((board.getField(checker.getX()+2, checker.getY())).getChecker()==null && checkAround(checker,2)!=null &&
                    jumpCheck(checker, board.getField(checker.getX()+1, checker.getY())) && checkPosition(board.getField(checker.getX()+2, checker.getY())))
                return board.getField(checker.getX()+2, checker.getY());
        }catch (Exception e){}
        try {
            if((board.getField(checker.getX()-2, checker.getY())).getChecker()==null && checkAround(checker,2)!=null &&
                    jumpCheck(checker, board.getField(checker.getX()-1, checker.getY())) && checkPosition(board.getField(checker.getX()-2, checker.getY())))
                return board.getField(checker.getX()-2, checker.getY());
        }catch (Exception e){}
        System.out.println("Bad");
        return null;
    }
    private boolean checkPosition(Field field){
        if(field.getPosX()<=board.getSize()*4 && field.getPosX()>=board.getSize()*2) return true;
        return false;
    }
}
