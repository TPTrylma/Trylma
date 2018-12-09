package bin.Board;


import javafx.scene.shape.Circle;

import static bin.Trylma.board;

public class Checker extends Circle {

    int posX;
    int posY;
    String color;
    private double mouseX, mouseY;
    private double oldX, oldY;

    public Checker(int posX, int posY, String color){
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
        setOnMousePressed( e-> {
            mouseX=e.getSceneX();
            mouseY=e.getSceneY();

        });
        setOnMouseDragged(e->{
            relocate(e.getSceneX()-mouseX+oldX, e.getSceneY()-mouseY+oldY);
        });
        setOnMouseReleased(e->{
            board.rules.move(checkPosition(oldX , oldY),
                    checkPosition(e.getSceneX()-mouseX+oldX, e.getSceneY()-mouseY+oldY));
        });
    }


    String getInfo(){
        return "pos: " + posX + " " + posY + " color: " + color;
    }

    public String getColor() {
        return color;
    }
    public void move(int x, int y){
        oldX = x*27+5;
        oldY=y*47+5;
        relocate(oldX, oldY);
    }
    public Field checkPosition(double x, double y){
        int newX=(int)(x)/27;
        int newY=(int)(y)/47;
        return board.fieldArr[newX][newY];
    }
}
