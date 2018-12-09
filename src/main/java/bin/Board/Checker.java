package bin.Board;

import java.awt.*;
import javafx.scene.shape.Circle;

public class Checker extends Circle {

    int posX;
    int posY;
    String color;

    Checker(int posX, int posY, String color){
        this.posX = posX;
        this.posY = posY;
        this.color = color;
        setRadius(22);
        if(color.equals("o")) setFill(javafx.scene.paint.Color.ORANGE);
        else if(color.equals("g")) setFill(javafx.scene.paint.Color.GREEN);
        else if(color.equals("b")) setFill(javafx.scene.paint.Color.BLUE);
        else if(color.equals("r")) setFill(javafx.scene.paint.Color.BROWN);
        else if(color.equals("k")) setFill(javafx.scene.paint.Color.BLACK);
        else if(color.equals("p")) setFill(javafx.scene.paint.Color.PINK);
        move(posX, posY);
    }


    String getInfo(){
        return "pos: " + posX + " " + posY + " color: " + color;
    }

    public String getColor() {
        return color;
    }
    public void move(int x, int y){
        relocate(x*27+5, y*47+5);
    }

}
