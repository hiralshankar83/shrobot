package com.sh.tr.toyrob;

import com.sh.tr.toyrob.model.impl.SquareBoard;
import com.sh.tr.toyrob.util.Position;

import junit.framework.Assert;
import junit.framework.TestCase;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SquareBoardTest extends TestCase{

    public void testIsValidPosition() throws Exception {
        Position mockPosition = mock(Position.class);
        when(mockPosition.getX()).thenReturn(6);
        when(mockPosition.getY()).thenReturn(7);

        SquareBoard board = new SquareBoard(4, 5);
        Assert.assertFalse(board.isValidPosition(mockPosition));


        when(mockPosition.getX()).thenReturn(1);
        when(mockPosition.getY()).thenReturn(1);
        Assert.assertTrue(board.isValidPosition(mockPosition));


        when(mockPosition.getX()).thenReturn(-1);
        when(mockPosition.getY()).thenReturn(-1);
        Assert.assertFalse(board.isValidPosition(mockPosition));
    }

}
