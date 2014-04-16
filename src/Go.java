import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;
import javax.swing.*;

import gui.*;
import GameEngine.Game;
import Players.*;

public class Go {

	private static final int FRAME_WIDTH = 750;
	private static final int FRAME_HEIGHT = 600;

	private static final long serialVersionUID = 1L;

	public static void main(String[] args){

		// making the main frame
		JFrame frame = new JFrame("GO!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    frame.setResizable(false);

		GameData data = new GameData();

		JOptionPane.showMessageDialog(null, new SetUp(data));

		JPanel bp = new JPanel();
		bp.setLayout(new BorderLayout(2,1));

		Game game = new Game(data.getSize(), data.getSize(), data.getP1(), data.getP2());
		bp.add(game.getGameBoard(), BorderLayout.CENTER);

		frame.getContentPane().add(bp);
	    frame.pack();
	    frame.setVisible(true);

		game.playGame();
	}
}
