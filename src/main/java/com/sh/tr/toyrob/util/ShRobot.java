package com.sh.tr.toyrob.util;

import com.sh.tr.toyrob.exception.TRException;

/**
 * Toy Robot Implementation 
 *
 */
public class ShRobot {


    private Position position;

    public ShRobot() {
    }

    public ShRobot(Position position) {
        this.position = position;
    }

    public boolean setPosition(Position position) {
        if (position == null)
            return false;

        this.position = position;
        return true;
    }

    public boolean move() throws TRException {
        return move(position.getNextPosition());
    }

    /**
     * Moves the robot one unit forward in the direction it is currently facing
     *
     * @return true if moved successfully
     */
    public boolean move(Position newPosition) {
        if (newPosition == null)
            return false;

        // change position
        this.position = newPosition;
        return true;
    }

    public Position getPosition() {
        return this.position;
    }

    /**
     * Rotates the robot 90 degrees LEFT
     *
     * @return true if rotated successfully
     */
    public boolean rotateLeft() {
        if (this.position.direction == null)
            return false;

        this.position.direction = this.position.direction.leftDirection();
        return true;
    }

    /**
     * Rotates the robot 90 degrees RIGHT
     *
     * @return true if rotated successfully
     */
    public boolean rotateRight() {
        if (this.position.direction == null)
            return false;

        this.position.direction = this.position.direction.rightDirection();
        return true;
    }
}
