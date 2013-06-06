import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * @author Andrew Tang, Blade Chapman, Carl Rodriguez
 * 
 * This class depicts the statistics of a character
 * All character data is written and read off of characterList.txt
 *
 * Format: CHARACTER_NAME, ATTACK_STAT, DEFENSE_STAT, SPEED_STAT, EVASIVENESS_STAT, ATTACK1_NUMBER, ATTACK2_NUMBER, ATTACK3_NUMBER, ATTACK4_NUMBER, EXPERIENCE, EXPERIENCE_BAR, LEVEL, ITEM1_NAME, ITEM2_NAME, ITEM3_NAME, ITEM4_NAME,
 *  
 * BUG NOTES:
 * lp: add catches for things such as cancel under writeCharacter, etc.  
 *
 * WIP:
 * removeAttack JOptionPane dialogue
 */

public class Character {
	Color color;
	int x;
	int y;
	private String name;
	private int attackStat;
	private int defenseStat;
	private int speedStat;
	private int evasivenessStat; // will implement random number generator for probability of hit
	int permaHealthStat; // max health a character has
	private int healthStat;

	private boolean isDead;

	int experienceStat;
	int experienceBar;
	int level;
	Attacks attackList[] = new Attacks[4];
	Item itemList[] = new Item[4];
	LinkedList<Item> itemLinkedList = new LinkedList<Item>();


	private int tempAttack = 0;
	private int tempDefense = 0;
	private int tempSpeed = 0;
	private String tempName = "default";
	private int tempEvasiveness = 1;
	//private int tempHealth = 25;
	
	private JTextArea attackSelect;
	private int variableAttackStat = 5;
	private JTextArea defenseSelect;
	private int variableDefenseStat = 5;
	private JTextArea speedSelect;
	private int variableSpeedStat = 5;
	private int levelPoints = 3;
	private JLabel pointsLeft;

