public class Field {

    Checker checker;
    int posX;
    int posY;

    Field(int posX, int posY, String checker){
        this.posX = posX;
        this.posY = posY;
        if (!checker.equals("n")){
            this.checker = new Checker(posX, posY, checker);
        }
    }

    String getInfo(){
        if (checker == null) return "field pos: " + posX + " " + posY;
        return "field pos: " + posX + " " + posY + " checker: " + checker.getInfo();
    }

}
