package GameEngine;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.List;
import java.awt.Point;

import gui.Board;
import Players.*;

/**
 * Game
 * this class will store the game state
 * each cell in the board will have one of 3 characters :
 * 	'*' means the cell is empty
 * 	'B' means the cell is occupied by a black piece
 * 	'W' means the cell is occupied by a white piece
 *
 * @author Steven Zhang
 */
public class Game {
	protected static int turn;			// which turn it is
	protected static Board GAME_BOARD;

	protected int height;				// height
	protected int width;				// width
	protected char[][] board;			// representation of the board
	protected LinkedList<Move> moves;	// list of moves
	protected HashSet<String> seenBoardStates;	 // past board states
	protected Player p1, p2;

	/** constructors **/

	/**
	 * @param height,width : the board size nxm
	 */
	public Game (int height, int width) {
		turn = 0;
		this.height = height;
		this.width = width;
		this.moves = new LinkedList<Move>();
		this.seenBoardStates = new HashSet<String>();

		board = new char[height][width];
		for(int i=0; i<height; i++) {
			Arrays.fill(board[i], '*');
		}

		GAME_BOARD = new Board(this);

		//TODO remove hardcode
		p1 = new Human('W');
		p2 = new RandomPlayer('B');

	}

	/**
	 * default constructor makes a 9x9 board
	 */
	public Game () {
		this(9, 9);
	}

	public void playGame() {

		while(!Rules.gameOver(board)) {
			if((turn & 1) == 0) {
				//make the move
				p1.planMove(this);
				p1.gain(1);
				GAME_BOARD.refresh(0);

				//remove the captured pieces
				List<Point> toRemove = Rules.findCaptured(p1.getColor(), p2.getColor(), board);
				while(!toRemove.isEmpty()) {
					Point tmp = toRemove.remove(0);
					board[tmp.x][tmp.y] = '*';
				}
				GAME_BOARD.refresh(0);

				continue;
			} else {
				p2.planMove(this);
				p2.gain(1);
				GAME_BOARD.refresh(0);

				List<Point> toRemove = Rules.findCaptured(p2.getColor(), p1.getColor(), board);
				while(!toRemove.isEmpty()) {
					Point tmp = toRemove.remove(0);
					board[tmp.x][tmp.y] = '*';
				}
				GAME_BOARD.refresh(0);

				continue;
			}
		}

		GAME_BOARD.alert(Rules.getWinner(board));
	}

	/**
	 * places a piece on the board
	 *
	 * @param color : the color of the piece must be 'W' or 'B'
	 * @param row,col : location of the piece to be placed 0 indexed
	 *
	 * @return boolean whether the piece is actually placed
	 */
	public boolean play(char color, int row, int col) {

		// sanity checks
		if(color != 'B' && color != 'W') {
			return false; //not a valid color
		}

		if(row < 0 || row >= height || col < 0 || col >= width) {
			return false; //not a valid coordinate
		}

		if (!Rules.isMoveLegal(color, row, col, board)) {
			return false;
		}

		char[][] boardCopy = Rules.copyBoard(board);

		boardCopy[row][col] = color;

		if (seenBoardStates.contains(Rules.serializeBoardState(boardCopy))) {
			return false;
		}

		board[row][col] = color;

		seenBoardStates.add(Rules.serializeBoardState(board));

		moves.add(new Move(color, new Point(row, col)));

		turn ++;

		return true;
	}

	public void skipTurn() {
		turn ++;
	}

	/** getters and setters **/
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public LinkedList<Move> getGamePlay() {
		return moves;
	}

	public char[][] getBoard() {
		return board;
	}

	public Move getLastMove() {
		if(moves.isEmpty()) {
			return null;
		}
		return moves.getLast();
	}

	public int getTurn() {
		return (turn & 1);
	}

	public Board getGameBoard() {
		return GAME_BOARD;
	}

	public void printBoard() {
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				System.out.print("[" + board[i][j] + "]");
			}System.out.println();
		}System.out.println();
	}
}
