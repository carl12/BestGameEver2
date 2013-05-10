/**
 * This class depicts the statistics of a character
 * All character data is written and read off of characterList.txt
 * characterList.txt is formatted as such: CHARACTER_NAME, ATTACK_STAT, DEFENSE_STAT, SPEED_STAT, EVASIVENESS_STAT,
 * Note: formatting is IMPORTANT! Follow it exactly when editing
 */

/**
 * BUG NOTES:
 * lp: add catches for things such as cancel under writeCharacter, etc.  
 * hp: add/removeAttack is not accounted for
 * hp: level changes/saves not accounted for
 */

/**
 * WIP:
 * addAttack(Attacks) method: adding IO functionality. Can be integrated without IO for now
 * removeAttack JOptionPane dialogue
 */


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
import java.util.Random;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
public class Character {
	Color color;
	int x;
	int y;
	private String name;
	private int attackStat;
	private int defenseStat;
	private int speedStat;
	private int evasivenessStat; // will implement random number generator for probability of hit
	private int permaHealthStat; // max health a character has
	private int healthStat;
	private int manaStat;
	private int manaBar;
	private int experienceStat;
	private int experienceBar;
	private int level;
	Attacks attackList[] = new Attacks[4];

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

	//sets the stats of a pre-written character
	public Character(String characterName) throws IOException{
		//stats not written by writeCharacter listed here
		permaHealthStat = 25;
		healthStat = permaHealthStat;

		//sets all stats, Character is already 
		//FIX: SHOULD NOT BE USED TO SET ALL STATS
		setName(getIOName(characterName));
		setAttack(getIOAttack(characterName));
		setDefense(getIODefense(characterName));
		setSpeed(getIOSpeed(characterName));
	}

	//Creates a new character with stats specified by user
	public Character() throws IOException{
		permaHealthStat = 25;
		healthStat = permaHealthStat;
		experienceStat = 0;
		level = 1;
		experienceBar = 10;
		evasivenessStat = tempEvasiveness;   
		
		writeCharacter();
		//System.out.println("default created");
	}
	public Character(int anX, int anY, Color color) throws IOException{
		//PrintWriter outputStream = new PrintWriter(new FileWriter("characterList.txt", true));
		//outputStream.println(tempName + ", " + tempAttack + ", " + tempDefense + ", " + tempSpeed + ", " + evasivenessStat + ", " + healthStat + "|");
		//outputStream.close();
		permaHealthStat = 25;
		healthStat = permaHealthStat;
		experienceStat = 0;
		level = 1;
		experienceBar = 10;
		this.color = color;
		x = anX;
		y = anY;
		//evasivenessStat = tempEvasiveness;    not used yet
		
		writeCharacter();
		System.out.println("default created");
	}

	public String getName(){
		return name;
	}
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
	//use if pre-written, sets system name
	public String setName(String input){
		name = input;
		return name;
	}
	//use if changing IO, also changes system name
	public String setIOName(String characterName, String input){
		try{
			replace(characterName, 1, input);
		} catch (IOException e){
		}

		name = input;
		return name;
	}

	public int getAttack(){
		return attackStat;
	}
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
	public int setAttack(int input){
		attackStat = input;
		return attackStat;
	}
	public int setIOAttack(int input){

		try{
			replace(getName(), 2, Integer.toString(input));
		} catch (IOException e){
		}

		attackStat = input;
		return attackStat;
	}

	public int getDefense(){
		return defenseStat;
	}
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
	public int setDefense(int input){
		defenseStat = input;
		return defenseStat;
	}
	public int setIODefense(int input){
		try{
			replace(getName(), 3, Integer.toString(input));
		} catch (IOException e){
		}

		attackStat = input;
		return attackStat;
	}

