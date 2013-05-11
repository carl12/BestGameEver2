import java.awt.*;
import java.awt.GridLayout;

import javax.swing.*;


public class GridPanel extends JPanel {
	Character fighter;
	boolean hasFighter = false;
	public GridPanel()
	{
		hasFighter = false;
	}
	public GridPanel(Character someone)
	{
		fighter = someone;
		hasFighter = true;
	}
	public void paintComponent(Graphics g)
	{		
		super.paintComponent(g);

		if(hasFighter)
		{
			fighter.create(g);
			System.out.println("fighter y: " + fighter.y);
			System.out.println("fighter x: " + fighter.x);
			System.out.println(fighter.getName() + " was created!");
		}
	}
}
