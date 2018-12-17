import static org.junit.Assert.*;

import bin.Board.Board;
import bin.Board.Checker;
import bin.Rules.ClassicRules;
import bin.Rules.LongJumpRules;
import bin.Trylma;
import org.junit.Before;
import org.junit.Test;


public class BoardTest {

    Board board;
    Trylma trylma;
    int players = 6, size =3;
    @Before
    public void addBoard(){
        board = new Board(players, size, new ClassicRules(), 0);
        trylma.board=board;

    }
    @Test
    public void addCheckersTest(){
        int count=0;
        for(int i =1; i<=size; i++){
            count=count+i;
        }
        assertEquals(players*count, board.allCheckers.size());
    }
    @Test
    public void nextPlayerTest(){
        for(int i=0; i<6; i++) {
            int player = board.getCurPlayer();
            board.nextPlayer();
            assertEquals((player + 1) % players, board.getCurPlayer());
        }
    }
    @Test
    public void rightMoveTest(){
        board.setCurrentPlayer(3);
        board.move(7,2,6,3);
        assertNull(board.getField(7,2).getChecker());
        assertNotNull(board.getField(6,3).getChecker());
    }
    @Test
    public void emptyWrongMoveTest(){
        board.setCurrentPlayer(3);
        board.move(7,2,7,6);
        assertNull(board.getField(7,6).getChecker());
        assertNotNull(board.getField(7,2).getChecker());

    }
    @Test
    public void nonEmptyWrongMoveTest(){
        board.setCurrentPlayer(3);
        board.move(7,2,9,2);
        assertNotNull(board.getField(9,2).getChecker());
        assertNotNull(board.getField(7,2).getChecker());

    }
    @Test
    public void jumpClassicTest(){
        board.setCurrentPlayer(3);
        board.move(8,1,6,3);
        board.nextPlayer();
        board.setCurrentPlayer(3);
        board.move(10,1,12,3);
        assertNotNull(board.getField(6,3).getChecker());
        assertNotNull(board.getField(12,3).getChecker());

    }
    @Test
    public void jumpLongTest(){
        board = new Board(players, size, new LongJumpRules(), 0);
        board.setCurrentPlayer(3);
        board.getField(13,4).setChecker(new Checker(13,4,0));
        board.move(11,2,15,6);
        assertNotNull(board.getField(15,6).getChecker());

    }
}