import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Trylma {

    public static void main(String[] args) {

        int size = 5;
        Board board = new Board(4, size);

        for (int i = 0; i <= size*4; i++) {
            for (int j = 0; j <= size*6; j++) {
                if (board.fieldArr[j][i] == null) System.out.print("-");
                else if (board.fieldArr[j][i].checker == null) System.out.print("+");
                else System.out.print(board.fieldArr[j][i].getChecker().getColor());
            }
            System.out.println();
        }
    }

}
