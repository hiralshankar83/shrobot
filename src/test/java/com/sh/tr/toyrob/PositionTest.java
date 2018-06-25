package com.sh.tr.toyrob;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.sh.tr.toyrob.model.Direction;
import com.sh.tr.toyrob.util.Position;


public class PositionTest extends TestCase{
    public void testGetNextPosition() throws Exception {
        Position position = new Position(0, 0, Direction.EAST);

        Position newPosition = position.getNextPosition();
        Assert.assertEquals(newPosition.getX(), 1);
        Assert.assertEquals(newPosition.getY(), 0);
        Assert.assertEquals(newPosition.getDirection(), Direction.EAST);

        newPosition = newPosition.getNextPosition();
        Assert.assertNotSame(newPosition.getX(), 1);
        Assert.assertEquals(newPosition.getY(), 0);
        Assert.assertEquals(newPosition.getDirection(), Direction.EAST);

        newPosition.setDirection(Direction.NORTH);
        newPosition = newPosition.getNextPosition();
        Assert.assertNotSame(newPosition.getX(), 1);
        Assert.assertEquals(newPosition.getY(), 1);
        Assert.assertNotSame(newPosition.getDirection(), Direction.EAST);
    }
}
