import guiBackround.Environments;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;

/** TO DO: CREATE MONSTER CLASS. RESTRUCTURE CODE TO BE MORE EXPANDABLE. WORK ON BUFFEREDREADER FOR READING MOVELIST. 
 * COMPLEX FORMULAS FOR ATTACK(USE DEFENSE AND/OR EVASIVENESS), LEVEL UP SYSTEM
 * 
 */

public class Arena extends JPanel{

	//boolean oneFirst = false;
	//boolean gameEnded = false;
	//boolean gameStarted = false;

	//CONSTANTS
	final int GRID_ROW = 7;
	final int GRID_COLUMN = 7;

	Character one = new Character(0, 3, Color.RED);
	Character two = new Character(6, 3, Color.BLUE);

	JMenuBar menuBar;
	JMenu fileMenu, optionMenu, levelMenu;
	JMenuItem exit, normal, desert, city, endGame;
	Environments background;
	DesertChoice desertChoice;
	CityChoice cityChoice;
	NormalChoice normalChoice;
	ExitChoice exitChoice;
	GridPanel[][] grid = new GridPanel[GRID_ROW][GRID_COLUMN];

	public Arena() throws IOException
	{
		setSize(500,400);
		setMaximumSize(new Dimension(500,400));
		setBackground(new Color(100, 200, 250));
		menuBar = new JMenuBar();
		fileMenu = new JMenu("File");
		optionMenu = new JMenu("Options");
		levelMenu = new JMenu("Choose Background");
		exit = new JMenuItem("Exit");
		desert = new JMenuItem("Desert");
		city = new JMenuItem("City");
		normal = new JMenuItem("Normal");
		//endGame = new JMenuItem("End Game");
		desertChoice = new DesertChoice();
		cityChoice = new CityChoice();
		normalChoice = new NormalChoice();
		exitChoice = new ExitChoice();
		//		endChoice = new EndChoice();
		background = new Environments(1);

		//add(menuBar);
		menuBar.add(fileMenu);
		menuBar.add(optionMenu);
		fileMenu.add(exit);
		//fileMenu.add(endGame);
		optionMenu.add(levelMenu);
		levelMenu.add(normal);
		levelMenu.add(desert);
		levelMenu.add(city);

		normal.addActionListener(normalChoice);
		desert.addActionListener(desertChoice);
		city.addActionListener(cityChoice);
		exit.addActionListener(exitChoice);
		//		endGame.addActionListener(endChoice);

		//TEMP
		one.addAttack(new Attacks(5));
		two.addAttack(new Attacks(6));


		setLayout(new GridLayout(7,7));
		for(int y = 0; y < 7; y++)
		{
			for(int x = 0; x < 7; x++)
			{
				grid[y][x] = new GridPanel();
				grid[y][x].setBorder(BorderFactory.createLineBorder(Color.black));

			}
		}
		grid[one.y][one.x] = new GridPanel(one);
		grid[two.y][two.x] = new GridPanel(two);
		updateGrid();
	}
	public void updateGrid()
	{
		for(int y = 0; y < 7; y++)
		{
			for(int x = 0; x < 7; x++)
			{
				add(grid[y][x]);
				grid[y][x].revalidate();
			}
		}
	}
	public void move(int direction, Character fighter, int x, int y)
	{
		if(0 <= x && x < 7 && y >= 0 && y < 7 && !grid[y][x].hasFighter)
		{
			for(int yIndex = 0; yIndex < 7; yIndex++)
			{
				for(int xIndex = 0; xIndex < 7; xIndex++)
				{
					remove(grid[yIndex][xIndex]);
					grid[yIndex][xIndex] = new GridPanel();
					grid[yIndex][xIndex].setBorder(BorderFactory.createLineBorder(Color.black));

				}
			}
			fighter.x = x;
			fighter.y = y;
			grid[y][x] = new GridPanel(fighter);
			if(fighter == one)
			{
				grid[two.y][two.x] = new GridPanel(two);
			}
			else
			{
				grid[one.y][one.x] = new GridPanel(one);
			}

			updateGrid();
		}
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		background.paint(g);


	}
	private class DesertChoice implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			background = new Environments(2);
			repaint();
		}

	}
	private class CityChoice implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			background = new Environments(3);
			repaint();
		}

	}
	private class NormalChoice implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			background = new Environments(1);
			repaint();
		}

	}
	private class ExitChoice implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			System.exit(0);
		}
	}
	/*
	private class EndChoice implements ActionListener{


		public void actionPerformed(ActionEvent e) {
			gameEnded = true;

		}

	}
	 */
}











