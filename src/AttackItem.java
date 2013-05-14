/**
 * class that has an item with an attack
 * @author Carl-Admin
 *
 */
public class AttackItem extends Item{
   Attacks myItem;
   public AttackItem(int speedChange, double defenseIn, int healthIn, Attacks attackIn) 
   {
      super(speedChange, defenseIn, healthIn);
      myItem = attackIn;
   }

   public Attacks getAttack()
   {
      return myItem;
   }



} 