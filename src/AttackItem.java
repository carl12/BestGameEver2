/**
 * class that has an item with an attack
 * 
 *
 */
public class AttackItem extends Item{
   
	Attacks myAttack;
   
   public AttackItem(int speedChange, int defenseIn, int healthIn, Attacks attackIn) 
   {
      super(speedChange, defenseIn, healthIn);
      myAttack = attackIn;
   }

   public Attacks getAttack()
   {
      return myAttack;
   }
   /**
    * passes next turn info down to attack for cooldown 
    */
   public void nextTurn()
   {
      myAttack.nextTurn();
   }
} 