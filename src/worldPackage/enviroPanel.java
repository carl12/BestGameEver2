package worldPackage;


public class enviroPanel {

	static String xCoord[] = new String[GUIDriver.X_WINDOW_SIZE/GUIDriver.GRID_LENGTH];
	static String yCoord[] = new String[GUIDriver.Y_WINDOW_SIZE/GUIDriver.GRID_LENGTH];

	public enviroPanel(){
		for(int i = 0; i < xCoord.length; i++){
			xCoord[i] = "e";
		}

		for(int i = 0; i < yCoord.length; i++){
			yCoord[i] = "e";
		}
	}

	public void setXValue(int slot, String input){
		xCoord[slot] = input;
	}
	public  String getXValue(int slot){
		return xCoord[slot];
	}

	public void setYValue(int slot, String input){
		yCoord[slot] = input;
	}
	public  String getYValue(int slot){
		return yCoord[slot];
	}

	public int getXLength(){
		return xCoord.length;
	}
	public int getYLength(){
		return yCoord.length;
	}
}
