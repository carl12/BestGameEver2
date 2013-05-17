
public class Item {

	private int duration;
	private int modifier;
	private String status;

	private int speedModifier;
	private int defenseModifier;
	private int otherStatsModifier;

	private int healthModifier;

	public Item(int speedChange, int defenseIn, int healthIn)
	{
		defenseModifier = defenseIn;
		healthModifier = healthIn;
		speedModifier = speedChange;
	}
	
	public Item(int aDuration, int aModifier, String description)
	{
		duration = aDuration;
		modifier = aModifier;
		status = description;
	}
	
	public int getSpeed()
	{
		return speedModifier;
	}
	
	public int getDefense()
	{
		return defenseModifier;
	}
	
	public int getHealth()
	{
		return healthModifier;
	}

	public int getDuration()
	{
		return duration;
	}

	public int getModifier()
	{
		return modifier;
	}

	public String getDescription()
	{
		return status;
	}
	
	public void statChanger(Character theCharacter){
		theCharacter.setSpeed(theCharacter.getSpeed() + speedModifier);
		theCharacter.setDefense(theCharacter.getDefense() + defenseModifier);
		theCharacter.setHealth(theCharacter.getHealth() + healthModifier);
		System.out.println("stats modified");
	}
}


