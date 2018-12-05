public class Checker {

    int posX;
    int posY;
    String color;

    Checker(int posX, int posY, String color){
        this.posX = posX;
        this.posY = posY;
        this.color = color;
    }

    String getInfo(){
        return "pos: " + posX + " " + posY + " color: " + color;
    }

}
