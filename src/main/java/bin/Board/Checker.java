package bin.Board;


import bin.Trylma;
import javafx.scene.shape.Circle;

import static bin.Trylma.board;

public class Checker extends Circle {

    private int posX;
    private int posY;
    private int color;
    private double mouseX, mouseY;
    private double oldX, oldY;

    public Checker(int posX, int posY, int color) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
        setRadius(22);
        if(color == 0) { setFill(javafx.scene.paint.Color.ORANGE); }
        else if(color == 1) { setFill(javafx.scene.paint.Color.GREEN); }
        else if(color == 2) { setFill(javafx.scene.paint.Color.BLUE); }
        else if(color == 3) { setFill(javafx.scene.paint.Color.BROWN); }
        else if(color == 4) { setFill(javafx.scene.paint.Color.BLACK); }
        else if(color == 5) { setFill(javafx.scene.paint.Color.PINK); }
        move(posX, posY);
        setOnMousePressed( e-> {
            mouseX=e.getSceneX();
            mouseY=e.getSceneY();

        });
        setOnMouseDragged(e-> {
            relocate(e.getSceneX()-mouseX+oldX, e.getSceneY()-mouseY+oldY);
        });
        setOnMouseReleased(e-> {
            if (Trylma.color == -1) {
                board.move(getXpos(oldX), getYpos(oldY), getXpos(e.getSceneX() - mouseX + oldX),
                        getYpos(e.getSceneY() - mouseY + oldY));
            }
            if (Trylma.color == board.getCurPlayer()) {
                Trylma.out.println("MOVE " + Trylma.color + " " + getXpos(oldX) + " " + getYpos(oldY) + " " +
                        getXpos(e.getSceneX() - mouseX + oldX) + " " + getYpos(e.getSceneY() - mouseY + oldY));
            } else relocate(oldX, oldY);
        });
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int x) {
        posX = x;
    }

    public void setPosY(int y) {
        posY = y;
    }

    String getInfo() {
        return "pos: " + posX + " " + posY + " color: " + color;
    }

    public int getColor() {
        return color;
    }

    public void move(int x, int y) {
        oldX = x* Field.fieldSizeX +5;
        oldY=y*Field.fieldSizeY+5;
        relocate(oldX, oldY);
    }


    public int getXpos(double x) {
        return (int) x/27;
    }

    public int getYpos(double y) {
        return (int) y/47;
    }

    public int getX(){return posX;}
    public int getY(){return posY;}

    public void setX(int x){posX=x;}
    public void setY(int y){posY=y;}
}
