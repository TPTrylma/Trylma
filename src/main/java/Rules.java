abstract class Rules {


    abstract int move(Field from, Field to);

    void visualMove(Field from, Field to){
        Checker tmp = from.getChecker();
        from.setChecker(null);
        to.setChecker(tmp);
    }

}