package gui;

import Players.*;

public class GameData {

	private Player p1, p2;
	private int boardSize;

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public void setBoardSize(int size) {
		this.boardSize = size;
	}

	public Player getP1() {
		return p1;
	}

	public Player getP2() {
		return p2;
	}

	public int getSize() {
		return boardSize;
	}
}
