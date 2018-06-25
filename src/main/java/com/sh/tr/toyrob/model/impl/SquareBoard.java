package com.sh.tr.toyrob.model.impl;

import com.sh.tr.toyrob.model.Board;
import com.sh.tr.toyrob.util.Position;

/**
 * Implementation for Board
 *
 */
public class SquareBoard implements Board {

	//to keep board size dynamic
    private int rows;
    private int columns;

    public SquareBoard(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public boolean isValidPosition(Position position) {
        return !(position.getX() > this.columns || position.getX() < 0 ||
                        position.getY() > this.rows || position.getY() < 0);
    }
}
