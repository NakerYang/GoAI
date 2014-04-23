package util;

public class AiUtil {

	private static boolean[][] vis;
	private static char[][] board;
	private static int[] dr = {1,-1,0,0};
	private static int[] dc = {0,0,-1,1};

	public static int getLiberties(char color, char[][] input) {
		board = input;
		vis = new boolean[board.length][board.length];

		int ret = 0;
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board.length; j++) {
				if(board[i][j] == color) {
					ret += countAround(i, j);
				}
			}
		}
		return ret;
	}

	private static int countAround(int r, int c) {
		int ret = 0;
		for(int i=0; i<dr.length; i++) {
			int nextr = r + dr[i];
			int nextc = c + dc[i];

			if(nextr < 0 || nextr >= board.length || nextc < 0 || nextc >= board.length || vis[nextr][nextc]) {
				continue;
			}

			if(board[nextr][nextc] == '*') {
				ret ++;
				vis[nextr][nextc] = true;
			}
		}
		return ret;
	}
}
