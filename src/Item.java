
public class Item {

    private int duration;
    private int modifier;
    private String status;
    
    private int speedModifier;
    private double defense;
    private double otherstats;
    
    private int health;
    
   

    public Item(int speedChange, double defenseIn, int healthIn)
    {
       defense = defenseIn;
       health = healthIn;
       speedModifier = speedChange;
       
       
    }
    public int getSpeed()
    {
      return speedModifier;
       
    }
    public double getDefense()
    {
       return defense;
    }
    public int getHealth()
    {
       return health;
    }
    public Item(int aDuration, int aModifier, String description)

    {

        duration = aDuration;

        modifier = aModifier;

        status = description;

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

}