	public int getSpeed(){
		return speedStat;
	}
	public int getIOSpeed(String characterName) throws IOException{
		String sb2String = getLineData(getLineNumber(characterName));
		int numberOfAppearances = getNumberOfAppearances(',', getLineNumber(characterName));
		int returnSpeed = -1;

		int beginIndex = 0;
		int endIndex = sb2String.indexOf(',');

		for(int i = 0; i < numberOfAppearances; i++){
			if(i == 2){
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
	public int setSpeed(int input){
		speedStat = input;
		return speedStat;
	}
	public int setIOSpeed(int input){
		try{
			replace(getName(), 4, Integer.toString(input));
		} catch (IOException e){
		}

		defenseStat = input;
		return defenseStat;
	}



	public int getEvasiveness(){
		return evasivenessStat;
	}
	public int getIOEvasiveness(String characterName) throws IOException{
		String sb2String = getLineData(getLineNumber(characterName));
		int numberOfAppearances = getNumberOfAppearances(',', getLineNumber(characterName));
		int returnEvasiveness = -1;

		int beginIndex = 0;
		int endIndex = sb2String.indexOf(',');

		for(int i = 0; i < numberOfAppearances; i++){
			if(i == 3){
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
	public int setEvasiveness(int input){
		evasivenessStat = input;
		return evasivenessStat;
	}
	public int setIOEvasiveness(int input){
		try{
			replace(getName(), 5, Integer.toString(input));
		} catch (IOException e){
		}

		evasivenessStat = input;
		return evasivenessStat;
	}
	
	public int getHealth(){
		return healthStat;
	}
	public int setHealth(int input){
		healthStat = input;
		return healthStat;
	}

	public int getExperience(){
		return experienceStat;
	}
	public void setExperience(int input){
		experienceStat = input;

	}

	public int getLevel()
	{
		return level;
	}
	public void setLevel(int aLevel)
	{
		level = aLevel;
	}

	public int getExperienceBar()
	{
		return experienceBar;
	}
	public void setExperienceBar(int bar)
	{
		experienceBar = bar;
	}

	public void fullHeal()
	{
		healthStat = permaHealthStat;
	}


	public void addAttack(Attacks anAttack){ // automatically adds given move to lowest slot

		boolean added = false;
		boolean moveExisted = false;
		for(int i = 0; i < 4; i++){
			if(attackList[i] != null){
				if(attackList[i].getName().compareToIgnoreCase(anAttack.getName()) == 0 )
				{
					moveExisted = true;
				}
			}
		}
		for(int i = 0; i < 4; i++)
		{
			if(attackList[i] == null
					&& !added
					&& !moveExisted)
			{
				attackList[i] = anAttack;
				added = true;
				//System.out.println(i);
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
		/**
		 * 
		//new stuff starts here
		try{
			BufferedReader reader1 = new BufferedReader(new FileReader ("characterList.txt"));
			String theLine = null;
			String sbTemp = null;
			StringBuffer sb1 = new StringBuffer();

			while((sbTemp = reader1.readLine()) != null){
				sb1.append(sbTemp);
				sb1.append("\n");

				//int firstBar = sbTemp.indexOf('|');
				//String firstSequence = sbTemp.substring(firstBar + 2, sbTemp.indexOf("|", firstBar + 1));
				int firstComma = sbTemp.indexOf(',');
				String nameSequence = sbTemp.substring(0, firstComma);
				//System.out.println(nameSequence);
				if(nameSequence.compareToIgnoreCase(getName()) == 0){
					theLine = sbTemp;
					//System.out.println("desired line is: " + theLine);
				}
			}

			int numberBarAppearances = 0;
			for(int i = 0; i < theLine.length(); i++){
				if(theLine.charAt(i) == '|'){
					numberBarAppearances++;
				}
			}

			System.out.println(numberBarAppearances);


			File inFile = new File("characterList.txt");
			File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

			BufferedReader reader2 = new BufferedReader(new FileReader("characterList.txt"));
			PrintWriter writer1 = new PrintWriter(new FileWriter(tempFile));

			String line = null;

			//Read from the original file and write to the new 
			//unless content matches data to be removed.
			while ((line = reader2.readLine()) != null) {
				if (!line.trim().equals(theLine)) {
					writer1.println(line);
					writer1.flush();
					//System.out.println("copied " + line);
				} else if(line.trim().equals(theLine)){
					int theSlot = 0;
					int varSequenceStart = theLine.indexOf('|');
					int varSequenceEnd = theLine.indexOf('|', varSequenceStart);

					for(int i = 0; i < numberBarAppearances; i++){

						System.out.println(theLine.substring(varSequenceStart, varSequenceEnd));
						varSequenceStart = varSequenceEnd + 2;
						varSequenceEnd = theLine.indexOf('|', varSequenceStart + 1);


					}
					writer1.println(getName() + ", " + getAttack() + ", " + getDefense() + ", " + getSpeed() + ", " + getEvasiveness() + ", " + getHealth() + "|");
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


		} catch(IOException ex){

		}
		 **/
	}
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

	public void removeAttack(){

		String theAttack = null;

		System.out.println("Which move would you like to remove?");
		for(int i = 0; i < 4; i++){
			if(attackList[i] != null){
				System.out.println(attackList[i].getName());
			}
		}

		JOptionPane removePane = new JOptionPane();
		JPanel removePanel = new JPanel();

		JTextArea whichAttack = new JTextArea();
		removePanel.add(whichAttack);

		int result = removePane.showConfirmDialog(null, removePanel, 
				"Delete a Move", JOptionPane.OK_CANCEL_OPTION);	

		if(result == JOptionPane.OK_OPTION){
			theAttack = whichAttack.getText();

			for(int i = 0; i < 4; i++){
				if(attackList[i].getName().compareToIgnoreCase(theAttack) == 0){
					attackList[i] = null;
					i = 5;
				} else{
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
		}
		/**
		for(int i = 0; i < 4; i++){
			if(attackList[i].getName().compareToIgnoreCase(input) == 0){
				attackList[i] = null;
				i = 5;
			} else{
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

		}**/
	}
	public void removeAttack(Attacks replaceAttack){

		System.out.println("Which move would you like to remove?");
		for(int i = 0; i < 4; i++){
			if(attackList[i] != null){
				System.out.println(attackList[i].getName());
			}
		}

		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine().toLowerCase();	

		for(int i = 0; i < 4; i++){
			if(attackList[i].getName().compareToIgnoreCase(input) == 0){
				attackList[i] = replaceAttack;
				i = 5;
			} else{
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
	}

	public String levelUp()
	{
		attackStat = attackStat + 3;
		defenseStat = defenseStat + 3;
		speedStat = speedStat + 3;
		permaHealthStat = permaHealthStat + 3;
		evasivenessStat = evasivenessStat + 3;
		experienceStat = 0;
		experienceBar = experienceBar + 5;
		level = level + 1;
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
	public void create(Graphics g)
	{
		g.setColor(color);
		g.drawOval(25, 2, 50, 50);
		g.fillOval(25, 2, 50, 50);
	
	}

	//reads from characterList, does not set any stats
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

	//opens dialogue to create character with name, attack, defense, speed

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
			System.out.println("Success!");
			tempName = nameArea.getText();
			tempAttack = Integer.parseInt(attackSelect.getText());
			tempDefense = Integer.parseInt(defenseSelect.getText());
			tempSpeed = Integer.parseInt(speedSelect.getText());
			try{
				outputStream.println(tempName + ", " + tempAttack + ", " + tempDefense + ", " + tempSpeed + ", " + evasivenessStat + ", " + healthStat + ",");
				setName(tempName);
				setAttack(tempAttack);
				setDefense(tempDefense);
				setSpeed(tempSpeed);
			}
			finally{
				if(outputStream != null){
					outputStream.close();
				}
			}		
		}
	}

	//removes the entire line of the character
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

	//returns the line number of a desired character on characterList.txt

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
				System.out.println("copied");
			}

			if(line.trim().equals(sb2String) && targetChunk == 1){
				writer1.println(input + ", " + getAttack() + ", " + getDefense() + ", " + getSpeed() + "," + getEvasiveness() + ", " + getHealth() + ",");
				System.out.println("setting name...");
				System.out.println("new data: " + input + ", " + getAttack() + ", " + getDefense() + ", " + getSpeed() + ", " + getEvasiveness() + ", " + getHealth() + ",");
				name = input;
			} else if(line.trim().equals(sb2String) && targetChunk == 2){
				writer1.println(getName() + ", " + Integer.parseInt(input) + ", " + getDefense() + ", " + getSpeed() + ", " + getEvasiveness() + ", " + getHealth() + ",");
				System.out.println("setting attack...");
				System.out.println("new data: " + getName() + ", " + Integer.parseInt(input) + ", " + getDefense() + ", " + getSpeed() + "," + getEvasiveness() + ", " + getHealth() + ",");
				attackStat = Integer.parseInt(input);
			} else if(line.trim().equals(sb2String) && targetChunk == 3){
				writer1.println(getName() + ", " + getAttack() + ", " + Integer.parseInt(input) + ", " + getSpeed() + ", " + getEvasiveness() + ", " + getHealth() + ",");
				System.out.println("setting defense...");
				System.out.println("new data: " + getName() + ", " + getAttack() + ", " + Integer.parseInt(input) + ", " + getSpeed() + "," + getEvasiveness() + ", " + getHealth() + ",");
				defenseStat = Integer.parseInt(input);
			} else if(line.trim().equals(sb2String) && targetChunk == 4){
				writer1.println(getName() + ", " + getAttack() + ", " + getDefense() + ", " + Integer.parseInt(input) + ", " + getEvasiveness() + ", " + getHealth() + ",");
				System.out.println("setting speed...");
				System.out.println("new data: " + getName() + ", " + getAttack() + ", " + getDefense() + ", " + Integer.parseInt(input) + ", " + getEvasiveness() + ", " + getHealth() + ",");
				speedStat = Integer.parseInt(input);
			} else if(line.trim().equals(sb2String) && targetChunk == 5){
				writer1.println(getName() + ", " + getAttack() + ", " + getDefense() + ", " + getSpeed() + ", " + Integer.parseInt(input) + ", " + getHealth() + ",");
				System.out.println("setting speed...");
				System.out.println("new data: " + getName() + ", " + getAttack() + ", " + getDefense() + ", " + getSpeed() + ", " + Integer.parseInt(input) + ", " + getHealth() + ",");
				speedStat = Integer.parseInt(input);
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


	public class upAttackListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("clicked");
			if(levelPoints > 0){
				variableAttackStat++;
				levelPoints--;
				attackSelect.setText(Integer.toString(variableAttackStat));
				pointsLeft.setText("Points Left" + levelPoints);
			}

		}	
	}
	public class downAttackListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			System.out.println("clicked");
			if(levelPoints >= 0 && variableAttackStat > 0){
				variableAttackStat--;
				levelPoints++;
				attackSelect.setText(Integer.toString(variableAttackStat));
				pointsLeft.setText("Points Left" + levelPoints);
			}
		}	
	}
	public class upDefenseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("clicked");
			if(levelPoints > 0){
				variableDefenseStat++;
				levelPoints--;
				defenseSelect.setText(Integer.toString(variableDefenseStat));
				pointsLeft.setText("Points Left" + levelPoints);
			}
		}

	}
	public class downDefenseListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("clicked");
			if(levelPoints >= 0 && variableDefenseStat > 0){
				variableDefenseStat--;
				levelPoints++;
				defenseSelect.setText(Integer.toString(variableDefenseStat));
				pointsLeft.setText("Points Left" + levelPoints);
			}
		}
	}
	public class upSpeedListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("clicked");
			if(levelPoints > 0){
				variableSpeedStat++;
				levelPoints--;
				speedSelect.setText(Integer.toString(variableSpeedStat));
				pointsLeft.setText("Points Left" + levelPoints);
			}
		}
	}
	public class downSpeedListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.out.println("clicked");
			if(levelPoints >= 0 && variableSpeedStat > 0){
				variableSpeedStat--;
				levelPoints++;
				speedSelect.setText(Integer.toString(variableSpeedStat));
				pointsLeft.setText("Points Left" + levelPoints);
			}
		}
	}
}


















