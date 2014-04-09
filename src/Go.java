import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.JPanel;

import gui.Board;
import GameEngine.Game;

public class Go extends JFrame {

	private static final int FRAME_WIDTH = 750;
	private static final int FRAME_HEIGHT = 600;

	private static final long serialVersionUID = 1L;

	public static void main(String[] args){

		// first select which AIs are going to play...
		//TODO this

		// making the main frame
		JFrame frame = new JFrame("GO!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	    frame.setResizable(false);

		JPanel bp = new JPanel();
	    bp.setLayout(new BorderLayout(2,1));

		Game game = new Game();
	    bp.add(game.getGameBoard(),BorderLayout.CENTER);

		frame.getContentPane().add(bp);
	    frame.pack();
	    frame.setVisible(true);

		game.playGame();
	}
}
