import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;


public class ArenaFrame extends JFrame 
//implements Runnable 
{
	//Constants
	final int UP_CLICK = 1;
	final int DOWN_CLICK = 2;
	final int RIGHT_CLICK = 3;
	final int LEFT_CLICK = 4;


	int roundNumber;
	int speedTotal;
	Random rand = new Random();
	ArrayList<Integer> turns = new ArrayList<Integer>();

	boolean onesTurn = false;
	boolean gameEnded = true;
	boolean turnFinished = true;

	JButton startButton;
	JButton attackButton;
	JButton finishButton;
	Arena arena;

	StartButtonListener startListener;
	AttackButtonListener attackListener;
	UpButtonListener upListener;
	DownButtonListener downListener;
	RightButtonListener rightListener;
	LeftButtonListener leftListener;

	JPanel inputPanel;
	JPanel movePanel;
	JPanel menuPanel;
	JPanel arenaPanel;
	JPanel sequencePanel;
	JTextField attackField;
	JTextArea battleArea;
	JPanel dPad, dPadPanel, dPadPanel2;
	JButton upButton, downButton, leftButton, rightButton;
	JLabel blank1, blank2, blank3, blank4, blank5, blank6, blank7, blank8; // To fill in the blanks for the directional buttons and other stuff
	JLabel enterStartLabel, restartLabel, attackOneLabel, attackTwoLabel, turnLabel;
	JPanel oneAttackPanel, oneItemPanel, twoAttackPanel, twoItemPanel;
	HealthPanel healthPanel1, healthPanel2;
	TurnPanel turnPanel;
	JButton attack11, attack12, attack13, attack14, item11, item12, item13, attack21, attack22, attack23, attack24, item21, item22, item23;
	JButton oneAttackButtons[] = new JButton[4];
	String[] oneAttacknames = new String[4];
	String[] twoAttacknames = new String[4];
	JButton oneItemButtons[] = new JButton[3];
	JButton twoAttackButtons[] = new JButton[4];
	JButton twoItemButtons[] = new JButton[3];
	String input;

	StringBuffer history;
	//Thread t1;
	//JPanel northPanel;
	public ArenaFrame() throws IOException
	{
		roundNumber = 0;

		setLayout(new BorderLayout());
		arena = new Arena();

		startListener = new StartButtonListener();
		attackListener = new AttackButtonListener();
		upListener = new UpButtonListener();
		downListener = new DownButtonListener();
		leftListener = new LeftButtonListener();
		rightListener = new RightButtonListener();

		startButton = new JButton("Start Battle?");
		attackButton = new JButton("Attack!");
		finishButton = new JButton("Finish Turn");
	
		attack11 = new JButton("None");
		attack12 = new JButton("None");
		attack13 = new JButton("None");
		attack14 = new JButton("None");
		item11 = new JButton("None");
		item12 = new JButton("None");
		item13 = new JButton("None");
		attack21 = new JButton("None");
		attack22 = new JButton("None");
		attack23 = new JButton("None");
		attack24 = new JButton("None");
		item21 = new JButton("None");
		item22 = new JButton("None");
		item23 = new JButton("None");
		
		for(int i = 0; i < 4; i++)
		{
			if(arena.one.attackList[i] != null)
			{
				oneAttackButtons[i] = new JButton(arena.one.attackList[i].getName());
			}
			else
			{
				oneAttackButtons[i] = new JButton("none");
			}
			if(arena.two.attackList[i] != null)
			{
				twoAttackButtons[i] = new JButton(arena.two.attackList[i].getName());
			}
			else
			{
				twoAttackButtons[i] = new JButton("none");
			}
			oneAttackButtons[i].addActionListener(attackListener);
			twoAttackButtons[i].addActionListener(attackListener);
		}
		
		nextTurn();
		dPad = new JPanel(new GridLayout(3,3));
		dPadPanel = new JPanel();
		dPadPanel2 = new JPanel();
		sequencePanel = new JPanel();
		upButton = new JButton("^");
		downButton = new JButton("v");
		leftButton = new JButton("<");
		rightButton = new JButton(">");
		blank1 = new JLabel();
		blank2 = new JLabel();
		blank3 = new JLabel();
		blank4 = new JLabel();
		blank5 = new JLabel();
		blank6 = new JLabel("                                                                                                                                                                      ");
		blank7 = new JLabel("                                                                                                                                                                      ");
		blank8 = new JLabel("                                                                                                                                                                      ");
		healthPanel1 = new HealthPanel(arena.one);
		healthPanel2 = new HealthPanel(arena.two);
		turnLabel = new JLabel("Turn sequence (STILL BROKEN): ");

		inputPanel = new JPanel();
		movePanel = new JPanel(new BorderLayout());
		menuPanel = new JPanel();
		arenaPanel = new JPanel(new BorderLayout());
		oneAttackPanel = new JPanel();
		oneItemPanel = new JPanel();
		twoAttackPanel = new JPanel();
		twoItemPanel = new JPanel();
		attackField = new JTextField(8);
		attackField.setEditable(true);
		battleArea = new JTextArea(10, 50);
		battleArea.setEditable(false);
		battleArea.setMaximumSize(new Dimension(20,20));
		battleArea.setLineWrap(true);
		history = new StringBuffer();
		JScrollPane scroll = new JScrollPane(battleArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JTabbedPane tabbedPaneOne = new JTabbedPane();
		JTabbedPane tabbedPaneTwo = new JTabbedPane();
		tabbedPaneOne.addTab("Abilities", oneAttackPanel);
		tabbedPaneOne.addTab("Items", oneItemPanel);
		tabbedPaneTwo.addTab("Abilities", twoAttackPanel);
		tabbedPaneTwo.addTab("Items", twoItemPanel);
		
		enterStartLabel = new JLabel("Press 'Start Battle?' to fight!");
		attackOneLabel = new JLabel("What will " + arena.one.getName() + " do?");
		attackTwoLabel = new JLabel("What will " + arena.two.getName() + " do?");
		restartLabel = new JLabel("Press 'Start Battle' to battle again!");

		oneAttackPanel.add(attack11);
		oneAttackPanel.add(attack12);
		oneAttackPanel.add(attack13);
		oneAttackPanel.add(attack14);
		oneItemPanel.add(item11);
		oneItemPanel.add(item12);
		oneItemPanel.add(item13);
		twoAttackPanel.add(attack21);
		twoAttackPanel.add(attack22);
		twoAttackPanel.add(attack23);
		twoItemPanel.add(item21);
		twoItemPanel.add(item22);
		twoItemPanel.add(item23);
		twoAttackPanel.add(attack24);

		dPadPanel2.setLayout(new BorderLayout());
		dPad.add(blank1);
		dPad.add(upButton);
		upButton.addActionListener(upListener);
		dPad.add(blank2);
		dPad.add(leftButton);
		leftButton.addActionListener(leftListener);
		dPad.add(blank3);
		dPad.add(rightButton);
		rightButton.addActionListener(rightListener);
		dPad.add(blank4);
		dPad.add(downButton);
		downButton.addActionListener(downListener);
		dPad.add(blank5);
		healthPanel1.add(blank6);
		healthPanel2.add(blank7);
		turnPanel.add(blank8);
		sequencePanel.add(turnLabel);
		sequencePanel.add(turnPanel);
		//dPadPanel.setLayout(null);
		dPadPanel.add(healthPanel1);
		dPadPanel.add(dPad);
		dPadPanel.add(healthPanel2);
		dPadPanel2.add(dPadPanel, BorderLayout.NORTH);
		dPadPanel2.add(sequencePanel, BorderLayout.CENTER);

		inputPanel.add(enterStartLabel);
		inputPanel.add(startButton);
		movePanel.add(inputPanel, BorderLayout.NORTH);
		movePanel.add(dPadPanel2, BorderLayout.SOUTH);
		menuPanel.setLayout(new BorderLayout());
		menuPanel.add(movePanel, BorderLayout.NORTH);
		menuPanel.add(scroll, BorderLayout.SOUTH);
		//arena.add(startButton);
		startButton.addActionListener(startListener);
		attackButton.addActionListener(attackListener);
		attackField.addActionListener(attackListener);
		//add(northLabel, BorderLayout.PAGE_START);
		arenaPanel.add(tabbedPaneOne, BorderLayout.WEST);
		arenaPanel.add(arena, BorderLayout.CENTER);
		arenaPanel.add(tabbedPaneTwo, BorderLayout.EAST);
		add(arenaPanel, BorderLayout.CENTER);
		add(menuPanel, BorderLayout.SOUTH);
	}
	/**
	 * sets onesTurn to the corresponding turn
	 * if roundNumber = 0 it resets the turns
	 */
	private void nextTurn()
	{
		double streakEnder;
		int[] currentStreak = new int[2];
		double streakConstant = 2;
		int randTurn;
		if(roundNumber == 0 || turns.size() == 0)
		{
			streakEnder = (arena.one.getSpeed() + 0.0)/(arena.two.getSpeed() + 0.0);
			ArrayList<Integer> temp = new ArrayList<Integer>();
			speedTotal = arena.one.getSpeed() + arena.two.getSpeed();
			int currentSpeed = speedTotal;
			rand = new Random();

			turns = new ArrayList<Integer>();
			for(int i = 0; i < speedTotal; i++)
			{
				if(i < arena.one.getSpeed())
				{
					temp.add(1);
				}
				else
				{

					temp.add(2);
				}
			} 
			System.out.println(temp);

			for(int i = 0; i < speedTotal; i++)
			{

				randTurn = rand.nextInt(currentSpeed);

				if(currentStreak[0] == 1 && currentStreak[1] >= streakConstant*streakEnder && temp.get(temp.size()-1) ==2)
				{
					System.out.println("streak1");
					System.out.println(turns);
					currentStreak[0] = 2;
					currentStreak[1]=1;
					turns.add(2);
					temp.remove(temp.size()-1);
				}
				else if(currentStreak[0] == 2 && currentStreak[1] >= streakConstant/(streakEnder)&& temp.get(0) ==1)
				{
					System.out.println("streak2");
					System.out.println(turns);
					currentStreak[0] = 1;
					currentStreak[1]=1;
					turns.add(1);
					temp.remove(0);
				}
				else if(temp.remove(randTurn) == 1)
				{
					if(currentStreak[0] == 1)
					{
						currentStreak[1]++;
					}
					else
					{
						currentStreak[1] = 1;
					}
					currentStreak[0] =1;
					turns.add(1);
				}
				else
				{
					if(currentStreak[0] == 2)
					{
						currentStreak[1]++;
					}
					else
					{
						currentStreak[1] = 1;
					}
					currentStreak[0] = 2;

					turns.add(2);
				}

				currentSpeed--;
				System.out.println(currentStreak[1] + " is streak");
			}
			System.out.println(turns +" is turns");
		}

		randTurn = rand.nextInt(speedTotal);
		//System.out.println("random is " + randTurn);
		//System.out.println(turns.get(randTurn));
		turnPanel = new TurnPanel(turns);
		turnPanel.repaint();
		System.out.println(turns);
		if(turns.remove(0) == 1)
		{
			onesTurn = true;
		}
		else
		{
			onesTurn = false;
		}


		roundNumber++;
		//System.out.println(turns + " after remove");
	}
	/** Updates the attack buttons for each characters turn
	 * 
	 */
	public void UIupdate()
	{
		inputPanel.remove(enterStartLabel);
		inputPanel.remove(attackOneLabel);
		inputPanel.remove(attackTwoLabel);
		inputPanel.remove(restartLabel);
		inputPanel.remove(attackField);
		inputPanel.remove(attackButton);
		inputPanel.remove(startButton);
		for(int i = 0; i < 4; i++)
		{
			inputPanel.remove(oneAttackButtons[i]);
			inputPanel.remove(twoAttackButtons[i]);
		}
		if(onesTurn && !gameEnded)
		{
			inputPanel.add(attackOneLabel);
			for(int i = 0; i < 4; i++)
			{
				inputPanel.add(oneAttackButtons[i]);
			}
		}
		else if (!onesTurn && !gameEnded)
		{
			inputPanel.add(attackTwoLabel);
			for(int i = 0; i < 4; i++)
			{
				inputPanel.add(twoAttackButtons[i]);
			}

		}
		else if(gameEnded)
		{

			inputPanel.add(restartLabel);
			inputPanel.add(startButton);
		}
		/*
		if(!gameEnded)
		{
			inputPanel.add(attackField);
			inputPanel.add(attackButton);
		}
		*/
		inputPanel.revalidate();
		repaint();
	}
	/**
	 * someone performs an attack on someone
	 * @param attacker does the attack
	 * @param victim takes the attack
	 * @param buttonName name of the attack to be recognized
	 */
	public void attackOther(Character attacker, Character victim, String buttonName)
	{
		//attackField.requestFocus();
		//Scanner scanner = new Scanner(System.in);
		//String input = scanner.nextLine().toLowerCase();
		//input = attackField.getText();
		input = buttonName;

		boolean didAttack = false;
		if(input != null)
		{
			System.out.println(input + "!");
			for(int i = 0; i < 4; i++)
			{
				if(attacker.attackList[i] != null){
					if(attacker.attackList[i].getName().compareToIgnoreCase(input) == 0)
					{
						//System.out.println("doing damage");
						didAttack = true;
						boolean criticalHit = attacker.attackList[i].doDamage(attacker, victim);
						if(criticalHit)
						{
							history.append("CRITICAL HIT \n");
						}
					}
				}
			}
		}
		if(!didAttack){
			history.append(attacker.getName() + " doesn't know that move! Try again! \n");
			attackField.setText("");
			//attackOther(attacker, victim);
		}
		else{
			history.append(attacker.getName() + " used " + input + "! \n");
			battleArea.setText(history.toString());
			attackField.setText("");
			if(!gameEnded)
			{
				nextTurn();
			}
		}
	}
	/**
	 * One turn of battle
	 * @param buttonName
	 * @throws IOException
	 */
	public void battle(String buttonName) throws IOException
	{

		if(onesTurn)
		{
			attackOther(arena.one, arena.two, buttonName);
		}	
		else
		{
			attackOther(arena.two, arena.one, buttonName);
		}
		UIupdate();
		if(arena.one.getHealth() <= 0){
			gameEnded = true;
			UIupdate();
			history.append("Game Over. " + arena.two.getName() + " wins. \n");
			arena.two.setExperience(arena.two.getExperience() + 5);
			if(arena.two.getExperience() >= arena.two.getExperienceBar())
			{
				history.append(arena.two.levelUp() + "\n");
			}
			
			for(int i = 0; i < 4; i++)
			{	
				if(arena.two.attackList[i] != null)
				{
					twoAttackButtons[i] = new JButton(arena.two.attackList[i].getName());
				}
				else
				{
					twoAttackButtons[i] = new JButton("none");
				}
				twoAttackButtons[i].addActionListener(attackListener);
			}
			
		} 
		else if(arena.two.getHealth() <= 0)
		{
			gameEnded = true;
			UIupdate();
			history.append("Game Over. " + arena.one.getName() + " wins. \n");
			arena.one.setExperience(arena.one.getExperience() + 5);
			if(arena.one.getExperience() >= arena.one.getExperienceBar())
			{
				history.append(arena.one.levelUp() + "\n");
			}
			
			for(int i = 0; i < 4; i++)
			{
				if(arena.one.attackList[i] != null)
				{
					oneAttackButtons[i] = new JButton(arena.one.attackList[i].getName());
				}
				else
				{
					oneAttackButtons[i] = new JButton("none");
				}
				oneAttackButtons[i].addActionListener(attackListener);

			}
			
		}

		battleArea.setText(history.toString());
		arena.repaint();
		System.out.println("Thread ended");
	}

	private class StartButtonListener implements ActionListener
	{

		public void actionPerformed(ActionEvent arg0) {
			//arena.battle(arena.one, arena.two);

			if(gameEnded)
			{
				gameEnded = false;
				//if(gameRestarted)
				//{
				//UIupdate();
				arena.one.fullHeal();
				arena.two.fullHeal();
				//}
				/*
            if(arena.one.getSpeed() > arena.two.getSpeed())
            {
               onesTurn = true;
            }
				 */
				roundNumber = 0;
				nextTurn();
				UIupdate();
			}

		}	
	}
	private class AttackButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0){
			if(!gameEnded)
			{
				JButton clicked = (JButton)arg0.getSource();
				try {
					battle(clicked.getText());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			repaint();
		}
	}
	private class UpButtonListener implements ActionListener
	{

		public void actionPerformed(ActionEvent arg0) {
			if(!gameEnded)
			{
				if(onesTurn)
				{
					arena.move(UP_CLICK,arena.one, arena.one.x, arena.one.y - 1);
				}
				else
				{
					arena.move(UP_CLICK, arena.two, arena.two.x, arena.two.y - 1);	
				}
			}
		}
	}
	private class DownButtonListener implements ActionListener
	{

		public void actionPerformed(ActionEvent arg0) {
			if(!gameEnded)
			{
				if(onesTurn)
				{
					arena.move(DOWN_CLICK,arena.one, arena.one.x, arena.one.y + 1);
				}
				else
				{
					arena.move(DOWN_CLICK, arena.two, arena.two.x, arena.two.y + 1);	
				}
			}
		}
	}
	private class LeftButtonListener implements ActionListener
	{

		public void actionPerformed(ActionEvent arg0) {
			if(!gameEnded)
			{
				if(onesTurn)
				{
					arena.move(LEFT_CLICK,arena.one, arena.one.x - 1, arena.one.y);
				}
				else
				{
					arena.move(LEFT_CLICK, arena.two, arena.two.x - 1, arena.two.y);	
				}
			}
		}
	}
	private class RightButtonListener implements ActionListener
	{

		public void actionPerformed(ActionEvent arg0) 
		{
			if(!gameEnded)
			{
				if(onesTurn)
				{
					arena.move(RIGHT_CLICK,arena.one, arena.one.x + 1, arena.one.y);
				}
				else
				{
					arena.move(RIGHT_CLICK, arena.two, arena.two.x + 1, arena.two.y);	
				}
			}
		}
	}
	private class itemButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			
		}
	}
	private class abilitiesButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			
		}
	}

	public static void main(String[] args) throws IOException
	{
	 
		ArenaFrame frame = new ArenaFrame();
		frame.setSize(1100,700);
		frame.setMinimumSize(new Dimension(1300,700));
		frame.setMaximumSize(new Dimension(1300,700));
		frame.setTitle("RPG Demo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	
	   
	}
}