	/**
	 * Sets the stats of a character using data from IO
	 * @ERROR: Editing a preexisting character does not seem to work properly
	 * @param characterName
	 * @throws IOException
	 */
	public Character(String characterName) throws IOException{
		//stats not written by writeCharacter listed here
		boolean isCreated = false;
		
		BufferedReader reader = new BufferedReader(new FileReader("characterList.txt"));
		String temp;
		while((temp = reader.readLine()) != null){
			String IOLineName = temp.substring(0, temp.indexOf(','));			
			if(IOLineName.trim().compareToIgnoreCase(characterName) == 0){
				isCreated = true;
			}
		}
		
		if(isCreated){
			setName(getIOName(characterName));
			setAttack(getIOAttack(characterName));
			setDefense(getIODefense(characterName));
			setSpeed(getIOSpeed(characterName));
			setEvasiveness(getIOEvasiveness(characterName));
			setHealth(getIOHealth(characterName));
			isDead = false;
			for(int i = 0; i < 4; i++){
				attackList[i] = new Attacks(getMoveNumber(getName(), i));
			}
			itemLinkedList.clear();
			for(int i = 0; i < 4; i++){
				itemLinkedList.add(new Item(getItemName(getName(), i)));
			}
			setExperience(getIOExperience(characterName));
			setExperienceBar(getIOExperienceBar(characterName));
			setLevel(getIOLevel(characterName));
		} else{
			JOptionPane alert = new JOptionPane();
			alert.showMessageDialog(null, "THAT CHARACTER DOESN'T EXIST!", "alert", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Sets the stats of a character from IO
	 * @param lineNumber
	 * @throws IOException
	 */
	public Character(int lineNumber) throws IOException{
		//stats not written by writeCharacter listed here
		boolean isCreated = false;
		
		String characterLine = getLineData(lineNumber);
		String characterName = characterLine.substring(0, characterLine.indexOf(','));
		
		BufferedReader reader = new BufferedReader(new FileReader("characterList.txt"));
		String temp;
		while((temp = reader.readLine()) != null){
			String IOLineName = temp.substring(0, temp.indexOf(','));			
			if(IOLineName.trim().compareToIgnoreCase(characterName) == 0){
				isCreated = true;
			}
		}
		
		if(isCreated){
			setName(getIOName(characterName));
			setAttack(getIOAttack(characterName));
			setDefense(getIODefense(characterName));
			setSpeed(getIOSpeed(characterName));
			setEvasiveness(getIOEvasiveness(characterName));
			setHealth(getIOHealth(characterName));
			isDead = false;
			for(int i = 0; i < 4; i++){
				attackList[i] = new Attacks(getMoveNumber(getName(), i));
			}
			itemLinkedList.clear();
			for(int i = 0; i < 4; i++){
				itemLinkedList.add( new Item(getItemName(getName(), i)));
			}
			setExperience(getIOExperience(characterName));
			setExperienceBar(getIOExperienceBar(characterName));
			setLevel(getIOLevel(characterName));
		} else{
			JOptionPane alert = new JOptionPane();
			alert.showMessageDialog(null, "THAT CHARACTER DOESN'T EXIST!", "alert", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Uses writeCharacter() to create a new character with user specified stats
	 * @throws IOException
	 */
	public Character() throws IOException{
		permaHealthStat = 25;
		healthStat = permaHealthStat;
		experienceStat = 0;
		level = 1;
		experienceBar = 10;
		evasivenessStat = tempEvasiveness;   

		for(int i = 0; i < 4; i++){
			attackList[i] = new Attacks(7);
		}
		itemLinkedList.clear();
		for(int i = 0; i < 4; i++){
			itemLinkedList.add(new Item("default"));
		}

		writeCharacter();
	}

	/**
	 * Uses writeCharacter() to create a new character with user specified stats, and sets specified colors and coordinates
	 * @param anX
	 * @param anY
	 * @param color
	 * @throws IOException
	 */
	public Character(int anX, int anY, Color color) throws IOException{
		permaHealthStat = 25;
		healthStat = permaHealthStat;
		experienceStat = 0;
		level = 1;
		experienceBar = 10;
		evasivenessStat = tempEvasiveness;   

		for(int i = 0; i < 4; i++){
			attackList[i] = new Attacks(7);
		}
		itemLinkedList.clear();
		for(int i = 0; i < 4; i++){
			itemLinkedList.add(new Item("default"));
		}
		
		this.color = color;
		x = anX;
		y = anY;
		//evasivenessStat = tempEvasiveness;    not used yet

		writeCharacter();
		System.out.println("default created");
	}

	/**
	 * calls the nextTurn() in all items to reduce cooldown on attacks
	 */
	public void nextTurn()
	{
		for(Item c : itemLinkedList)
		{
			c.nextTurn();
		}
	}

	/**
	 * adds item to the char's items
	 * @param a
	 */
	public void addItem1(Item a)
	{
		if(!itemLinkedList.contains(a))
			itemLinkedList.add(a);
	}
	
	/**
	 * gets the SYSTEM name of the character
	 * @return name
	 */
	public String getName(){
		return name;
	}
	/**
	 * gets the IO name of the character
	 * @param returnName
	 * @return name
	 * @throws IOException
	 */
	public String getIOName(String characterName) throws IOException{
		String sb2String = getLineData(getLineNumber(characterName));
		int numberOfAppearances = getNumberOfAppearances(',', getLineNumber(characterName));
		String returnName = null;

		int beginIndex = 0;
		int endIndex = sb2String.indexOf(',');

		for(int i = 0; i < numberOfAppearances; i++){
			if(i == 0){
				returnName = sb2String.substring(beginIndex, endIndex);
				break;
			}
			if(i != numberOfAppearances){
				beginIndex = endIndex + 2;
				endIndex = sb2String.indexOf(",", endIndex + 1);
			} 
		}

		return returnName;

	}	
	/**
	 * sets the SYSTEM name of the character
	 * @param input
	 * @return name
	 */
	public String setName(String input){
		name = input;
		return name;
	}
	/**
	 * sets the IO and SYSTEM name of the character
	 * @param characterName
	 * @param input
	 * @return name
	 */
	public String setIOName(String characterName, String input){
		try{
			replace(characterName, 1, input);
		} catch (IOException e){
		}

		name = input;
		return name;
	}

	/**
	 * gets the SYSTEM attackStat of the character
	 * @return attackStat
	 */
	public int getAttack(){
		return attackStat;
	}
	/**
	 * gets the IO attackStat of the character
	 * @param characterName
	 * @return returnAttack
	 * @throws IOException
	 */
	public int getIOAttack(String characterName) throws IOException{
		String sb2String = getLineData(getLineNumber(characterName));
		int numberOfAppearances = getNumberOfAppearances(',', getLineNumber(characterName));
		int returnAttack = -1;

		int beginIndex = 0;
		int endIndex = sb2String.indexOf(',');

		for(int i = 0; i < numberOfAppearances; i++){
			if(i == 1){
				returnAttack = Integer.parseInt(sb2String.substring(beginIndex, endIndex));
				break;
			}
			if(i != numberOfAppearances){
				beginIndex = endIndex + 2;
				endIndex = sb2String.indexOf(",", endIndex + 1);
			} 
		}

		return returnAttack;
	}
	/**
	 * sets the SYSTEM attackStat of a character
	 * @param input
	 * @return attackStat
	 */
	public int setAttack(int input){
		attackStat = input;
		return attackStat;
	}
	/**
	 * sets the IO and SYSTEM attackStat of a character 
	 * @param input
	 * @return attackStat
	 */
	public int setIOAttack(int input){

		try{
			replace(getName(), 2, Integer.toString(input));
		} catch (IOException e){
		}

		attackStat = input;
		return attackStat;
	}

	/**
	 * gets the SYSTEM defenseStat of a character
	 * @return defenseStat
	 */
	public int getDefense(){
		return defenseStat;
	}
	/**
	 * gets the IO defenseStat of a character
	 * @param characterName
	 * @return returnDefense
 	 * @throws IOException
	 */
	public int getIODefense(String characterName) throws IOException{
		String sb2String = getLineData(getLineNumber(characterName));
		int numberOfAppearances = getNumberOfAppearances(',', getLineNumber(characterName));
		int returnDefense = -1;

		int beginIndex = 0;
		int endIndex = sb2String.indexOf(',');

		for(int i = 0; i < numberOfAppearances; i++){
			if(i == 2){
				returnDefense = Integer.parseInt(sb2String.substring(beginIndex, endIndex));
				break;
			}
			if(i != numberOfAppearances){
				beginIndex = endIndex + 2;
				endIndex = sb2String.indexOf(",", endIndex + 1);
			} 
		}

		return returnDefense;
	}
	/**
	 * sets the SYSTEM value of defenseStat
	 * @param input
	 * @return defenseStat
	 */
	public int setDefense(int input){
		defenseStat = input;
		return defenseStat;
	}
	/**
	 * sets the IO and SYSTEM value of defenseStat
	 * @param input
	 * @return defenseStat
	 */
	public int setIODefense(int input){
		try{
			replace(getName(), 3, Integer.toString(input));
		} catch (IOException e){
		}

		defenseStat = input;
		return defenseStat;
	}

	/**
	 * gets the SYSTEM speedStat
	 * @return speedStat
	 */
	public int getSpeed(){
		return speedStat;
	}
	/**
	 * gets the IO speedStat of a character
	 * @param characterName
	 * @return returnSpeed
	 * @throws IOException
	 */
	public int getIOSpeed(String characterName) throws IOException{
		String sb2String = getLineData(getLineNumber(characterName));
		int numberOfAppearances = getNumberOfAppearances(',', getLineNumber(characterName));
		int returnSpeed = -1;

		int beginIndex = 0;
		int endIndex = sb2String.indexOf(',');

		for(int i = 0; i < numberOfAppearances; i++){
			if(i == 3){
				returnSpeed = Integer.parseInt(sb2String.substring(beginIndex, endIndex));
				break;
			}
			if(i != numberOfAppearances){
				beginIndex = endIndex + 2;
				endIndex = sb2String.indexOf(",", endIndex + 1);
			} 
		}

		return returnSpeed;
	}
	/**
	 * sets the SYSTEM speedStat of a character
	 * @param input
	 * @return speedStat
	 */
	public int setSpeed(int input){
		speedStat = input;
		return speedStat;
	}
	/**
	 * sets the IO and SYSTEM speedStat of a character
	 * @param input
	 * @return speedStat
	 */
	public int setIOSpeed(int input){
		try{
			replace(getName(), 4, Integer.toString(input));
		} catch (IOException e){
		}

		speedStat = input;
		return speedStat;
	}

	/**
	 * returns the SYSTEM evasivenessStat
	 * @return evasivenessStat
	 */
	public int getEvasiveness(){
		return evasivenessStat;
	}
	/**
	 * returns the IO and SYSTEM evasivenessStat
	 * @param characterName
	 * @return returnEvasiveness
	 * @throws IOException
	 */
	public int getIOEvasiveness(String characterName) throws IOException{
		String sb2String = getLineData(getLineNumber(characterName));
		int numberOfAppearances = getNumberOfAppearances(',', getLineNumber(characterName));
		int returnEvasiveness = -1;

		int beginIndex = 0;
		int endIndex = sb2String.indexOf(',');

		for(int i = 0; i < numberOfAppearances; i++){
			if(i == 4){
				returnEvasiveness = Integer.parseInt(sb2String.substring(beginIndex, endIndex));
				break;
			}
			if(i != numberOfAppearances){
				beginIndex = endIndex + 2;
				endIndex = sb2String.indexOf(",", endIndex + 1);
			} 
		}

		return returnEvasiveness;
	}
	/**
	 * sets the SYSTEM evasivenessStat
	 * @param input
	 * @return evasivenessStat
	 */
	public int setEvasiveness(int input){
		evasivenessStat = input;
		return evasivenessStat;
	}
	/**
	 * sets the IO and SYSTEM evasivenessStat
	 * @param input
	 * @return evasivenessStat
	 */
	public int setIOEvasiveness(int input){
		try{
			replace(getName(), 5, Integer.toString(input));
		} catch (IOException e){
		}

		evasivenessStat = input;
		return evasivenessStat;
	}

	/**
	 * returns the SYSTEm healthStat
	 * @return healthStat
	 */
	public int getHealth(){
		return healthStat;
	}
	/**
	 * returns the IO health stat
	 * @param characterName
	 * @return returnHealth
	 * @throws IOException
	 */
	public int getIOHealth(String characterName) throws IOException{
		String sb2String = getLineData(getLineNumber(characterName));
		int numberOfAppearances = getNumberOfAppearances(',', getLineNumber(characterName));
		int returnHealth = -1;

		int beginIndex = 0;
		int endIndex = sb2String.indexOf(',');

		for(int i = 0; i < numberOfAppearances; i++){
			if(i == 5){
				returnHealth = Integer.parseInt(sb2String.substring(beginIndex, endIndex));
				break;
			}
			if(i != numberOfAppearances){
				beginIndex = endIndex + 2;
				endIndex = sb2String.indexOf(",", endIndex + 1);
			}
		}
		return returnHealth;
	}
	/**
	 * sets the SYSTEM healthStat
	 * @param input
	 * @return healthStat
	 */
	public int setHealth(int input){
		healthStat = input;
		return healthStat;
	}	
	/**
	 * sets the IO and SYSTEM healthStat
	 * @param input
	 * @return healthStat
	 */
	public int setIOHealth(int input){
		try{
			replace(getName(), 6, Integer.toString(input));
		} catch (IOException e){
		}

		healthStat = input;
		return healthStat;
	}

	/**
	 * gets the SYSTEM experienceStat
	 * @return experienceStat
	 */
	public int getExperience(){
		return experienceStat;
	}
	/**
	 * gets the IO experienceStat
	 * @param characterName
	 * @return returnExperience
	 * @throws IOException
	 */
	public int getIOExperience(String characterName) throws IOException{
		String sb2String = getLineData(getLineNumber(characterName));
		int numberOfAppearances = getNumberOfAppearances(',', getLineNumber(characterName));
		int returnExperience = -1;

		int beginIndex = 0;
		int endIndex = sb2String.indexOf(',');

		for(int i = 0; i < numberOfAppearances; i++){
			if(i == 10){
				returnExperience = Integer.parseInt(sb2String.substring(beginIndex, endIndex));
				break;
			}
			if(i != numberOfAppearances){
				beginIndex = endIndex + 2;
				endIndex = sb2String.indexOf(",", endIndex + 1);
			}
		}
		return returnExperience;
	}
	/**
	 * sets the SYSTEM experienceStat
	 * @param input
	 * @return experienceStat
	 */
	public int setExperience(int input){
		experienceStat = input;
		return experienceStat;
	}
	/**
	 * sets the SYSTEM and IO experienceStat
	 * @param input
	 * @return experienceStat
	 */
	public int setIOExperience(int input){
		try{
			replace(getName(), 11, Integer.toString(input));
		} catch (IOException e){
		}

		experienceStat = input;
		return experienceStat;
	}

	/**
	 * gets the SYSTEM experienceBar
	 * @return experienceBar
	 */
	public int getExperienceBar()
	{
		return experienceBar;
	}
	/**
	 * gets the IO experienceBar
	 * @param characterName
	 * @return returnExperienceBar
	 * @throws IOException
	 */
	public int getIOExperienceBar(String characterName) throws IOException{
		String sb2String = getLineData(getLineNumber(characterName));
		int numberOfAppearances = getNumberOfAppearances(',', getLineNumber(characterName));
		int returnExperienceBar = -1;

		int beginIndex = 0;
		int endIndex = sb2String.indexOf(',');

		for(int i = 0; i < numberOfAppearances; i++){
			if(i == 11){
				returnExperienceBar = Integer.parseInt(sb2String.substring(beginIndex, endIndex));
				break;
			}
			if(i != numberOfAppearances){
				beginIndex = endIndex + 2;
				endIndex = sb2String.indexOf(",", endIndex + 1);
			}
		}
		return returnExperienceBar;
	}
	/**
	 * sets the SYSTEM experienceBar
	 * @param input
	 * @return experienceBar
	 */
	public int setExperienceBar(int input)
	{
		experienceBar = input;
		return experienceBar;
	}
	/**
	 * sets the SYSTEM and IO experienceBar
	 * @param input
	 * @return experienceBar
	 */ 
	public int setIOExperienceBar(int input){
		try{
			replace(getName(), 12, Integer.toString(input));
		} catch (IOException e){
		}

		experienceBar = input;
		return experienceBar;
	}
	
	/**
	 * gets the SYSTEM level
	 * @return level
	 */
	public int getLevel()
	{
		return level;
	}
	/**
	 * gets the IO level
	 * @param characterName
	 * @return returnLevel
	 * @throws IOException
	 */
	public int getIOLevel(String characterName) throws IOException{
		String sb2String = getLineData(getLineNumber(characterName));
		int numberOfAppearances = getNumberOfAppearances(',', getLineNumber(characterName));
		int returnLevel = -1;

		int beginIndex = 0;
		int endIndex = sb2String.indexOf(',');

		for(int i = 0; i < numberOfAppearances; i++){
			if(i == 12){
				returnLevel = Integer.parseInt(sb2String.substring(beginIndex, endIndex));
				break;
			}
			if(i != numberOfAppearances){
				beginIndex = endIndex + 2;
				endIndex = sb2String.indexOf(",", endIndex + 1);
			}
		}
		return returnLevel;
	}
	/**
	 * sets the SYS level
	 * @param aLevel
	 * @return level
	 */
	public int setLevel(int aLevel)
	{
		level = aLevel;
		return level;
	}
	/**
	 * sets the IO and SYSTEM level
	 * @param input
	 * @return level
	 */
	public int setIOLevel(int input){
		try{
			replace(getName(), 13, Integer.toString(input));
		} catch (IOException e){
		}

		level = input;
		return level;
	}
	
	/**
	 * returns the healthStat of the character to permaHealthStat
	 */
	public void fullHeal()
	{
		healthStat = permaHealthStat;
	}
	
	/**
	 * adds an Attack to the character's attackList[]
	 * @param anAttack
	 * @throws IOException
	 */
	public void addAttack(Attacks anAttack) throws IOException{ // automatically adds given move to lowest slot

		boolean added = false;
		boolean moveExisted = false;


		for(int i = 0; i < getNumberOfAppearances(',', getLineNumber()); i++){
			String sb2String = getLineData(getLineNumber());
			int beginIndex = 0;
			int endIndex = sb2String.indexOf(',');

			//checks to see if attack's name is already in IO
			if(sb2String.substring(beginIndex, endIndex).trim().compareToIgnoreCase(anAttack.getName()) != 0){
				beginIndex = endIndex + 2;
				endIndex = sb2String.indexOf(',', endIndex + 1);
			} else if (sb2String.substring(beginIndex, endIndex).trim().compareToIgnoreCase(anAttack.getName()) == 0){
				moveExisted = true; 
			}
		}

		for(int i = 0; i < 4; i++){
			if(attackList[i].getName().compareToIgnoreCase(anAttack.getName()) == 0 )
			{
				moveExisted = true;
			}
		}

		for(int i = 0; i < 4; i++)
		{
			if(attackList[i].getName().compareToIgnoreCase(new Attacks(7).getName()) == 0
					&& !added
					&& !moveExisted)
			{
				attackList[i] = anAttack;
				replace(getName(), (i + 7), Integer.toString(anAttack.getIONumber()));	//bug possible here

				added = true;
				System.out.println(i);
				System.out.println("Congratulations!! " +
						name + " has learned " + anAttack.getName() + "!!!!");	
			}
		}


		if(!added && !moveExisted)
		{
			System.out.println("Could not learn " + anAttack.getName() + ". Moveset full");
			removeAttack(anAttack);
		}
		else if(moveExisted)
		{
			System.out.println(name + " already knows this move!");
		}
	}
	/**
	 * adds an attack to the character's attackList[] with a specified slot
	 * @param anAttack
	 * @param slot
	 */
	public void addAttack(Attacks anAttack, int slot){ //tries to add move to given slot, slot = user input
		if ((slot - 1) < 4){
			if (attackList[slot] == null){
				attackList[slot] = anAttack;
			}
			else{
				System.out.println("This slot has been taken, choose another");
				addAttack(anAttack, slot);
			}
		}
	}
	/**
	 * gives the player a dialogue to remove an attack from attackList[]
	 * @throws IOException
	 */
	public void removeAttack() throws IOException{

		boolean removed = false;
		String theAttack = null;
		int i = 0;

		System.out.println("Which move would you like to remove?");
		for(i = 0; i < 4; i++){
			if(attackList[i].getName().compareToIgnoreCase(new Attacks(7).getName()) != 0){
				System.out.println(attackList[i].getName());
			}
		}

		JPanel removePanel = new JPanel();

		JTextArea whichAttack = new JTextArea(1,10);
		removePanel.add(whichAttack);

		JOptionPane removePane = new JOptionPane();
		int result = removePane.showConfirmDialog(null, removePanel, 
				"Delete a Move", JOptionPane.OK_CANCEL_OPTION);	

		if(result == JOptionPane.OK_OPTION){
			theAttack = whichAttack.getText();
			for(i = 0; i < 4; i++){
				if(attackList[i].getName().compareToIgnoreCase(theAttack.trim()) == 0){
					attackList[i] = new Attacks(7);
					System.out.println("removed " + theAttack);
					replace(getName(), (i + 7), "7");
					removed = true;
					break;
				}
			}
		} else{
			System.out.println("Nothing was removed");
			removed = true;
		}

		if (!removed){
			System.out.println("That move does not exist! Would you still like to delete a move? (type yes or no)");
			Scanner scan2 = new Scanner(System.in);
			String input2 = scan2.nextLine().toLowerCase();

			boolean isValid = true;

			while(isValid == true){
				if(input2.compareToIgnoreCase("yes") == 0){
					removeAttack();
					isValid = false;
					i = 5;
				}
				else if(input2.compareToIgnoreCase("no") == 0){
					isValid = false;
					i = 5;
				} else{
					isValid = true;
					i = 5;
				}
			}
		}
	}
	/**
	 * removes an attack with a given dialogue and replaces it with a given attack
	 * @ERROR test this for completion
	 * @param replaceAttack
	 * @throws IOException
	 */
	public void removeAttack(Attacks replaceAttack) throws IOException{

		boolean removed = false;
		String theAttack = null;
		int i = 0;

		System.out.println("Which move would you like to remove?");
		for(i = 0; i < 4; i++){
			if(attackList[i].getName().compareToIgnoreCase(new Attacks(7).getName()) != 0){
				System.out.println(attackList[i].getName());
			}
		}

		JPanel removePanel = new JPanel();

		JTextArea whichAttack = new JTextArea(1,10);
		removePanel.add(whichAttack);

		JOptionPane removePane = new JOptionPane();
		int result = removePane.showConfirmDialog(null, removePanel, 
				"Delete a Move", JOptionPane.OK_CANCEL_OPTION);	

		if(result == JOptionPane.OK_OPTION){
			theAttack = whichAttack.getText();
			for(i = 0; i < 4; i++){
				if(attackList[i].getName().compareToIgnoreCase(theAttack.trim()) == 0){
					attackList[i] = new Attacks(7);
					System.out.println("removed " + theAttack);
					replace(getName(), (i + 7), Integer.toString(replaceAttack.getIONumber()));
					removed = true;
					break;
				}
			}
		} else{
			System.out.println("Nothing was removed");
			removed = true;
		}

		if (!removed){
			System.out.println("That move does not exist! Would you still like to delete a move? (type yes or no)");
			Scanner scan2 = new Scanner(System.in);
			String input2 = scan2.nextLine().toLowerCase();

			boolean isValid = true;

			while(isValid == true){
				if(input2.compareToIgnoreCase("yes") == 0){
					removeAttack();
					isValid = false;
					i = 5;
				}
				else if(input2.compareToIgnoreCase("no") == 0){
					isValid = false;
					i = 5;
				} else{
					isValid = true;
					i = 5;
				}
			}
		}
	}

	/**
	 * adds an item to the character's itemLinkedList
	 * @param anItem
	 * @throws IOException
	 */
	public void addItem(Item anItem) throws IOException{
		boolean isAdded = false;
		boolean itemExisted = false;
		
		String sb2String = getLineData(getLineNumber());
		int beginIndex = 0;
		int endIndex = sb2String.indexOf(',');
		
		for(int i = 0; i < getNumberOfAppearances(',', getLineNumber()); i++){	
			//checks to see if item's name is already in IO
			if(sb2String.substring(beginIndex, endIndex).trim().compareToIgnoreCase(anItem.getName()) != 0){
				beginIndex = endIndex + 2;
				endIndex = sb2String.indexOf(',', endIndex + 1);
			} else if (sb2String.substring(beginIndex, endIndex).trim().compareToIgnoreCase(anItem.getName()) == 0){
				itemExisted = true; 
				break;
			}
		}
		
		for(int i = 0; i < 4; i++){
			if(itemLinkedList.get(i).getName().compareToIgnoreCase(anItem.getName()) == 0 )
			{
				itemExisted = true;
			}
		}
		
		for(int i = 0; i < 4; i++)
		{
			if(itemLinkedList.get(i).getName().compareToIgnoreCase(new Item("default").getName()) == 0
					&& !isAdded
					&& !itemExisted)
			{
				itemLinkedList.set(i,anItem);
				replace(getName(), (i + 14), anItem.getName());	//bug possible here

				isAdded = true;
				System.out.println("Congratulations!! " +
						name + " has acquired " + anItem.getName() + "!!!!");	
			}
		}
		
		for(int i = 0; i < 4; i ++){
			System.out.println(itemLinkedList.get(i).getName());
		}
		
		if(!isAdded && !itemExisted)
		{
			System.out.println("Could not learn " + anItem.getName() + ". Moveset full");
			removeItem();
		}
		else if(itemExisted)
		{
			System.out.println(name + " already has this item!");
		}
	}
	/**
	 * removes an item from the character's itemLinkedList
	 * @ERROR test for completion
	 * @throws IOException
	 */
	public void removeItem() throws IOException{
		boolean removed = false;
		String theItem = null;
		int i = 0;

		System.out.println("Which item would you like to remove?");
		for(i = 0; i < 4; i++){
			if(itemLinkedList.get(i).getName().compareToIgnoreCase(new Item("default").getName()) != 0){
				System.out.println(itemLinkedList.get(i).getName());
			}
		}
		
		JPanel removePanel = new JPanel();

		JTextArea whichItem = new JTextArea(1,10);
		removePanel.add(whichItem);

		JOptionPane removePane = new JOptionPane();
		int result = removePane.showConfirmDialog(null, removePanel, 
				"Remove an Item", JOptionPane.OK_CANCEL_OPTION);	
		
		if(result == JOptionPane.OK_OPTION){
			theItem = whichItem.getText();
			for(i = 0; i < 4; i++){
				if(itemLinkedList.get(i).getName().compareToIgnoreCase(theItem.trim()) == 0){
					itemLinkedList.set(i,new Item("default"));
					System.out.println("removed " + theItem);
					replace(getName(), (i + 14), "default");
					removed = true;
					break;
				}
			}
		} else{
			System.out.println("Nothing was removed");
			removed = true;
		}
		
		if (!removed){
			System.out.println("That item does not exist! Would you still like to remove an item? (type yes or no)");
			Scanner scan2 = new Scanner(System.in);
			String input2 = scan2.nextLine().toLowerCase();

			boolean isValid = true;

			while(isValid == true){
				if(input2.compareToIgnoreCase("yes") == 0){
					removeItem();
					isValid = false;
					break;
				}
				else if(input2.compareToIgnoreCase("no") == 0){
					isValid = false;
					break;
				} else{
					isValid = true;
					break;
				}
			}
		}
	}
	/**
	 * removes a specified item
	 * @ERROR test for completion
	 * @param anItem
	 * @throws IOException
	 */
	public void removeItem(Item anItem) throws IOException{
		boolean removed = false;
		for(int i = 0; i < 4; i++){
			if(itemLinkedList.get(i).getName().compareToIgnoreCase(anItem.getName()) == 0){
				itemLinkedList.set(i,new Item("default"));
				removed = true;
				replace(getName(), i + 14, "default");
			}
		}
		if(removed == false){
			System.out.println("The item you'd like to remove isn't there!");
		}
	}
	
	public int attack(int damage)
	{
		if(damage >= 0)
		{
			healthStat = healthStat - damage/defenseStat;
			if(healthStat < 0 )
			{
				healthStat = 0;
				isDead = true;
			}
			return healthStat;
		}
		else
		{
			return -1;
		}
	}
	
	/**
	 * Applies the item's stat changes to the specified character
	 * @param theCharacter
	 */
	public void use(Item theItem) throws IOException{
		setSpeed(getSpeed() + theItem.getSpeed());
		setDefense(getDefense() + theItem.getDefense());
		setHealth(getHealth() + theItem.getHealth());
		removeItem(theItem);
		System.out.println("stats modified");
	}
	
	public boolean isDead()
	{
		return isDead;
	}
	public boolean kill()
	{
		isDead = true;
		return isDead;
	}
	
	/**
	 * Gets the move number of a specified attack
	 * @ERROR test for completion
	 * @param characterName
	 * @param slot
	 * @return moveNumber
	 * @throws IOException
	 */
	public int getMoveNumber(String characterName, int slot) throws IOException{//returns the line number of the move
		String sb2String = getLineData(getLineNumber(characterName));
		int numberOfAppearances = getNumberOfAppearances(',', getLineNumber(characterName));
		int moveNumber = 7;

		int beginIndex = 0;
		int endIndex = sb2String.indexOf(',');

		for(int i = 0; i < numberOfAppearances; i++){
			if(i == slot + 6){
				moveNumber = Integer.parseInt(sb2String.substring(beginIndex, endIndex));
				break;
			}
			if(i != numberOfAppearances){
				beginIndex = endIndex + 2;
				endIndex = sb2String.indexOf(",", endIndex + 1);
			} 
		}
		return moveNumber;
	}
	
	/**
	 * Gets the name of a specified item
	 * @param characterName
	 * @param slot
	 * @return itemName
	 * @throws IOException
	 */
	public String getItemName(String characterName, int slot) throws IOException{
		String sb2String = getLineData(getLineNumber(characterName));
		int numberOfAppearances = getNumberOfAppearances(',', getLineNumber(characterName));
		String itemName = "default";
		
		int beginIndex = 0;
		int endIndex = sb2String.indexOf(',');
		
		for(int i = 0; i < numberOfAppearances; i++){
			if(i == slot + 13){
				itemName = sb2String.substring(beginIndex, endIndex);
				break;
			}
			if(i != numberOfAppearances){
				beginIndex = endIndex + 2;
				endIndex = sb2String.indexOf(",", endIndex + 1);
			}
		}
		return itemName;
	}

	/**
	 * levels up a character
	 * @return congratulations
	 * @throws IOException
	 */
	public String levelUp() throws IOException
	{
		setIOAttack(getAttack() + 3);
		setIODefense(getDefense() + 3);
		setIOSpeed(getSpeed() + 3);
		setIOHealth(getHealth() + 3);
		setIOEvasiveness(getEvasiveness() + 3);
		setIOExperience(0);
		setIOExperienceBar(getExperience() + 5);
		setIOLevel(getLevel() + 1);
		String congratulations = "Congratulations: " + name + 
		" just leveled up to level " + level + "!!";

		//These should theoretically use the switch notation to make adding a move more efficient
		//Also when we start creating actual characters (subclasses of character), these should be in there instead of here
		if(level == 2)
		{
			this.addAttack(new Attacks(1));
		}
		if(level == 3)
		{
			this.addAttack(new Attacks(2));
		}
		if(level == 4)
		{
			this.addAttack(new Attacks(3));
		}
		if(level == 5)
		{
			this.addAttack(new Attacks(4));
		}
		return congratulations;
	}
	
	/**
	 * draws the character's GUI representation
	 * @param g
	 */
	public void create(Graphics g)
	{
		g.setColor(color);
		g.drawOval(25, 2, 50, 50);
		g.fillOval(25, 2, 50, 50);

	}

	/**
	 * Lists a character's stats from IO 
	 * IS NOT USED TO SET ANYTHING, TESTING PURPOSES ONLY
	 * @throws IOException
	 */
	public void readCharacter() throws IOException{
		int k = getLineNumber(getName());
		int numberOfAppearances = getNumberOfAppearances(',', k);
		String sb2String = getLineData(k);

		int beginIndex = 0;
		int endIndex = sb2String.indexOf(",");

		System.out.println(sb2String);
		// Does the bulk of the work: Sets the name, attack, defense, speed, etc.
		for(int i = 0; i < numberOfAppearances; i++){
			//set name and attack
			if(i == 0){
				//setName((String) sb2String.subSequence(beginIndex, endIndex));
				System.out.println("name is: " + getName());
			}
			else if(i == 1){
				//setAttack(Integer.parseInt((String) sb2String.subSequence(beginIndex, endIndex)));
				System.out.println("attack is: " + getAttack());
			}  
			else if(i == 2){
				//setDefense(Integer.parseInt((String) sb2String.subSequence(beginIndex, endIndex)));
				System.out.println("defense is: " + getDefense());
			}

			else if(i == 3){
				//setSpeed(Integer.parseInt((String) sb2String.subSequence(beginIndex, endIndex)));
				System.out.println("speed is: " + getSpeed());
			}

			//resets the function to read for the next "chunk", as segregated by ","
			if(i != numberOfAppearances){
				beginIndex = endIndex + 2;
				endIndex = sb2String.indexOf(",", endIndex + 1);
			} 
		}
	}
	/**
	 * Reads off the stats of a specified character, TESTING PURPOSES ONLY
	 * @param theName
	 * @throws IOException
	 */
	public void readCharacter(String theName) throws IOException{
		int k = getLineNumber(theName);
		int numberOfAppearances = getNumberOfAppearances(',', k);
		String sb2String = getLineData(k);

		int beginIndex = 0;
		int endIndex = sb2String.indexOf(",");

		System.out.println(sb2String);
		// Does the bulk of the work: Sets the name, attack, defense, speed, etc.
		for(int i = 0; i < numberOfAppearances; i++){
			//set name and attack
			if(i == 0){
				//setName((String) sb2String.subSequence(beginIndex, endIndex));
				System.out.println("name is: " + getName());
			}
			else if(i == 1){
				//setAttack(Integer.parseInt((String) sb2String.subSequence(beginIndex, endIndex)));
				System.out.println("attack is: " + getAttack());
			}  
			else if(i == 2){
				//setDefense(Integer.parseInt((String) sb2String.subSequence(beginIndex, endIndex)));
				System.out.println("defense is: " + getDefense());
			}

			else if(i == 3){
				//setSpeed(Integer.parseInt((String) sb2String.subSequence(beginIndex, endIndex)));
				System.out.println("speed is: " + getSpeed());
			}

			//resets the function to read for the next "chunk", as segregated by ","
			if(i != numberOfAppearances){
				beginIndex = endIndex + 2;
				endIndex = sb2String.indexOf(",", endIndex + 1);
			} 
		}
	}

	/**
	 * Opens dialogue for user to specify stats of a new character
	 * @throws IOException
	 */
	public void writeCharacter() throws IOException{

		GridLayout theLayout = new GridLayout(5,4);

		JPanel createPanel = new JPanel();
		createPanel.setLayout(theLayout);


		JLabel nameLabel = new JLabel("Name: ");
		JTextArea nameArea = new JTextArea(1,1);

		pointsLeft = new JLabel("Points Left " + levelPoints);

		JLabel attackLabel = new JLabel("Attack");
		attackSelect = new JTextArea(Integer.toString(variableAttackStat),1,1);
		attackSelect.setEditable(false);
		JButton attackUp = new JButton(">");
		upAttackListener upAttack = new upAttackListener();
		attackUp.addActionListener(upAttack);
		JButton attackDown = new JButton("<");
		downAttackListener downAttack = new downAttackListener();
		attackDown.addActionListener(downAttack);

		JLabel defenseLabel = new JLabel("Defense");
		defenseSelect = new JTextArea(Integer.toString(variableDefenseStat),1,1);
		defenseSelect.setEditable(false);
		JButton defenseUp = new JButton(">");
		upDefenseListener upDefense = new upDefenseListener();
		defenseUp.addActionListener(upDefense);
		JButton defenseDown = new JButton("<");
		downDefenseListener downDefense = new downDefenseListener();
		defenseDown.addActionListener(downDefense);

		JLabel speedLabel = new JLabel("Speed");
		speedSelect = new JTextArea(Integer.toString(variableSpeedStat),1,1);
		speedSelect.setEditable(false);
		JButton speedUp = new JButton(">");
		upSpeedListener upSpeed = new upSpeedListener();
		speedUp.addActionListener(upSpeed);
		JButton speedDown = new JButton("<");
		downSpeedListener downSpeed = new downSpeedListener();
		speedDown.addActionListener(downSpeed);

		createPanel.add(nameLabel);
		createPanel.add(nameArea);
		createPanel.add(new JLabel());
		createPanel.add(new JLabel());

		createPanel.add(attackLabel);
		createPanel.add(attackSelect);
		createPanel.add(attackDown);
		createPanel.add(attackUp);

		createPanel.add(defenseLabel);
		createPanel.add(defenseSelect);
		createPanel.add(defenseDown);
		createPanel.add(defenseUp);

		createPanel.add(speedLabel);
		createPanel.add(speedSelect);
		createPanel.add(speedDown);
		createPanel.add(speedUp);

		createPanel.add(pointsLeft);
		createPanel.add(new JLabel());
		createPanel.add(new JLabel());
		createPanel.add(new JLabel());


		JOptionPane createPrompt = new JOptionPane("Character Creation");
		int result = createPrompt.showConfirmDialog(null, createPanel, 
				"Create your character", JOptionPane.OK_CANCEL_OPTION);	

		//IO Begins
		PrintWriter outputStream = new PrintWriter(new FileWriter("characterList.txt", true));

		if (result == JOptionPane.OK_OPTION) {
			//System.out.println("Success!");
			tempName = nameArea.getText();
			tempAttack = Integer.parseInt(attackSelect.getText());
			tempDefense = Integer.parseInt(defenseSelect.getText());
			tempSpeed = Integer.parseInt(speedSelect.getText());
			try{
				outputStream.println(tempName + ", " + tempAttack + ", " + tempDefense + ", " + tempSpeed + ", " + evasivenessStat + ", " + healthStat + ", " + attackList[0].getIONumber() + ", "  + 
						attackList[1].getIONumber() + ", "  + attackList[2].getIONumber() + ", "  + attackList[3].getIONumber() + ", "
						+ experienceStat + ", " + experienceBar + ", " + level + ", " +
						itemLinkedList.get(0).getName() + ", " + itemLinkedList.get(1).getName() + ", " + itemLinkedList.get(2).getName() + ", " + itemLinkedList.get(3).getName() + ",");
				setName(tempName);
				setAttack(tempAttack);
				setDefense(tempDefense);
				setSpeed(tempSpeed);
				setEvasiveness(evasivenessStat);
				setHealth(healthStat);
				setExperience(experienceStat);
				setExperienceBar(experienceBar);
				setLevel(level);
			}
			finally{
				if(outputStream != null){
					outputStream.close();
				}
			}		
		}
	}

	/**
	 * completely removes the IO line of a character
	 * @throws IOException
	 */
	public void removeCharacter() throws IOException{
		try{
			//CHUNK 1: Determines the line number on which the desired name is located
			BufferedReader reader1 = new BufferedReader(
					new FileReader("characterList.txt"));
			//gets the line theName is on (k = lineNumber)
			String temp = null;
			String lineToRemove = null;
			StringBuffer sb1 = new StringBuffer();	//temp string buffer that holds everything in the document
			int j = 0;	// j = number of lines in document
			while((temp = reader1.readLine()) != null){
				sb1.append(temp);
				sb1.append("\n");
				j++;

				int firstComma = temp.indexOf(',');
				String firstSequence = temp.substring(0, firstComma);

				if(firstSequence.compareToIgnoreCase(getName()) == 0){
					lineToRemove = temp;
				}
			}
			reader1.close();
			//System.out.println("document contents:" + "\n" + sb1);
			//System.out.println("total lines in document: " + j);
			//System.out.println("line of desired name: " + k);
			//System.out.println("contents of desired line: " + lineToRemove + "\n");


			//CHUNK TWO: Removes the desired line of characterList.txt and by deleting and rewriting
			//source: http://www.javadb.com/remove-a-line-from-a-text-file
			File inFile = new File("characterList.txt");
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

			BufferedReader reader2 = new BufferedReader(new FileReader("characterList.txt"));
			PrintWriter writer1 = new PrintWriter(new FileWriter(tempFile));

			String line = null;

			//Read from the original file and write to the new 
			//unless content matches data to be removed.
			while ((line = reader2.readLine()) != null) {
				if (!line.trim().equals(lineToRemove)) {
					writer1.println(line);
					writer1.flush();
					System.out.println("copied");
				}
			}
			writer1.close();
			reader2.close();

			//Delete the original file
			if (!inFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}
			//Rename the new file to the filename the original file had.
			if (!tempFile.renameTo(inFile))
				System.out.println("Could not rename file");	
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}
	/**
	 * Completely removes the IO line of a specified character
	 * @param theCharacterName
	 * @throws IOException
	 */
	public void removeCharacter(String theCharacterName) throws IOException{
		try{
			//CHUNK 1: Determines the line number on which the desired name is located
			BufferedReader reader1 = new BufferedReader(
					new FileReader("characterList.txt"));
			//gets the line theName is on (k = lineNumber)
			String temp = null;
			String lineToRemove = null;
			StringBuffer sb1 = new StringBuffer();	//temp string buffer that holds everything in the document
			int j = 0;	// j = number of lines in document
			while((temp = reader1.readLine()) != null){
				sb1.append(temp);
				sb1.append("\n");
				j++;

				int firstComma = temp.indexOf(',');
				String firstSequence = temp.substring(0, firstComma);

				if(firstSequence.compareToIgnoreCase(theCharacterName) == 0){
					lineToRemove = temp;
				}
			}
			reader1.close();
			//System.out.println("document contents:" + "\n" + sb1);
			//System.out.println("total lines in document: " + j);
			//System.out.println("line of desired name: " + k);
			//System.out.println("contents of desired line: " + lineToRemove + "\n");


			//CHUNK TWO: Removes the desired line of characterList.txt and by deleting and rewriting
			//source: http://www.javadb.com/remove-a-line-from-a-text-file
			File inFile = new File("characterList.txt");
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

			BufferedReader reader2 = new BufferedReader(new FileReader("characterList.txt"));
			PrintWriter writer1 = new PrintWriter(new FileWriter(tempFile));

			String line = null;

			//Read from the original file and write to the new 
			//unless content matches data to be removed.
			while ((line = reader2.readLine()) != null) {
				if (!line.trim().equals(lineToRemove)) {
					writer1.println(line);
					writer1.flush();
					System.out.println("copied");
				}
			}
			writer1.close();
			reader2.close();

			//Delete the original file
			if (!inFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}
			//Rename the new file to the filename the original file had.
			if (!tempFile.renameTo(inFile))
				System.out.println("Could not rename file");	
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
	}

	/**
	 * gets the line number of a specific character on characterList.txt
	 * @param theName
	 * @return k
	 * @throws IOException
	 */
	public int getLineNumber(String theName) throws IOException{

		int j = 0;	// j = number of lines in document
		int k = 0;	// k = line number of desired name

		try{
			//CHUNK 1: Determines the line number on which the desired name is located
			BufferedReader reader1 = new BufferedReader(
					new FileReader("characterList.txt"));
			//gets the line theName is on (k = lineNumber)
			String temp = null;
			StringBuffer sb1 = new StringBuffer();	//temp string buffer that holds everything in the document

			while((temp = reader1.readLine()) != null){
				sb1.append(temp);
				sb1.append("\n");
				j++;

				int firstComma = temp.indexOf(',');
				String firstSequence = temp.substring(0, firstComma);

				if(firstSequence.compareToIgnoreCase(theName) == 0){
					k = j;
				}
			}
			reader1.close();
			//System.out.println("document contents:" + "\n" + sb1);
			//System.out.println("total lines in document: " + j);
			//System.out.println("line of desired name: " + k);
		} catch(IOException ex){
			ex.printStackTrace();
		}
		return k;
	}
	/**
	 * gets the line number of the character in characterList.txt
	 * @return k
	 * @throws IOException
	 */
	public int getLineNumber() throws IOException{

		int j = 0;	// j = number of lines in document
		int k = 0;	// k = line number of desired name

		try{
			//CHUNK 1: Determines the line number on which the desired name is located
			BufferedReader reader1 = new BufferedReader(
					new FileReader("characterList.txt"));
			//gets the line theName is on (k = lineNumber)
			String temp = null;
			StringBuffer sb1 = new StringBuffer();	//temp string buffer that holds everything in the document

			while((temp = reader1.readLine()) != null){
				sb1.append(temp);
				sb1.append("\n");
				j++;

				int firstComma = temp.indexOf(',');
				String firstSequence = temp.substring(0, firstComma);

				if(firstSequence.compareToIgnoreCase(getName()) == 0){
					k = j;
				}
			}
			reader1.close();
			//System.out.println("document contents:" + "\n" + sb1);
			//System.out.println("total lines in document: " + j);
			//System.out.println("line of desired name: " + k);
		} catch(IOException ex){
			ex.printStackTrace();
		}
		return k;
	}
	/**
	 * Gets the complete data of the line of IO
	 * @param k
	 * @return sb2String
	 * @throws IOException
	 */
	public String getLineData(int k) throws IOException{
		BufferedReader reader2 = new BufferedReader(
				new FileReader("characterList.txt"));
		StringBuffer sb2 = new StringBuffer();	//only has desired line info
		String input = null;
		for(int i = 1; i < k; i++){
			reader2.readLine();		//runs through the document, throwing away undesired lines
		}
		input = reader2.readLine();	//reads desired line
		sb2.append(input);
		//System.out.println(input);

		String sb2String = sb2.toString();
		//System.out.println(sb2String);
		return sb2String;
	}
	
	/**
	 * Gets the number of appearances of a specific character on a specific line
	 * @param desiredCharacter
	 * @param theLine
	 * @return numberOfAppearances
	 * @throws IOException
	 */
	public int getNumberOfAppearances(char desiredCharacter, int theLine) throws IOException{

		//CHUNK TWO: Reads the desired line and assigns values accordingly; Similar to readAttack from here on
		//warning: creates a new buffered reader to read the same document. Could be made more efficient with the reset method. Look into this for future efficiency
		String input = null;
		BufferedReader reader2 = new BufferedReader(
				new FileReader("characterList.txt"));
		StringBuffer sb2 = new StringBuffer();	//only has desired line info
		for(int i = 1; i < theLine; i++){
			reader2.readLine();		//runs through the document, throwing away undesired lines
		}
		input = reader2.readLine();	//reads desired line
		sb2.append(input);
		//System.out.println(input);

		String sb2String = sb2.toString();
		//System.out.println(sb2String);

		int numberOfAppearances = 0;
		//finds number of appearances of ","
		for(int i = 0; i < sb2String.length(); i++){
			if (sb2String.charAt(i) == desiredCharacter){
				numberOfAppearances++;
			}
		}

		return numberOfAppearances;
	}

	/**
	 * for character targetName, replaces the targetChunk with input 
	 * @param targetName
	 * @param targetChunk
	 * @param input
	 * @throws IOException
	 */
	public void replace(String targetName, int targetChunk, String input) throws IOException{
		String sb2String = getLineData(getLineNumber(targetName));

		File inFile = new File("characterList.txt");
		File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

		BufferedReader reader2 = new BufferedReader(new FileReader("characterList.txt"));
		PrintWriter writer1 = new PrintWriter(new FileWriter(tempFile));

		String line = null;

		//Read from the original file and write to the new 
		//unless content matches data to be removed.
		while ((line = reader2.readLine()) != null) {

			if (!line.trim().equals(sb2String)) {
				writer1.println(line);
				writer1.flush();
				//System.out.println("copied");
			}

			if(line.trim().equals(sb2String) && targetChunk == 1){	//name
				writer1.println(input + ", " + getAttack() + ", " + getDefense() + ", " + getSpeed() + ", " + getEvasiveness() + ", " + getHealth() + ", "
						+ attackList[0].getIONumber() + ", " + attackList[1].getIONumber() + ", " + attackList[2].getIONumber() + ", " + attackList[3].getIONumber() + ", "
						+ getExperience() + ", " + getExperienceBar() +  ", " + getLevel() + ", "
						+ itemLinkedList.get(0).getName() + ", " + itemLinkedList.get(1).getName() + ", " + itemLinkedList.get(2).getName() + ", " + itemLinkedList.get(3).getName() + ",");
				//System.out.println("setting name...");
				name = input;
			} else if(line.trim().equals(sb2String) && targetChunk == 2){	//attack
				writer1.println(getName() + ", " + Integer.parseInt(input) + ", " + getDefense() + ", " + getSpeed() + ", " + getEvasiveness() + ", " + getHealth() + ", "
						+ attackList[0].getIONumber() + ", " + attackList[1].getIONumber() + ", " + attackList[2].getIONumber() + ", " + attackList[3].getIONumber() + ", "
						+ getExperience() + ", " + getExperienceBar() +  ", " + getLevel() + ", "
						+ itemLinkedList.get(0).getName() + ", " + itemLinkedList.get(1).getName() + ", " + itemLinkedList.get(2).getName() + ", " + itemLinkedList.get(3).getName() + ",");
				//System.out.println("setting attack...");
				attackStat = Integer.parseInt(input);
			} else if(line.trim().equals(sb2String) && targetChunk == 3){	//defense
				writer1.println(getName() + ", " + getAttack() + ", " + Integer.parseInt(input) + ", " + getSpeed() + ", " + getEvasiveness() + ", " + getHealth() + ", "
						+ attackList[0].getIONumber() + ", " + attackList[1].getIONumber() + ", " + attackList[2].getIONumber() + ", " + attackList[3].getIONumber() + ", "
						+ getExperience() + ", " + getExperienceBar() +  ", " + getLevel() + ", "
						+ itemLinkedList.get(0).getName() + ", " + itemLinkedList.get(1).getName() + ", " + itemLinkedList.get(2).getName() + ", " + itemLinkedList.get(3).getName() + ",");
				//System.out.println("setting defense...");
				defenseStat = Integer.parseInt(input);
			} else if(line.trim().equals(sb2String) && targetChunk == 4){	//speed
				writer1.println(getName() + ", " + getAttack() + ", " + getDefense() + ", " + Integer.parseInt(input) + ", " + getEvasiveness() + ", " + getHealth() + ", "
						+ attackList[0].getIONumber() + ", " + attackList[1].getIONumber() + ", " + attackList[2].getIONumber() + ", " + attackList[3].getIONumber() + ", "
						+ getExperience() + ", " + getExperienceBar() +  ", " + getLevel() + ", "
						+ itemLinkedList.get(0).getName() + ", " + itemLinkedList.get(1).getName() + ", " + itemLinkedList.get(2).getName() + ", " + itemLinkedList.get(3).getName() + ",");
				//System.out.println("setting speed...");
				speedStat = Integer.parseInt(input);
			} else if(line.trim().equals(sb2String) && targetChunk == 5){	//evasiveness
				writer1.println(getName() + ", " + getAttack() + ", " + getDefense() + ", " + getSpeed() + ", " + Integer.parseInt(input) + ", " + getHealth() + ", "
						+ attackList[0].getIONumber() + ", " + attackList[1].getIONumber() + ", " + attackList[2].getIONumber() + ", " + attackList[3].getIONumber() + ", "
						+ getExperience() + ", " + getExperienceBar() +  ", " + getLevel() + ", "
						+ itemLinkedList.get(0).getName() + ", " + itemLinkedList.get(1).getName() + ", " + itemLinkedList.get(2).getName() + ", " + itemLinkedList.get(3).getName() + ",");
				//System.out.println("setting evasiveness...");
				evasivenessStat = Integer.parseInt(input);
			} else if(line.trim().equals(sb2String) && targetChunk == 6){	//health
				writer1.println(getName() + ", " + getAttack() + ", " + getDefense() + ", " + getSpeed() + ", " + getEvasiveness() + ", " + Integer.parseInt(input) + ", "
						+ attackList[0].getIONumber() + ", " + attackList[1].getIONumber() + ", " + attackList[2].getIONumber() + ", " + attackList[3].getIONumber() + ", "
						+ getExperience() + ", " + getExperienceBar() + ", " + getLevel() + ", "
						+ itemLinkedList.get(0).getName() + ", " + itemLinkedList.get(1).getName() + ", " + itemLinkedList.get(2).getName() + ", " + itemLinkedList.get(3).getName() + ",");
				//System.out.println("setting health...");
				healthStat = Integer.parseInt(input);
			} else if(line.trim().equals(sb2String) && targetChunk == 7){	//attack1
				writer1.println(getName() + ", " + getAttack() + ", " + getDefense() + ", " + getSpeed() + ", " + getEvasiveness() + ", " + getHealth() + ", "
						+ Integer.parseInt(input) + ", " + attackList[1].getIONumber() + ", " + attackList[2].getIONumber() + ", " + attackList[3].getIONumber() + ", "
						+ getExperience() + ", " + getExperienceBar() + ", " + getLevel() + ", "
						+ itemLinkedList.get(0).getName() + ", " + itemLinkedList.get(1).getName() + ", " + itemLinkedList.get(2).getName() + ", " + itemLinkedList.get(3).getName() + ",");
				//System.out.println("setting attack 1...");
				attackList[0] = new Attacks(Integer.parseInt(input));
			} else if(line.trim().equals(sb2String) && targetChunk == 8){	//attack2
				writer1.println(getName() + ", " + getAttack() + ", " + getDefense() + ", " + getSpeed() + ", " + getEvasiveness() + ", " + getHealth() + ", "
						+ attackList[0].getIONumber() + ", " + Integer.parseInt(input) + ", " + attackList[2].getIONumber() + ", " + attackList[3].getIONumber() + ", "
						+ getExperience() + ", " + getExperienceBar() + ", " + getLevel() + ", "
						+ itemLinkedList.get(0).getName() + ", " + itemLinkedList.get(1).getName() + ", " + itemLinkedList.get(2).getName() + ", " + itemLinkedList.get(3).getName() + ",");
				//System.out.println("setting attack 2...");
				attackList[1] = new Attacks(Integer.parseInt(input));
			} else if(line.trim().equals(sb2String) && targetChunk == 9){	//attack3
				writer1.println(getName() + ", " + getAttack() + ", " + getDefense() + ", " + getSpeed() + ", " + getEvasiveness() + ", " + getHealth() + ", "
						+ attackList[0].getIONumber() + ", " + attackList[1].getIONumber() + ", " + Integer.parseInt(input) + ", " + attackList[3].getIONumber() + ", "
						+ getExperience() + ", " + getExperienceBar() + ", " + getLevel() + ", "
						+ itemLinkedList.get(0).getName() + ", " + itemLinkedList.get(1).getName() + ", " + itemLinkedList.get(2).getName() + ", " + itemLinkedList.get(3).getName() + ",");
				//System.out.println("setting attack 3...");
				attackList[2] = new Attacks(Integer.parseInt(input));
			} else if(line.trim().equals(sb2String) && targetChunk == 10){	//attack4
				writer1.println(getName() + ", " + getAttack() + ", " + getDefense() + ", " + getSpeed() + ", " + getEvasiveness() + ", " + getHealth() + ", "
						+ attackList[0].getIONumber() + ", " + attackList[1].getIONumber() + ", " + attackList[2].getIONumber() + ", " + Integer.parseInt(input) + ", "
						+ getExperience() + ", " + getExperienceBar() + ", " + getLevel() + ", "
						+ itemLinkedList.get(0).getName() + ", " + itemLinkedList.get(1).getName() + ", " + itemLinkedList.get(2).getName() + ", " + itemLinkedList.get(3).getName() + ",");
				//System.out.println("setting attack 4...");
				attackList[3] = new Attacks(Integer.parseInt(input));
			} else if(line.trim().equals(sb2String) && targetChunk == 11){	//ExperienceStat
				writer1.println(getName() + ", " + getAttack() + ", " + getDefense() + ", " + getSpeed() + ", " + getEvasiveness() + ", " + getHealth() + ", "
						+ attackList[0].getIONumber() + ", " + attackList[1].getIONumber() + ", " + attackList[2].getIONumber() + ", " + attackList[3].getIONumber() + ", "
						+ Integer.parseInt(input) + ", " + getExperienceBar() + ", " + getLevel() + ", "
						+ itemLinkedList.get(0).getName() + ", " + itemLinkedList.get(1).getName() + ", " + itemLinkedList.get(2).getName() + ", " + itemLinkedList.get(3).getName() + ",");
				//System.out.println("setting experience stat...");
				experienceStat = Integer.parseInt(input);
			} else if(line.trim().equals(sb2String) && targetChunk == 12){	//ExperienceBar
				writer1.println(getName() + ", " + getAttack() + ", " + getDefense() + ", " + getSpeed() + ", " + getEvasiveness() + ", " + getHealth() + ", "
						+ attackList[0].getIONumber() + ", " + attackList[1].getIONumber() + ", " + attackList[2].getIONumber() + ", " + attackList[3].getIONumber() + ", "
						+ getExperience() + ", " + Integer.parseInt(input) + ", " + getLevel() + ", "
						+ itemLinkedList.get(0).getName() + ", " + itemLinkedList.get(1).getName() + ", " + itemLinkedList.get(2).getName() + ", " + itemLinkedList.get(3).getName() + ",");
				//System.out.println("setting experience bar...");
				experienceBar = Integer.parseInt(input);
			} else if(line.trim().equals(sb2String) && targetChunk == 13){	//level
				writer1.println(getName() + ", " + getAttack() + ", " + getDefense() + ", " + getSpeed() + ", " + getEvasiveness() + ", " + getHealth() + ", "
						+ attackList[0].getIONumber() + ", " + attackList[1].getIONumber() + ", " + attackList[2].getIONumber() + ", " + attackList[3].getIONumber() + ", "
						+ getExperience() + ", " + getExperienceBar() + ", " + Integer.parseInt(input) + ", "
						+ itemLinkedList.get(0).getName() + ", " + itemLinkedList.get(1).getName() + ", " + itemLinkedList.get(2).getName() + ", " + itemLinkedList.get(3).getName() + ",");
				//System.out.println("setting level...");
				level = Integer.parseInt(input);
			} else if(line.trim().equals(sb2String) && targetChunk == 14){	//item 1
				writer1.println(getName() + ", " + getAttack() + ", " + getDefense() + ", " + getSpeed() + ", " + getEvasiveness() + ", " + getHealth() + ", "
						+ attackList[0].getIONumber() + ", " + attackList[1].getIONumber() + ", " + attackList[2].getIONumber() + ", " + attackList[3].getIONumber() + ", "
						+ getExperience() + ", " + getExperienceBar() + ", " + getLevel() + ", "
						+ input + ", " + itemLinkedList.get(1).getName() + ", " + itemLinkedList.get(2).getName() + ", " + itemLinkedList.get(3).getName() + ",");
				//System.out.println("setting item 1");
				itemLinkedList.set(0,new Item(input));
			} else if(line.trim().equals(sb2String) && targetChunk == 15){	//item 2
				writer1.println(getName() + ", " + getAttack() + ", " + getDefense() + ", " + getSpeed() + ", " + getEvasiveness() + ", " + getHealth() + ", "
						+ attackList[0].getIONumber() + ", " + attackList[1].getIONumber() + ", " + attackList[2].getIONumber() + ", " + attackList[3].getIONumber() + ", "
						+ getExperience() + ", " + getExperienceBar() + ", " + getLevel() + ", "
						+ itemLinkedList.get(0).getName() + ", " + input + ", " + itemLinkedList.get(2).getName() + ", " + itemLinkedList.get(3).getName() + ",");
				//System.out.println("setting item 1");
				itemLinkedList.set(1,new Item(input));
			} else if(line.trim().equals(sb2String) && targetChunk == 16){	//item 3
				writer1.println(getName() + ", " + getAttack() + ", " + getDefense() + ", " + getSpeed() + ", " + getEvasiveness() + ", " + getHealth() + ", "
						+ attackList[0].getIONumber() + ", " + attackList[1].getIONumber() + ", " + attackList[2].getIONumber() + ", " + attackList[3].getIONumber() + ", "
						+ getExperience() + ", " + getExperienceBar() + ", " + getLevel() + ", "
						+ itemLinkedList.get(0).getName() + ", " + itemLinkedList.get(1).getName() + ", " + input + ", " + itemLinkedList.get(3).getName() + ",");
				//System.out.println("setting item 1");
				itemLinkedList.set(2,new Item(input));
			} else if(line.trim().equals(sb2String) && targetChunk == 17){	//item 4
				writer1.println(getName() + ", " + getAttack() + ", " + getDefense() + ", " + getSpeed() + ", " + getEvasiveness() + ", " + getHealth() + ", "
						+ attackList[0].getIONumber() + ", " + attackList[1].getIONumber() + ", " + attackList[2].getIONumber() + ", " + attackList[3].getIONumber() + ", "
						+ getExperience() + ", " + getExperienceBar() + ", " + getLevel() + ", "
						+ itemLinkedList.get(0).getName() + ", " + itemLinkedList.get(1).getName() + ", " + itemLinkedList.get(2).getName() + ", " + input + ",");
				//System.out.println("setting item 1");
				itemLinkedList.set(3,new Item(input));
			}
		}
		writer1.close();
		reader2.close();

		//Delete the original file
		if (!inFile.delete()) {
			System.out.println("Could not delete file");
		}
		//Rename the new file to the filename the original file had.
		if (!tempFile.renameTo(inFile))
			System.out.println("Could not rename file");	
	}

	/**
	 * Action listener for writeCharacter
	 * @author leerandy
	 *
	 */
	public class upAttackListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if(levelPoints > 0){
				variableAttackStat++;
				levelPoints--;
				attackSelect.setText(Integer.toString(variableAttackStat));
				pointsLeft.setText("Points Left" + levelPoints);
			}

		}	
	}
	/**
	 * Action listener for writeCharacter
	 * @author leerandy
	 *
	 */
	public class downAttackListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			if(levelPoints >= 0 && variableAttackStat > 0){
				variableAttackStat--;
				levelPoints++;
				attackSelect.setText(Integer.toString(variableAttackStat));
				pointsLeft.setText("Points Left" + levelPoints);
			}
		}	
	}
	/**
	 * Action listener for writeCharacter
	 * @author leerandy
	 *
	 */
	public class upDefenseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(levelPoints > 0){
				variableDefenseStat++;
				levelPoints--;
				defenseSelect.setText(Integer.toString(variableDefenseStat));
				pointsLeft.setText("Points Left" + levelPoints);
			}
		}

	}
	/**
	 * Action listener for writeCharacter
	 * @author leerandy
	 *
	 */
	public class downDefenseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(levelPoints >= 0 && variableDefenseStat > 0){
				variableDefenseStat--;
				levelPoints++;
				defenseSelect.setText(Integer.toString(variableDefenseStat));
				pointsLeft.setText("Points Left" + levelPoints);
			}
		}
	}
	/**
	 * Action listener for writeCharacter
	 * @author leerandy
	 *
	 */
	public class upSpeedListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(levelPoints > 0){
				variableSpeedStat++;
				levelPoints--;
				speedSelect.setText(Integer.toString(variableSpeedStat));
				pointsLeft.setText("Points Left" + levelPoints);
			}
		}
	}
	/**
	 * Action listener for writeCharacter
	 * @author leerandy
	 *
	 */
	public class downSpeedListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(levelPoints >= 0 && variableSpeedStat > 0){
				variableSpeedStat--;
				levelPoints++;
				speedSelect.setText(Integer.toString(variableSpeedStat));
				pointsLeft.setText("Points Left" + levelPoints);
			}
		}
	}
}




















