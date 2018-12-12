package bin.Board;


import javafx.scene.shape.Circle;

import static bin.Trylma.board;

public class Checker extends Circle {

    public int posX;
    public int posY;
    String color;
    private double mouseX, mouseY;
    private double oldX, oldY;

    public Checker(int posX, int posY, String color) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
        setRadius(22);
        if(color.equals("0")) setFill(javafx.scene.paint.Color.ORANGE);
        else if(color.equals("1")) setFill(javafx.scene.paint.Color.GREEN);
        else if(color.equals("2")) setFill(javafx.scene.paint.Color.BLUE);
        else if(color.equals("3")) setFill(javafx.scene.paint.Color.BROWN);
        else if(color.equals("4")) setFill(javafx.scene.paint.Color.BLACK);
        else if(color.equals("5")) setFill(javafx.scene.paint.Color.PINK);
        move(posX, posY);
        setOnMousePressed( e-> {
            mouseX=e.getSceneX();
            mouseY=e.getSceneY();

        });
        setOnMouseDragged(e-> {
            relocate(e.getSceneX()-mouseX+oldX, e.getSceneY()-mouseY+oldY);
        });
        setOnMouseReleased(e-> {
            board.move(getXpos(oldX), getYpos(oldY), getXpos(e.getSceneX()-mouseX+oldX), getYpos(e.getSceneY()-mouseY+oldY));
        });
    }


    String getInfo() {
        return "pos: " + posX + " " + posY + " color: " + color;
    }

    public String getColor() {
        return color;
    }

    public void move(int x, int y) {
        oldX = x*27+5;
        oldY=y*47+5;
        relocate(oldX, oldY);
    }


    public int getXpos(double x) {
        return (int) x/27;
    }

    public int getYpos(double y) {
        return (int) y/47;
    }
}
