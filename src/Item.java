import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Describes an item to be used by Character()
 * IO Format: NAME, DEFENSE_MODIFIER, HEALTH_MODIFIER, SPEED_MODIFIER,
 * @author Andrew Tang, Blade Chapman, Carl Rodriguez
 * 
 * 
 * WIP: Does not implement duration, modifier, and description variables
 */

public class Item {
	
	private int duration;
	private int modifier;
	private String status;

	private String name;
	private int speedModifier;
	private int defenseModifier;
	private int otherStatsModifier;

	private int healthModifier;

	/**
	 * Constructs an item with given stats
	 * @param speedChange
	 * @param defenseIn
	 * @param healthIn
	 */
	public Item(int speedChange, int defenseIn, int healthIn)
	{
		defenseModifier = defenseIn;
		healthModifier = healthIn;
		speedModifier = speedChange;
	}
	
	/**
	 * Constructs an Item with given stats
	 * ??? What do these variables do?
	 * @param aDuration
	 * @param aModifier
	 * @param description
	 */
	public Item(int aDuration, int aModifier, String description)
	{
		duration = aDuration;
		modifier = aModifier;
		status = description;
	}
	
	/**
	 * Constructs an Item using the stats defined by IO
	 * @param theName
	 * @throws IOException
	 */
	public Item(String theName) throws IOException{
		BufferedReader reader1 = new BufferedReader(new FileReader("itemList.txt"));
		
		String emptySet = null;
		StringBuffer sb1 = new StringBuffer();
		int theLine = getLine(theName);
		
		//System.out.println("Data is on line " + theLine);
		
		for(int i = 1; i < theLine; i++){
			reader1.readLine();
		}
		emptySet = reader1.readLine();
		sb1.append(emptySet);
		sb1.append("\n");
		
		int numberOfAppearances = 0;
		for(int i = 0; i < sb1.length(); i++){
			if(sb1.charAt(i) == ','){
				numberOfAppearances++;
			}
		}

		//System.out.println("Data is " + sb1);
		
		int beginIndex = 0;
		int endIndex = sb1.indexOf(",");
		
		for(int i = 0; i < numberOfAppearances; i++){
			//put stat edits here;
			if(i == 0){
				name = ((String) sb1.subSequence(beginIndex, endIndex));
				//.println("set " + i);
			} else if(i == 1){
				defenseModifier = (Integer.parseInt((String) sb1.subSequence(beginIndex, endIndex)));
				//System.out.println("set " + i);
			} else if(i == 2){
				healthModifier = (Integer.parseInt((String) sb1.subSequence(beginIndex, endIndex)));
				//System.out.println("set " + i);
			} else if(i == 3){
				speedModifier = (Integer.parseInt((String) sb1.subSequence(beginIndex, endIndex)));
				//System.out.println("set " + i);
			}
			
			if(i != numberOfAppearances){
				beginIndex = endIndex + 2;
				endIndex = sb1.indexOf(",", endIndex + 1);
			}
		}
	}
	
	/**
	 * Gets the name of the item
	 * @return name
	 */
	public String getName(){
		return name;
	}
	/**
	 * Sets the name of the item and returns the new name
	 * @param input
	 * @return name
	 */
	public String setName(String input){
		name = input;
		return name;
	}
	
	/**
	 * Gets the speed of the item
	 * @return speedModifier
	 */
	public int getSpeed()
	{
		return speedModifier;
	}
	/**
	 * Sets the speed of the item and returns the new speed
	 * @param input
	 * @return speedModifier
	 */
	public int setSpeed(int input){
		speedModifier = input;
		return speedModifier;
	}
	
	/**
	 * Gets the defense of the item
	 * @return defenseModifier
	 */
	public int getDefense()
	{
		return defenseModifier;
	}
	/**
	 * Sets the defense of the item and returns the new defense
	 * @param input
	 * @return defenseModifier
	 */
	public int setDefense(int input){
		defenseModifier = input;
		return defenseModifier;
	}
	
	/**
	 * Gets the health boost of the item 
	 * @return healthModifier
	 */
	public int getHealth()
	{
		return healthModifier;
	}
	/**
	 * Sets the health boost of the item and returns the new health boost
	 * @param input
	 * @return
	 */
	public int setHealth(int input){
		healthModifier = input;
		return healthModifier;
	}

	/**
	 * Gets the duration of the item
	 * ???
	 * @return duration
	 */
	public int getDuration()
	{
		return duration;
	}
	/**
	 * Gets the modifier of the item
	 * ???
	 * @return modifier
	 */
	public int getModifier()
	{
		return modifier;
	}
	/**
	 * Gets the description of the item
	 * ???
	 * @return status
	 */
	public String getDescription()
	{
		return status;
	}
	
	/**
	 * Returns the line on which the desired item is located in the IO
	 * @param theName
	 * @return theLine
	 */
	public int getLine(String theName){
		
		int theLine = 0;
		
		try{
			BufferedReader reader1 = new BufferedReader(new FileReader("itemList.txt"));
			
			String temp = null;
			StringBuffer sb1 = new StringBuffer();
			int counter = 0;
			
			
			while((temp = reader1.readLine()) != null){
				sb1.append(temp);
				sb1.append('\n');
				counter++;
				
				int firstComma = temp.indexOf(',');
				String firstSequence = temp.substring(0, firstComma);
				if(firstSequence.compareToIgnoreCase(theName) == 0){
					theLine = counter;
				}
			}
			reader1.close();
			
		} catch (IOException e){
			System.out.println("Problem @ getLine");
		}
		return theLine;
	}
	
	/**
	 * Applies the item's stat changes to the specified character
	 * @param theCharacter
	 */
	public void use(Character theCharacter){
		theCharacter.setSpeed(theCharacter.getSpeed() + speedModifier);
		theCharacter.setDefense(theCharacter.getDefense() + defenseModifier);
		theCharacter.setHealth(theCharacter.getHealth() + healthModifier);
		//System.out.println("stats modified");
	}
}


