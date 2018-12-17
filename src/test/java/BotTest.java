/**
 * Copyright 2011 Joao Miguel Pereira
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import static org.junit.Assert.*;

import bin.Board.Board;
import bin.Rules.ClassicRules;
import bin.Trylma;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author jpereira
 *
 */
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