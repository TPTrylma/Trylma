package bin;

import bin.Board.Checker;
import bin.Board.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static bin.Trylma.board;
import static java.lang.Math.random;
import static java.lang.Thread.sleep;

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
        else if(position==0){
            dirField=board.getField(board.getSize()*3, 0);
        }
        else if(position==2){
            dirField=board.getField(board.getSize()*6, board.getSize()*3);
        }
        else if(position==4){
            dirField=board.getField(0, board.getSize()*3);
        }else if(position==1){
            dirField=board.getField(board.getSize()*6, board.getSize());
        }else if(position==5){
            dirField=board.getField(0, board.getSize());
        }
    }
    private int dirX(Checker c, int n){
        if(position==0&&c.getX()==dirField.getPosX()){
            if(board.getField(c.getX()-n, c.getY()-n).getChecker()==null) return -n;
            if(board.getField(c.getX()+n, c.getY()-n).getChecker()==null) return n;
        }else if(position==3&&c.getX()==dirField.getPosX()){
            if(board.getField(c.getX()-n, c.getY()+n).getChecker()==null) return -n;
            if(board.getField(c.getX()+n, c.getY()+n).getChecker()==null) return n;
        }
//        if(position==0 && n==1){
//            if(c.getX()>dirField.getPosX()&&board.getField(c.getX()-n, c.getY()-n).getChecker()!=null
//                    &&board.getField(c.getX()+n, c.getY()-n).getChecker()==null) return n;
//            if(c.getX()<dirField.getPosX()&&board.getField(c.getX()+n, c.getY()-n).getChecker()!=null
//                    &&board.getField(c.getX()-n, c.getY()-n).getChecker()==null) return -n;
//        }
//        if(position==3 && n==1){
//            if(c.getX()>dirField.getPosX()&&board.getField(c.getX()-n, c.getY()+n).getChecker()!=null
//                    &&board.getField(c.getX()+n, c.getY()+n).getChecker()==null) return n;
//            if(c.getX()<dirField.getPosX()&&board.getField(c.getX()+n, c.getY()+n).getChecker()!=null
//                    &&board.getField(c.getX()-n, c.getY()+n).getChecker()==null) return -n;
//        }
        if(c.getX()>dirField.getPosX()) return -n;
        else  return n;
    }
    private int dirY(Checker c, int n){
        if(c.getY()>=dirField.getPosY()) return -n;
        else  return n;
    }
    public void move(){
        try {
            sleep(1000);
        }catch (Exception e){}
        direction();
        System.out.println("Dir is "+dirField.getPosX()+" "+dirField.getPosY());
        int checker_jump=0;
        Checker checker = chooseChecker();
        if(checker!=null )System.out.println("Checker is "+checker.getX()+" "+checker.getY());
        Field field;
        if(checker!=null) {
            while(jump(checker) != null) {
                field = jump(checker);
                board.move(checker.getX(), checker.getY(), field.getPosX(), field.getPosY());
                checker.setX(field.getPosX());
                checker.setY(field.getPosY());
                checker_jump++;
                System.out.println("Jump to "+checker.getX()+" "+checker.getY());
                if(checker.getX()==dirField.getPosX()&&checker.getY()==dirField.getPosY()) break;
            }
            if(checker_jump>0) { board.nextPlayer();return;}
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
            if(jump(checker) != null && !(checker.getX()==dirField.getPosX() && checker.getY()==dirField.getPosY())) {c.add(checker);
            System.out.println("Jumping checker added "+checker.getX()+" "+checker.getY());}
        }
        for (Checker checker : checkers){
            if(checkAround(checker, 1)!=null) {c.add(checker);
            System.out.println("------------------------------");}
        }
        return c;
    }
    
    private Checker chooseChecker(){
        if(randCheckers().size()==0) return null;
        for(Checker checker : randCheckers()) {
            if (jump(checker) != null) return checker;
            try {
                if ((board.getField(checker.getX() + dirX(checker, 1), checker.getY() + dirY(checker, 1))).getChecker() == null &&
                        checkPosition(board.getField(checker.getX() + dirX(checker, 1), checker.getY() + dirY(checker, 1))))
                    return checker;
            } catch (Exception e) {
            }
            if (position == 2 || position==1 || position==5 || position==4) {
                try {
                    if ((board.getField(checker.getX() + dirX(checker, 2), checker.getY())).getChecker() == null &&
                    checkPosition(board.getField(checker.getX() + dirX(checker, 2), checker.getY()))) return checker;
                } catch (Exception e) {
                }
            }
        }
        return null;
    }

    private Field checkAround(Checker checker, int n){
        try{if((board.getField(checker.getX()+dirX(checker, n), checker.getY()+dirY(checker, n))).getChecker()==null &&
                checkPosition(board.getField(checker.getX()+dirX(checker, n), checker.getY()+dirY(checker, n)))) {
            return board.getField(checker.getX()+dirX(checker, n), checker.getY()+dirY(checker, n));
        }}catch (Exception e){}
        if(position == 2 || position==1 || position==5 || position==4) {
            try {
                if ((board.getField(checker.getX() + dirX(checker, 2) , checker.getY())).getChecker() == null &&
                        checkPosition(board.getField(checker.getX() +dirX(checker, 2), checker.getY()))) {
                    return board.getField(checker.getX() + dirX(checker, 2), checker.getY());
                }
            } catch (Exception e) {
            }
        }
        return null;
    }
    private boolean jumpCheck(Field field){
        if(field.getChecker()!=null) return true;
        return false;
    }
    
    private Field jump(Checker checker){

        try{
            if((board.getField(checker.getX()+dirX(checker, 2), checker.getY()+dirY(checker, 2))).getChecker()==null &&
                    jumpCheck(board.getField(checker.getX()+dirX(checker, 1), checker.getY()+dirY(checker, 1))) &&
                    checkPosition(board.getField(checker.getX()+dirX(checker, 2), checker.getY()+dirY(checker, 2))))
                return board.getField(checker.getX()+dirX(checker, 2), checker.getY()+dirY(checker, 2));
        }catch (Exception e){}
        if(position == 2 || position==1 || position==5 || position==4){
            try{
                if((board.getField(checker.getX()+dirX(checker, 2)*2, checker.getY())).getChecker()==null &&
                        jumpCheck(board.getField(checker.getX()+dirX(checker, 1)*2, checker.getY())) &&
                        checkPosition(board.getField(checker.getX()+dirX(checker, 2)*2, checker.getY())))
                    return board.getField(checker.getX()+dirX(checker, 2)*2, checker.getY());
            }catch (Exception e){}
        }
        return null;
    }
    
    private boolean checkPosition(Field field) {
        if(position==0||position==3) {
            if (field.getPosX() <= board.getSize() * 4 && field.getPosX() >= board.getSize() * 2) return true;
        }
        else if(position==2){
            if(field.getPosY()>=board.getSize() && ((field.getPosY()<=board.getSize()*3 && field.getPosX()>=board.getSize()*4)||
                    field.getPosY()<board.getSize()*3 && field.getPosX()<board.getSize()*4)) return true;
        }
        else if(position==4){
            if(field.getPosY()>=board.getSize() && ((field.getPosY()<=board.getSize()*3 && field.getPosX()<=board.getSize()*2)||
                    field.getPosY()<board.getSize()*3 && field.getPosX()>board.getSize()*2)) return true;
        }else if(position==1){
            if(field.getPosY()<=board.getSize()*3 && ((field.getPosY()>=board.getSize() && field.getPosX()>=board.getSize()*4)||
                    field.getPosY()>board.getSize() && field.getPosX()<board.getSize()*4)) return true;
        }else if(position==5){
            if(field.getPosY()<=board.getSize()*3 && ((field.getPosY()>=board.getSize() && field.getPosX()<=board.getSize()*2)||
                    field.getPosY()>board.getSize() && field.getPosX()>board.getSize()*2)) return true;
        }
        return false;
    }
    
}
