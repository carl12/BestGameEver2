import java.util.Random;


public class Attacks {

    private int strength;

    private String name;


    public Attacks(int theStrength, String name){

        strength = theStrength;

        this.name = name;

    }

    public int getStrength(){

        return strength;

    }

    public int setStrength(int theStrength){

        strength = theStrength;

        return strength;

    }

    public String getName(){

        return name;

    }

    public boolean doDamage(Character user, Character other)

    {

        boolean crit = false;

        int criticalHit = 0;

        if(new Random().nextInt(5) > 3)

        {

            criticalHit = user.getAttack();

            crit = true;

        }

        int damage = (user.getAttack() * strength + criticalHit) / other.getDefense();

        System.out.println("Damage done: " + damage);

        other.setHealth(other.getHealth() - damage);

        return crit;

    }   

}






