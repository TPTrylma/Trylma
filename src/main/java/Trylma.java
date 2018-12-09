import Board.Board;
import Rules.ClassicRules;
import Rules.LongJumpRules;


public class Trylma {
    public static void main(String[] args) {

        int size = 4;
        Board board = new Board(6, size, new LongJumpRules());
        //launch(Board.Board.class);

        prin(size, board);
        board.move(15, 3, 14, 4);
        prin(size, board);
        board.move(18, 4, 10, 4);
        prin(size, board);


    }
    static void prin(int size, Board board){
        for (int i = 0; i <= size*4; i++) {
            for (int j = 0; j <= size*6; j++) {
                if (board.getField(j, i) == null) System.out.print(" ");
                else if (board.getField(j, i).getChecker() == null) System.out.print("-");
                else System.out.print(board.getField(j, i).getChecker().getColor());
            }
            System.out.println();
        }
    }

}
