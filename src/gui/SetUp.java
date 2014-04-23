package gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import GameEngine.Game;
import Players.*;

public class SetUp extends JPanel {

	private PlayerFactory factory1, factory2;	//player factory
	private JLabel player1Label, player2Label;	//labels for player
	private JLabel title;
	private JLabel dimensionPrompt;
	private JComboBox p1ai, p2ai;				//combobox for ai selection
	private JComboBox dimensions;
	private GameData data;

	public SetUp(GameData data) {

		this.data = data;

		//instantiate stuff
		title = new JLabel("Go Game Setup");

		dimensionPrompt = new JLabel("Board Size: ");
		String[] arr = new String[9-4];
		for(int i=0; i<arr.length; i++) {
			arr[i] = (i + 4) + "";
		}
		dimensions = new JComboBox(arr);
		dimensions.addActionListener(new SizeListener(data));

		factory1 = new PlayerFactory('W');
		factory2 = new PlayerFactory('B');

		data.setBoardSize(4);
		data.setP1(factory1.get(factory1.getPlayerNames()[0]));
		data.setP2(factory2.get(factory1.getPlayerNames()[0]));

		player1Label = new JLabel("Player 1 (white): ");
		player2Label = new JLabel("Player 2 (Black): ");

		p1ai = new JComboBox(factory1.getPlayerNames());
		p1ai.addActionListener(new P1Listener(data, factory1));
		p2ai = new JComboBox(factory2.getPlayerNames());
		p2ai.addActionListener(new P2Listener(data, factory2));

		//layout building
		GroupLayout layout = new GroupLayout(this);
	    setLayout(layout);
	    layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);

		GroupLayout.SequentialGroup leftToRight = layout.createSequentialGroup();
		GroupLayout.ParallelGroup col1 = layout.createParallelGroup();
		GroupLayout.ParallelGroup col2 = layout.createParallelGroup();

		col1.addComponent(player1Label);
		col1.addComponent(p1ai);
		col1.addComponent(title);
		col1.addComponent(dimensionPrompt);

		col2.addComponent(player2Label);
		col2.addComponent(p2ai);
		col2.addComponent(dimensions);

		leftToRight.addGroup(col1);
		leftToRight.addGroup(col2);

		GroupLayout.SequentialGroup topToBottom = layout.createSequentialGroup();
		GroupLayout.ParallelGroup row1 = layout.createParallelGroup();
		row1.addComponent(title);

		GroupLayout.ParallelGroup row2 = layout.createParallelGroup();
		row2.addComponent(dimensionPrompt);
		row2.addComponent(dimensions);

		GroupLayout.ParallelGroup row3 = layout.createParallelGroup();
		row3.addComponent(player1Label);
		row3.addComponent(player2Label);

		GroupLayout.ParallelGroup row4 = layout.createParallelGroup();
		row4.addComponent(p1ai);
		row4.addComponent(p2ai);

		topToBottom.addGroup(row1);
		topToBottom.addGroup(row2);
		topToBottom.addGroup(row3);
		topToBottom.addGroup(row4);

		layout.setHorizontalGroup(leftToRight);
		layout.setVerticalGroup(topToBottom);
	}

	private class SizeListener implements ActionListener {
		GameData data;

		public SizeListener(GameData data) {
			this.data = data;
		}

		public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox)e.getSource();
			String selected = (String)cb.getSelectedItem();
			data.setBoardSize(Integer.parseInt(selected));
		}
	}

	private class P1Listener implements ActionListener {
		PlayerFactory factory;
		GameData data;

		public P1Listener(GameData data, PlayerFactory factory) {
			this.data = data;
			this.factory = factory;
		}

		public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox)e.getSource();
			String selected = (String)cb.getSelectedItem();
			data.setP1(factory.get(selected));
		}
	}

	private class P2Listener implements ActionListener {
		PlayerFactory factory;
		GameData data;

		public P2Listener(GameData data, PlayerFactory factory) {
			this.data = data;
			this.factory = factory;
		}

		public void actionPerformed(ActionEvent e) {
			JComboBox cb = (JComboBox)e.getSource();
			String selected = (String)cb.getSelectedItem();
			data.setP2(factory.get(selected));
		}
	}
}
