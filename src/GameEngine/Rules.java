package GameEngine;

import java.util.HashSet;

public class Rules {
	
	
	
	public static boolean isMoveLegal(char color, int row, int col, char[][] board) {
		if (board[row][col] != '*') {
			return false;
		}
			
		
		return true;	
	}
	
	public static String serializeBoardState(char[][] board) {
		
		String boardString = "";
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				boardString = boardString + board[i][j];
			}
		}
		return boardString;
	}
	
	public static char[][] copyBoard(char[][] board) {
		
		char[][] boardCopy = new char[board.length][board[0].length];
		
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				boardCopy[i][j] = board[i][j];
			}
		}
		return boardCopy;
	}
}
