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
				//System.out.println(i);
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
				//System.out.println(i);
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
	
	public boolean getXValidation(int xLocation){
		boolean isValid = false;
		
		if(xGrid[xLocation] == true){
			isValid = true;
		} else{
			isValid = false;
		}
		
		return isValid;
	}
	public void switchXValidation(int xLocation){
		if(xGrid[xLocation] == true){
			xGrid[xLocation] = false;
		} else{
			xGrid[xLocation] = true;
		}
	}
	
	public boolean getYValidation(int yLocation){
		boolean isValid = false;
		
		if(yGrid[yLocation] == true){
			isValid = true;
		} else{
			isValid = false;
		}
		
		return isValid;
	}
	public void switchYValidation(int yLocation){
		if(yGrid[yLocation] == true){
			yGrid[yLocation] = false;
		} else{
			yGrid[yLocation] = true;
		}
	}

}
