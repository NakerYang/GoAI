package util;

import GameEngine.Game;

import java.util.List;
import java.util.LinkedList;

import java.awt.Point;
/**
 * a static class of stuff
 * that the player can use
 */
public class PlayerUtil {

	/**
	 * @return : list of points that are empty on the board
	 */
	public static List<Point> getEmptySpaces(Game game) {
		char[][] board = game.getBoard();

		List<Point> ret = new LinkedList<Point>();
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[i].length; j++) {
				if(board[i][j] == '*') {
					ret.add(new Point(i, j));
				}
			}
		}

		return ret;
	}
}
