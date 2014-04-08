package GameEngine;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Arrays;
import java.awt.Point;

import gui.Board;

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
	}

	/**
	 * default constructor makes a 9x9 board
	 */
	public Game () {
		this(9, 9);
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

	public char getTurn() {
		if((turn & 1) == 0) {
			return 'W';
		} else {
			return 'B';
		}
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
