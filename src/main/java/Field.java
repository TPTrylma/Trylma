public class Field {

    Board board;
    Checker checker;
    int posX;
    int posY;

    Field(int posX, int posY, String checker, Board board){
        this.posX = posX;
        this.posY = posY;
        if (!checker.equals("n")){
            this.checker = new Checker(posX, posY, checker);
        }
        this.board = board;
    }

    String getInfo(){
        if (checker == null) return "field pos: " + posX + " " + posY;
        return "field pos: " + posX + " " + posY + " checker: " + checker.getInfo();
    }

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
