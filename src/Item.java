
public class Item {

    private int duration;

    private int modifier;

    private String status;

   

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


