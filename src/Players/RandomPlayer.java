package Players;

import java.util.Random;
import java.awt.Point;

import GameEngine.Game;

public class RandomPlayer extends Player {

	private Random rand;

	public RandomPlayer(char color) {
		super(color);
	}

	public Point planMove(Game game) {
		int row = rand.nextInt(game.getHeight());
		int col = rand.nextInt(game.getWidth());

		return new Point(row, col);
	}
}
