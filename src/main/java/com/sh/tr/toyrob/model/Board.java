package com.sh.tr.toyrob.model;

import com.sh.tr.toyrob.util.Position;

/**
 * Interface for the game board
 *
 */
public interface Board {

	//check on valid position
	public boolean isValidPosition(Position position);
}
