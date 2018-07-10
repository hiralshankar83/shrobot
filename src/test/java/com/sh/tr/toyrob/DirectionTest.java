package com.sh.tr.toyrob;

import junit.framework.Assert;
import junit.framework.TestCase;

import com.sh.tr.toyrob.model.Direction;


@SuppressWarnings("deprecation")
public class DirectionTest extends TestCase{
    public void testRotate() throws Exception {
        Direction direction = Direction.EAST;

        direction = direction.leftDirection();
        Assert.assertEquals(direction, Direction.NORTH);

        direction = direction.leftDirection();
        Assert.assertEquals(direction, Direction.WEST);

        direction = direction.leftDirection();
        Assert.assertEquals(direction, Direction.SOUTH);

        direction = direction.leftDirection();
        Assert.assertEquals(direction, Direction.EAST);

        direction = direction.leftDirection();
        Assert.assertEquals(direction, Direction.NORTH);

        direction = direction.rightDirection();
        Assert.assertEquals(direction, Direction.EAST);

        direction = direction.rightDirection();
        Assert.assertEquals(direction, Direction.SOUTH);

        direction = direction.rightDirection();
        Assert.assertEquals(direction, Direction.WEST);

        direction = direction.rightDirection();
        Assert.assertEquals(direction, Direction.NORTH);

        direction = direction.rightDirection();
        Assert.assertEquals(direction, Direction.EAST);
    }
}
