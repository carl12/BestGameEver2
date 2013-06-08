package worldPackage;

public class PlayerPiece {

	private boolean xGrid[] = new boolean[GUIDriver.xGrid.length];
	private boolean yGrid[] = new boolean[GUIDriver.yGrid.length];
	
	public PlayerPiece(){
		for(int i = 0; i < xGrid.length; i++){
			xGrid[i] = GUIDriver.xGrid[i];
		}
		for(int i = 0; i < yGrid.length; i++){
			yGrid[i] = GUIDriver.yGrid[i];
		}
		
		
		xGrid[4] = true;
		yGrid[4] = true;
	}
	
	public void setXLocation(int input){
		for(int i = 0; i < xGrid.length; i ++){
			if(xGrid[i] == true){
				xGrid[i] = false;
				System.out.println(i);
			}
		}
		xGrid[input] = true;
	}
	public int getXLocation(){
		int gridNumber = -1;
		for(int i = 0; i < xGrid.length; i++){
			if(xGrid[i] == true){
				gridNumber = i;
			}
		}
		return gridNumber;
	}
	
	public void setYLocation(int input){
		for(int i = 0; i < yGrid.length; i ++){
			if(yGrid[i] == true){
				yGrid[i] = false;
				System.out.println(i);
			}
		}
		yGrid[input] = true;
	}
	public int getYLocation(){
		int gridNumber = -1;
		for(int i = 0; i < yGrid.length; i++){
			if(yGrid[i] == true){
				gridNumber = i;
			}
		}
		return gridNumber;
	}

}
