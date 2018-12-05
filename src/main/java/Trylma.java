import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Trylma {

    public static void main(String[] args) {

        Board board = new Board(6, 4);

        for (Field field : board.fields) {
            System.out.println(field.getInfo());
        }

    }

}
