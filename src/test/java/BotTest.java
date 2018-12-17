import static org.junit.Assert.*;

import bin.Board.Board;
import bin.Board.Checker;
import bin.Rules.ClassicRules;
import bin.Trylma;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class BotTest {
    Board board;
    Trylma trylma;
    @Before
    public void addBoard(){
        int players = 2, size =2;
        board = new Board(players, size, new ClassicRules(), 0);
        trylma.board=board;
        board.addBot(0);
        board.addBot(3);

    }
    @Test
    public void botGameTest(){
        int i=0;
        int players = board.getPlaying();
        while (true) {
            board.bot0.move();
            board.bot3.move();
            if(players!=board.getPlaying()) break;
            if(i>=200) fail();
            i++;
        }
    }
    @Test
    public void botDirectionTest(){
        board.bot0.direction();
        board.bot3.direction();
        assertEquals(board.getField(6,0), board.bot0.dirField);
        assertEquals(board.getField(6,8), board.bot3.dirField);
    }
    @Test
    public void randCheckerTest(){
        assertNotNull(board.bot0.randCheckers());
        assertNotNull(board.bot3.randCheckers());
    }
}