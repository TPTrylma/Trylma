import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.abs;

public class Trylma {

    public static void main(String[] args) {

        int size = 2;
        Board board = new Board(6, size);

        prin(size, board);
        board.move(6, 0, 8, 2);
        prin(size, board);
        board.move(10, 2, 6, 2);
        prin(size, board);
        board.move(8, 2, 6, 0);
        prin(size, board);
        board.move(6, 0, 6, 4);
        prin(size, board);
    }

    static void prin(int size, Board board){
        for (int i = 0; i <= size*4; i++) {
            for (int j = 0; j <= size*6; j++) {
                if (board.fieldArr[j][i] == null) System.out.print(" ");
                else if (board.fieldArr[j][i].checker == null) System.out.print("-");
                else System.out.print(board.fieldArr[j][i].getChecker().getColor());
            }
            System.out.println();
        }
    }

}
