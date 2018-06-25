package com.sh.tr.toyrob;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.sh.tr.toyrob.exception.TRException;
import com.sh.tr.toyrob.model.Direction;
import com.sh.tr.toyrob.model.impl.SquareBoard;
import com.sh.tr.toyrob.util.Position;
import com.sh.tr.toyrob.util.ShRobot;


public class GameTest {

    final int BOARD_ROWS = 5;
    final int BOARD_COLUMNS = 5;
    @Rule
    public org.junit.rules.ExpectedException thrown = ExpectedException.none();

    @Test
    public void testPlacing() throws TRException {

        SquareBoard board = new SquareBoard(BOARD_COLUMNS, BOARD_ROWS);
        ShRobot toyRobot = new ShRobot();
        Game game = new Game(board, toyRobot);

        Assert.assertTrue(game.placeToyRobot(new Position(0, 1, Direction.NORTH)));
        Assert.assertTrue(game.placeToyRobot(new Position(2, 2, Direction.SOUTH)));
        Assert.assertFalse(game.placeToyRobot(new Position(6, 6, Direction.WEST)));
        Assert.assertFalse(game.placeToyRobot(new Position(-1, 5, Direction.EAST)));
    }

    @Test
    public void testPlacingExceptions() throws TRException {

        SquareBoard board = new SquareBoard(BOARD_COLUMNS, BOARD_ROWS);
        ShRobot toyRobot = new ShRobot();
        Game game = new Game(board, toyRobot);

        thrown.expect(TRException.class);
        game.placeToyRobot(null);
        thrown.expect(TRException.class);
        game.placeToyRobot(new Position(0, 1, null));
    }

    @Test
    public void testEval() throws TRException {

        SquareBoard board = new SquareBoard(BOARD_COLUMNS, BOARD_ROWS);
        ShRobot toyRobot = new ShRobot();
        Game game = new Game(board, toyRobot);

        game.eval("PLACE 0,0,NORTH");
        Assert.assertEquals("0,0,NORTH", game.eval("REPORT"));

        game.eval("MOVE");
        game.eval("RIGHT");
        game.eval("MOVE");
        Assert.assertEquals("1,1,EAST", game.eval("REPORT"));

        // if it goes out of the board it ignores the command
        for (int i = 0; i < 10; i++)
            game.eval("MOVE");
        Assert.assertEquals("5,1,EAST", game.eval("REPORT"));

        //rotate on itself
        for (int i = 0; i < 4; i++)
            game.eval("LEFT");
        Assert.assertEquals("5,1,EAST", game.eval("REPORT"));

        // invalid commands
        thrown.expect(TRException.class);
        Assert.assertEquals("Invalid command", game.eval("PLACE12NORTH"));
        thrown.expect(TRException.class);
        Assert.assertEquals("Invalid command", game.eval("LEFFT"));
        thrown.expect(TRException.class);
        Assert.assertEquals("Invalid command", game.eval("RIGHTT"));
    }
}
