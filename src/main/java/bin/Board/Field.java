package bin.Board;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Field extends Circle {

    Checker checker;
    Board board;

    Field(int posX, int posY, String checker, Board board){
        this.posX = posX;
        this.posY = posY;
        if (!checker.equals("n")){
            this.checker = new Checker(posX, posY, checker);
        }
        this.board = board;
        setRadius(27);
        relocate(posX*27, posY*47);
        setFill(Color.GRAY);
    }
    public String getInfo(){
        if (checker == null) return "field pos: " + posX + " " + posY;
        return "field pos: " + posX + " " + posY + " checker: " + checker.getInfo();
    }
    int posX;
    int posY;


    void setInitChecker(int posX, int posY, String checker){
        this.checker = new Checker(posX, posY, checker);
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public Checker getChecker(){
        return checker;
    }

    public Board getBoard() {
        return board;
    }

    public void setChecker(Checker checker){
        this.checker = checker;
    }
}
