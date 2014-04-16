package gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import GameEngine.Game;

public class Board extends JPanel {

	private static final int CELL_SIZE = 64;
	private static int N = 9;

	private final Image[] boardImg = {
		new ImageIcon(this.getClass().getResource("/res/board_middle.png")).getImage(),
		new ImageIcon(this.getClass().getResource("/res/board_dot.png")).getImage(),
		new ImageIcon(this.getClass().getResource("/res/board_right.png")).getImage(),
		new ImageIcon(this.getClass().getResource("/res/board_bottom.png")).getImage(),
		new ImageIcon(this.getClass().getResource("/res/board_left.png")).getImage(),
		new ImageIcon(this.getClass().getResource("/res/board_top.png")).getImage()
	};

	private final Image[] pieceImg = {
		new ImageIcon(this.getClass().getResource("/res/white.png")).getImage(),
		new ImageIcon(this.getClass().getResource("/res/black.png")).getImage()
	};

	private Game game;

	public Board (Game game) {
		this.game = game;
		N = game.getHeight();
		setPreferredSize(new Dimension(CELL_SIZE * N,CELL_SIZE * N));
		setDoubleBuffered(true);
		//setMouseAdaptor(new ActionListener(this));
	}

	public void paintComponent(Graphics g) {
		char[][] board = game.getBoard();

		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				g.drawImage(boardImg[1], i * CELL_SIZE, j * CELL_SIZE, this);

				if(board[i][j] == 'W') {
					g.drawImage(pieceImg[0], i * CELL_SIZE, j * CELL_SIZE, this);
				} else if(board[i][j] == 'B') {
					g.drawImage(pieceImg[1], i * CELL_SIZE, j * CELL_SIZE, this);
				}
			}
		}
	}

	public void alert(String message) {
		JOptionPane.showMessageDialog(null, message);
	}

	public void refresh(int sleep) {

		try {
			Thread.sleep(sleep);
		}catch (Exception e) {
			System.out.println("Sleep Error");
		}

		repaint();
	}

	public void setMouseAdaptor(MouseAdapter actionListener) {
		this.addMouseListener(actionListener);
	}

	public void removeMouseAdaptor(MouseAdapter actionListener) {
		this.removeMouseListener(actionListener);
	}

	/////////////////GETTERS AND SETTERS//////////////////////////
	public Game getGame() {
		return game;
	}
}
