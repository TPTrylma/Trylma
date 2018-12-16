package bin;

import bin.Board.Checker;
import bin.Board.Field;

import java.util.ArrayList;
import java.util.List;

import static bin.Trylma.board;
import static java.lang.Math.random;

public class Bot {
    List<Checker> checkers;
    int position;
    private Field dirField;
    public Bot(List<Checker> checkers, int position){
        this.checkers=checkers;
        this.position=position;
    }
    private void direction(){
        if(position==3){
            dirField=board.getField(board.getSize()*3, board.getSize()*4);
        }
        else {
            dirField=board.getField(board.getSize()*3, 0);
        }
    }
    private int dirX(Checker c, int n){
        if(c.getX()>dirField.getPosX()) return -n;
        else if(c.getX()<dirField.getPosX()) return n;
        else {
            if(random()>0.5) return n;
            else return -n;
        }
    }
    private int dirY(Checker c, int n){
        if(c.getY()>dirField.getPosY()) return -n;
        else if(c.getY()<dirField.getPosY()) return n;
        else {
            if(random()>0.5) return n;
            else return -n;
        }
    }
    public void move(){
        direction();
        int checker_jump=0;
        Checker checker = chooseChecker();
        System.out.println("Checker is "+checker.getX()+" "+checker.getY());
        Field field;
        if(checker!=null) {
            while(jump(checker) != null) {
                field = jump(checker);
                board.move(checker.getX(), checker.getY(), field.getPosX(), field.getPosY());
                checker.setX(field.getPosX());
                checker.setY(field.getPosY());
                checker_jump++;
                System.out.println("Jump to "+checker.getX()+" "+checker.getY());
            }
            if(checker_jump>0) {board.nextPlayer();return;}
            field = checkAround(checker, 1);
            if (field == null) board.nextPlayer();
            else {
                board.move(checker.getX(), checker.getY(), field.getPosX(), field.getPosY());
                checker.setX(field.getPosX());
                checker.setY(field.getPosY());
                System.out.println("Move to "+checker.getX()+" "+checker.getY());
            }

        } else {
            board.nextPlayer();
        }
    }
    private List<Checker> randCheckers(){
        List<Checker> c = new ArrayList<>();
        for(Checker checker : checkers){
            if(jump(checker) != null) c.add(checker);
            System.out.println("Jumping checker added "+checker.getX()+" "+checker.getY());
        }
        for (Checker checker : checkers){
            if(!c.contains(checker)) c.add(checker);
        }
        return c;
    }
    
    private Checker chooseChecker(){
        for(Checker checker : randCheckers()){
            //if(!checkFront(board.getField(checker.getX(), checker.getY()), checker)) { continue;}
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
    
    public boolean checkFront(Field field, Checker checker) {
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
        try{if((board.getField(checker.getX()+dirX(checker, n), checker.getY()+dirY(checker, n))).getChecker()==null &&
                checkPosition(board.getField(checker.getX()+dirX(checker, n), checker.getY()+dirY(checker, n)))) {
            return board.getField(checker.getX()+dirX(checker, n), checker.getY()+dirY(checker, n));
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
    private boolean jumpCheck(Field field){
        if(field.getChecker()!=null) return true;
        return false;
    }
    
    private Field jump(Checker checker){
        try{
            if((board.getField(checker.getX()+dirX(checker, 2), checker.getY()+dirY(checker, 2))).getChecker()==null &&
//                    checkAround(checker,2)!=null &&
                    jumpCheck(board.getField(checker.getX()+dirX(checker, 1), checker.getY()+dirY(checker, 1))) &&
                    checkPosition(board.getField(checker.getX()+dirX(checker, 2), checker.getY()+dirY(checker, 2))))
                return board.getField(checker.getX()+dirX(checker, 2), checker.getY()+dirY(checker, 2));
        }catch (Exception e){}
        return null;
    }
    
    private boolean checkPosition(Field field) {
        if(field.getPosX()<=board.getSize()*4 && field.getPosX()>=board.getSize()*2) return true;
        return false;
    }
    
}
