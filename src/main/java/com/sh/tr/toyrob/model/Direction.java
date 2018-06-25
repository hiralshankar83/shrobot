package com.sh.tr.toyrob.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Direction of the robot
 *
 */
public enum Direction {
	//list of directions
	NORTH(0), EAST(1), SOUTH(2), WEST(3);
	
    //stores direction index and direction inside the map
	private static Map<Integer, Direction> map = new HashMap<Integer, Direction>();

	/**
	 * put all possible directions along with index in map at the start up
	 */
	static {
        for (Direction directionEnum : Direction.values()) {
            map.put(directionEnum.directionIndex, directionEnum);
        }
    }

    private int directionIndex;

    private Direction(int direction) {
        this.directionIndex = direction;
    }

    public static Direction valueOf(int directionNum) {
        return map.get(directionNum);
    }

    /**
     * Returns the direction on the left of the current one
     */
    public Direction leftDirection() {
        return rotate(-1);
    }

    /**
     * Returns the direction on the right of the current one
     */
    public Direction rightDirection() {
        return rotate(1);
    }

    private Direction rotate(int step) {
    	//rotate with given direction without changing position  
        int newIndex = (this.directionIndex + step) < 0 ?
                map.size() - 1 :
                (this.directionIndex + step) % map.size();

        return Direction.valueOf(newIndex);
    }
}
