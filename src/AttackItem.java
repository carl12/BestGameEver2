/**
 * class that has an item with an attack
 * @author Carl-Admin
 *
 */
public class AttackItem extends Item{
   Attacks myAttack;
   public AttackItem(int speedChange, double defenseIn, int healthIn, Attacks attackIn) 
   {
      super(speedChange, defenseIn, healthIn);
      myAttack = attackIn;
   }

   public Attacks getAttack()
   {
      return myAttack;
   }



} 