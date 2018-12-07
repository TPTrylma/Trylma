import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Field extends Circle {

    Checker checker;

    public Field(int posX, int posY, String checker){
        this.posX = posX;
        this.posY = posY;
        if (!checker.equals("n")){
            this.checker = new Checker(posX, posY, checker);
        }
        setRadius(30);
        relocate(posX*30, posY*55);
        setFill(Color.GRAY);
    }

    String getInfo(){
        if (checker == null) return "field pos: " + posX + " " + posY;
        return "field pos: " + posX + " " + posY + " checker: " + checker.getInfo();
    }
    int posX;
    int posY;


    void setInitChecker(int posX, int posY, String checker){
        this.checker = new Checker(posX, posY, checker);
        //checkers.add(this.checker);
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

    public void setChecker(Checker checker){
        this.checker = checker;
    }
}
