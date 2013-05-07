
public class AttackItem extends Item{
   Attacks attackItem;
   public AttackItem(int speedChange, double defenseIn, int healthIn, Attacks attackIn) 
   {
      super(speedChange, defenseIn, healthIn);
      attackItem = attackIn;
   }



}
