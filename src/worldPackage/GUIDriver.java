package worldPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Problems:
 * 
 * WIP: 
 * -Clean code and do javadoc
 * -add to a single panel
 */

import javax.swing.JFrame;

import worldPackage.enviroPanel;

public class GUIDriver extends JFrame{

	private static final long serialVersionUID = 1L;

	public final static int X_WINDOW_SIZE = 1100;
	public final static int Y_WINDOW_SIZE = 700;
	public final static int GRID_LENGTH = 10;
	public final static int MENU_WIDTH = 50;

	/*
	public final static String xGrid[] = new String[X_WINDOW_SIZE/GRID_LENGTH];
	public final static String yGrid[] = new String[Y_WINDOW_SIZE/GRID_LENGTH];	
	 */

	public static enviroPanel initPanel = new enviroPanel();

	Piece player = new PlayerPiece(4, 4);
	Piece switcher = new panelSwitchPiece(10, 10);
	KeyListener theListener = new boardListener();

	public GUIDriver(){
		setSize(X_WINDOW_SIZE, Y_WINDOW_SIZE + MENU_WIDTH);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		addKeyListener(theListener);
		addCharacter(player);
		//switcherCollisionCheck(player, switcher);

	}

	public void paint (Graphics g){

		g.clearRect(0, 0, X_WINDOW_SIZE, Y_WINDOW_SIZE + MENU_WIDTH);
		g.setColor(Color.black);
		
		//draws horizontal grid lines in intervals of GRID_LENGTH
		for(int i = 0; i < initPanel.getYLength(); i++){
			g.drawLine(0, i * GRID_LENGTH + MENU_WIDTH, X_WINDOW_SIZE, i * GRID_LENGTH + MENU_WIDTH);
		}
		//draws vertical grid lines in intervals of GRID_LENGTH
		for(int i = 0; i < initPanel.getXLength(); i++){
			g.drawLine(i * GRID_LENGTH, 0 + MENU_WIDTH, i * GRID_LENGTH, Y_WINDOW_SIZE + MENU_WIDTH);
		}	

		//draws player coordinates
		for(int i = 0; i < initPanel.getXLength(); i++){
			if(player.getXValidation(i)){
				System.out.println("X Coordinate: " + i);
			}
		}
		for(int i = 0; i < initPanel.getYLength(); i++){
			if(player.getYValidation(i)){
				System.out.println("Y Coordinate: " + i);
			}
		}
		
		//draws player
		g.fillRect(player.getXCoord() * GRID_LENGTH, player.getYCoord() * GRID_LENGTH + MENU_WIDTH, GRID_LENGTH, GRID_LENGTH);

		//draws switcher
		g.setColor(Color.red);
		g.fillRect(switcher.getXCoord() * GRID_LENGTH, switcher.getYCoord() * GRID_LENGTH + MENU_WIDTH, GRID_LENGTH, GRID_LENGTH);
		
		checkCollision(player, switcher);
		
		repaint();
	}

	public void addCharacter(Piece thePlayer){
		int xGridLocation = thePlayer.getXCoord();
		int yGridLocation = thePlayer.getYCoord();

		initPanel.setXValue(xGridLocation, "p");
		initPanel.setYValue(yGridLocation, "p");
	}

	public boolean checkCollision(Piece thePlayer, Piece theSwitch){
		boolean collision = false;
		
		if(thePlayer.getXCoord() == theSwitch.getXCoord() && thePlayer.getYCoord() == theSwitch.getYCoord()){
			System.out.println("SWITCHSWITCHSWITCHSWITCHSWITCHSWITCHSWITCHSWITCHSWITCHSWITCH");
			collision = true;
		}
		
		
		return collision;
	}
	
	public class boardListener implements KeyListener{

		public void keyPressed(KeyEvent arg0) {
			switch(arg0.getKeyCode()){
			case KeyEvent.VK_UP : 
				if(player.getYCoord() != 0){
					player.setYCoord(player.getYCoord() - 1);
				}
				break;
			case KeyEvent.VK_DOWN : 
				if(player.getYCoord() != 69){
					player.setYCoord(player.getYCoord() + 1);
				}
				break;
			case KeyEvent.VK_RIGHT : 
				if(player.getXCoord() != 109){
					player.setXCoord(player.getXCoord() + 1);
				}
				break;
			case KeyEvent.VK_LEFT : 
				if(player.getXCoord() != 0){
					player.setXCoord(player.getXCoord() - 1);
				}
				break;
			}
		}

		public void keyReleased(KeyEvent arg0) {


		}

		public void keyTyped(KeyEvent arg0) {

		}

	}

}
