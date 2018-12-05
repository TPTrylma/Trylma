import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Trylma {

    public static void main(String[] args) {

        Board board = new Board(2, 2);

        System.out.println(board.getCurPlayer());
        System.out.println(board.getNextPlayer());
        System.out.println(board.getNextPlayer());
        System.out.println(board.getNextPlayer());
        System.out.println(board.getNextPlayer());
        System.out.println(board.getNextPlayer());
        System.out.println(board.getNextPlayer());
        System.out.println(board.getNextPlayer());
        System.out.println(board.getNextPlayer());
        System.out.println();
        board.playerWin(1);

        System.out.println(board.getNextPlayer());
        System.out.println(board.getNextPlayer());
        System.out.println(board.getNextPlayer());
        System.out.println(board.getNextPlayer());

        for (Field field : board.fields) {
            System.out.println(field.getInfo());
        }

    }

}
