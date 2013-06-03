import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


public class TurnPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean onesTurn;
	ArrayList<Integer> sequence;
	final int AMOUNT_OF_TURNS_SHOWN = 8;
	public TurnPanel(ArrayList<Integer> turns)
	{
		sequence = turns;
		
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.black);
		g.drawRect(0, 0, 200, 20);
		Integer temp[] = new Integer[AMOUNT_OF_TURNS_SHOWN];
		temp = sequence.toArray(temp);
		
		for(int i = 0; i < AMOUNT_OF_TURNS_SHOWN; i++)
		{
			//NEEDS FIXING
			System.out.println(temp[i]);
			if(temp[i] == 1)
			{
				g.setColor(Color.RED);
			}
			else
			{
				g.setColor(Color.BLUE);
			}
			
			g.fillRect(0 + i * (200/AMOUNT_OF_TURNS_SHOWN) ,0, 200/AMOUNT_OF_TURNS_SHOWN, 20);
			g.setColor(Color.black);
			g.drawRect(0 + i * (200/AMOUNT_OF_TURNS_SHOWN) ,0, 200/AMOUNT_OF_TURNS_SHOWN, 20);
		}
	}
}
