package worldPackage;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Problems:
 * 
 * WIP: 
 * -Get character moving
 * -Clean code and do javadoc
 * -add to a single panel
 */

import javax.swing.JFrame;

public class GUIDriver extends JFrame{

	private static final long serialVersionUID = 1L;

	public final static int X_WINDOW_SIZE = 1100;
	public final static int Y_WINDOW_SIZE = 700;
	public final static int GRID_LENGTH = 10;
	public final static int MENU_WIDTH = 50;

	public final static boolean xGrid[] = new boolean[X_WINDOW_SIZE/GRID_LENGTH];
	public final static boolean yGrid[] = new boolean[Y_WINDOW_SIZE/GRID_LENGTH];	

	PlayerPiece player = new PlayerPiece();
	KeyListener theListener = new boardListener();

	public GUIDriver(){
		setSize(X_WINDOW_SIZE, Y_WINDOW_SIZE + MENU_WIDTH);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);

		for(int i = 0; i < xGrid.length; i++){
			xGrid[i] = false;
		}
		for(int i = 0; i < yGrid.length; i++){
			yGrid[i] = false;
		}

		addKeyListener(theListener);
		addCharacter(player);
	}

	public void paint (Graphics g){

		//draws horizontal grid lines in intervals of GRID_LENGTH
		for(int i = 0; i < yGrid.length; i++){
			g.drawLine(0, i * GRID_LENGTH + MENU_WIDTH, X_WINDOW_SIZE, i * GRID_LENGTH + MENU_WIDTH);
		}
		//draws vertical grid lines in intervals of GRID_LENGTH
		for(int i = 0; i < xGrid.length; i++){
			g.drawLine(i * GRID_LENGTH, 0 + MENU_WIDTH, i * GRID_LENGTH, Y_WINDOW_SIZE + MENU_WIDTH);
		}	
		
		g.fillRect(player.getXLocation() * GRID_LENGTH, player.getYLocation() * GRID_LENGTH + MENU_WIDTH, GRID_LENGTH, GRID_LENGTH);
		repaint();
	}

	public void addCharacter(PlayerPiece thePlayer){
		int xGridLocation = thePlayer.getXLocation();
		int yGridLocation = thePlayer.getYLocation();

		xGrid[xGridLocation] = true;
		yGrid[yGridLocation] = true;
	}

	public class boardListener implements KeyListener{

		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			switch(arg0.getKeyCode()){
			case KeyEvent.VK_UP : player.setYLocation(player.getYLocation() - 1 );
				break;
			case KeyEvent.VK_DOWN : player.setYLocation(player.getYLocation() + 1);
				break;
			case KeyEvent.VK_RIGHT : player.setXLocation(player.getXLocation() + 1);
				break;
			case KeyEvent.VK_LEFT : player.setXLocation(player.getXLocation() - 1);
				break;
			}
		}

		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			

		}

		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

}
