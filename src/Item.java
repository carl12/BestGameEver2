
public class Item {

    private int duration;
    private int modifier;
    private String status;
    
    
    public boolean hasAttack;
    private double attackDamage;
    private int cooldown;
    private int lifesteal;
    private double criticalDamage;
    
   

    public Item()

    {

        duration = 0;

        modifier = 0;

        status = " ";

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


