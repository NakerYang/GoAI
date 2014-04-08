package GameEngine;

import java.awt.Point;
/**
 * Move
 * this is a representation of a move made by a player
 *
 * @author Steven Zhang
 */

public final class Move {
	private char color; 		// this is the color of the piece
	private Point location;		// this is the placement of the piece
	private boolean pass;

	public Move(char color, Point location) {
		this.color = color;
		this.location = location;
		this.pass = false;
	}

	/**
	 * default constructor will be a pass
	 */
	public Move() {
		this.pass = true;
	}

	public char getColor() {
		return color;
	}

	public void setColor(char color) {
		this.color = color;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation() {
		this.location = location;
	}

	public String toString() {
		if(pass) {
			return "PASS";
		} else {
			return "(" + location.x + ", " + location.y + ")";
		}
	}
}

