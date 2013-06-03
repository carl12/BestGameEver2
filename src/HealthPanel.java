import java.awt.*;
import javax.swing.*;


public class HealthPanel extends JPanel {
	Character fighter;
	JLabel nameLabel;
	public HealthPanel(Character fighter)
	{
		setMinimumSize(new Dimension(100,100));
		this.fighter = fighter;
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.black);
		g.drawString(fighter.getName() + "'s Health:", 10 ,10);
		g.drawString("EXP:", 10, 25);
		g.drawString("Lvl: " + fighter.level, 10, 0);
		
		
		if(fighter.getHealth() > 0)
		{
			g.drawRect(fighter.getName().length()  + 109, 0, fighter.permaHealthStat * 4, 10);
			g.setColor(Color.red);
			g.fillRect(fighter.getName().length()   + 109, 0, fighter.getHealth() * 4, 10);
		}
			g.setColor(Color.black);
			g.drawRect(fighter.getName().length() + 109, 15, fighter.experienceBar * 4 , 10);
			g.setColor(Color.YELLOW);
			g.fillRect(fighter.getName().length() + 109, 15, fighter.experienceStat * 4, 10);
		System.out.println("label height " + this.getHeight());
		System.out.println("label width " + this.getWidth());
	}
}
