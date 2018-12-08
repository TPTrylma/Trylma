package Board;

import javax.swing.*;
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
        setRadius(24);
        relocate(posX*30+6, posY*55+6);
        if(color=="o") setFill(javafx.scene.paint.Color.ORANGE);
        else if(color=="g") setFill(javafx.scene.paint.Color.GREEN);
        else if(color=="b") setFill(javafx.scene.paint.Color.BLUE);
        else if(color=="r") setFill(javafx.scene.paint.Color.BROWN);
        else if(color=="k") setFill(javafx.scene.paint.Color.BLACK);
        else if(color=="p") setFill(javafx.scene.paint.Color.PINK);
    }


    String getInfo(){
        return "pos: " + posX + " " + posY + " color: " + color;
    }

    public String getColor() {
        return color;
    }

    public void paint (Graphics g) {
        int y=80;
        int x=50;
        if(color.equals("o")) g.setColor(Color.ORANGE);                                       // !!!!!!!!!!!!!!!!!!!!!EQUALS!!!!!!!!!!!!
        else if(color=="g") g.setColor(Color.GREEN);
        else if(color=="b") g.setColor(Color.BLUE);
        else if(color=="r") g.setColor(Color.RED);
        else if(color=="k") g.setColor(Color.BLACK);
        else if(color=="p") g.setColor(Color.PINK);
        g.fillOval(posX * 30 +8+ x, y +8+ posY * 55, 45, 45);
    }

    /*public void paintComponent(Graphics g1) {
        super.paintComponent(g1);
        Graphics2D g = (Graphics2D) g1;
        for (Figura f : figury) f.draw(g);
        for (int i = 1; i < coordsOfPolygonsX.size(); i++) {
            g.drawLine(coordsOfPolygonsX.get(i - 1), coordsOfPolygonsY.get(i - 1), coordsOfPolygonsX.get(i), coordsOfPolygonsY.get(i));
        }
    }*/

}
