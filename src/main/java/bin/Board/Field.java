package bin.Board;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class Field extends Circle {

    private Checker checker;
    private Board board;
    static int fieldSizeX, fieldSizeY;
    Field(int posX, int posY, Board board){
        this.posX = posX;
        this.posY = posY;

        this.board = board;
        setRadius(27);
        fieldSizeX=27;
        fieldSizeY=47;
        relocate(posX*fieldSizeX, posY*fieldSizeY);
        setFill(Color.GRAY);
    }
    public String getInfo(){
        if (checker == null) return "field pos: " + posX + " " + posY;
        return "field pos: " + posX + " " + posY + " checker: " + checker.getInfo();
    }
    private int posX;
    private int posY;


    public void setInitChecker(int posX, int posY, int checker){
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
