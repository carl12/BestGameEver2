

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.swing.JOptionPane;


public class Attacks {

   public int strength;
   public double lifesteal;
   public int cooldown;
   public double criticalChance;
   public double criticalDamage;
   public String name;
   public int actionPointCost;
   public int IOLineNumber = -1;

   private int currentCooldown;



   public Attacks(int theStrength, String name){
      strength = theStrength;
      this.name = name;
      lifesteal=0;
      cooldown = 0;
      criticalDamage = 0;
      criticalChance = 0;
      currentCooldown = 0;
      actionPointCost = 1;
   }
   /** 
    * full constructor for attack
    * @param nameIn -name
    * @param strengthIn -base damage of attack
    * @param lifestealIn - decimal of damage healed
    * @param criticalChanceIn - decimal of critical chance (0-1)
    * @param criticalDamageIn - multiplier of critical damage
    * @param actionCostIn - action point cost
    * @param cooldownIn - cooldown
    */
   public Attacks(String nameIn, int strengthIn, double lifestealIn, double criticalChanceIn, 
         double criticalDamageIn,int actionCostIn,  int cooldownIn)
   {
      name = nameIn;
      strength = strengthIn;
      lifesteal = lifestealIn;
      criticalChance = criticalChanceIn;
      criticalDamage = criticalDamageIn;
      actionPointCost = actionCostIn;
      cooldown = cooldownIn;
      currentCooldown = 0;
   }

   public Attacks(int theLine){
      IOLineNumber = theLine;
      try {
         BufferedReader reader = new BufferedReader
               (new FileReader("attackList.txt"));

         String input = null;
         StringBuffer sb = new StringBuffer();

         //loops through the document to get to the desired line
         for(int i = 1; i < theLine; i++){
            reader.readLine();
         }
         input = reader.readLine();

         sb.append(input);
         sb.append("\n");

         //prints out all data on desired line. Does not affect actual stat manipulation
         String sbString = sb.toString();
         //System.out.println(sbString);


         int beginIndex = 0;
         int endIndex = sbString.indexOf(",");

         int numberOfAppearances = 0;

         //finds number of appearances of ","
         for(int i = 0; i < sbString.length(); i++){
            if (sbString.charAt(i) == ','){
               numberOfAppearances++;
            }
         }

         //prints out the number of appearances of "," and the length of the desired line. Not critical to actual data manipulation
         //System.out.println(numberOfAppearances + " appearances of ,");
         //System.out.println(sbString.length() + " is the length" + "\n");

         //"Meat" of the function. Manipulates attack data
         for(int i = 0; i < numberOfAppearances; i++){

            //set name and attack
            if(i == 1)
            {
               setName((String) sbString.subSequence(beginIndex, endIndex));
               //System.out.println("New name is " + getName());
            }
            else if(i == 2){
               setStrength(Integer.parseInt((String) sbString.subSequence(beginIndex, endIndex)));
               //System.out.println("New strength is " + getStrength());
            }  

            //resets the function to read for the next "chunk", as segregated by ","
            if(i != numberOfAppearances){
               beginIndex = endIndex + 2;
               endIndex = sbString.indexOf(",", endIndex + 1);
            } 
         }

      } catch (IOException e) {
         JOptionPane.showMessageDialog(null,
               "Invalid File \n"+e ,"File Error",
               JOptionPane.ERROR_MESSAGE);
      }
   }

   /**
    * This class asks the attack to use an attack
    * if it is off cooldown, it returns the damage 
    * (after defenses of enemy)
    * otherwise, it returns -1.
    * @return damage the damage dealt
    */
   public int useAttack(Character other)
   {
      int damage = -1;
      if(currentCooldown == 0)
      {
         currentCooldown = cooldown;
         if(Math.random() > criticalChance)
         {
            damage = (int) Math.round(strength * criticalDamage);
         }
         else
         {
            damage = strength;
         }
         return other.attack(damage);
      }
      else
      {
         return -1;
      }
   }
   /**
    * this method reduces the cooldown on the attack by one	
    */
   public void nextTurn()
   {
      currentCooldown --;
   }
   /**
    * this method does the attack ignoring cooldowns
    * @return damage
    */
   public int attackIgnoreCooldown()
   {
      int damage = 0;
      currentCooldown = cooldown;
      if(Math.random() > criticalChance)
      {
         damage = (int) Math.round(strength * criticalDamage);
      }
      else
      {
         damage = strength;
      }
      return damage;
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

   public void setName(String theName){
      name = theName;
   }

   public int getIONumber(){
      return IOLineNumber;
   }
   /**
    * Deals damage to other character
    * I think it is better to not have the attack do the damage, to have the damage done in the char class
    * 
    * @param user
    * @param other
    * @return
    */
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








