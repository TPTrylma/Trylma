import Board.Board;
import Rules.ClassicRules;


public class Trylma {
    public static void main(String[] args) {

        int size = 2;
        Board board = new Board(6, size, new ClassicRules());
        //launch(Board.Board.class);
        prin(size, board);
        board.move(6, 0, 7, 1);
        prin(size, board);
        board.move(6, 0, 8, 2);
        prin(size, board);
        board.move(10, 2, 6, 2);
        prin(size, board);
    }
    static void prin(int size, Board board){
        System.out.println(" 0123456789012");
        for (int i = 0; i <= size*4; i++) {
            System.out.print(i);
            for (int j = 0; j <= size*6; j++) {
                if (board.getField(j, i) == null) System.out.print(" ");
                else if (board.getField(j, i).getChecker() == null) System.out.print("-");
                else System.out.print(board.getField(j, i).getChecker().getColor());
            }
            System.out.println();
        }
    }

}
