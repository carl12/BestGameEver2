package worldPackage;


public class PlayerPiece extends Piece{

	//private String xGrid[] = new String[GUIDriver.xGrid.length];
	//private String yGrid[] = new String[GUIDriver.yGrid.length];
	
	enviroPanel init2Panel = GUIDriver.initPanel;
	
	public PlayerPiece(int x, int y){
		
		super(x, y);
		/*
		for(int i = 0; i < xGrid.length; i++){
			xGrid[i] = GUIDriver.xGrid[i];
		}
		for(int i = 0; i < yGrid.length; i++){
			yGrid[i] = GUIDriver.yGrid[i];
		}
		*/
		
		for(int i = 0; i < init2Panel.getXLength(); i++){
			init2Panel.setXValue(i, "e");
		}
		for(int i = 0; i < init2Panel.getYLength(); i++){
			init2Panel.setYValue(i, "e");
		}
		
		init2Panel.setXValue(x, "p");
		init2Panel.setYValue(y, "p");
	}
	
	/*
	public void setXLocation(int input){
		for(int i = 0; i < init2Panel.getXLength(); i ++){
			if(init2Panel.getXValue(i).compareTo("p") == 0){
				init2Panel.setXValue(i, "e");
				//System.out.println(i);
			}
		}
		init2Panel.setXValue(input, "p");
	}
	public int getXLocation(){
		int gridNumber = -1;
		for(int i = 0; i < init2Panel.getXLength(); i++){
			if(init2Panel.getXValue(i).compareTo("p") == 0){
				gridNumber = i;
			}
		}
		return gridNumber;
	}
	
	public void setYLocation(int input){
		for(int i = 0; i < init2Panel.getYLength(); i ++){
			if(init2Panel.getYValue(i).compareTo("p") == 0){
				init2Panel.setYValue(i, "e");
				//System.out.println(i);
			}
		}
		init2Panel.setYValue(input, "p");
	}
	public int getYLocation(){
		int gridNumber = -1;
		for(int i = 0; i < init2Panel.getYLength(); i++){
			if(init2Panel.getYValue(i).compareTo("p") == 0){
				gridNumber = i;
			}
		}
		return gridNumber;
	}
	*/
	
	public boolean getXValidation(int xLocation){
		boolean isValid = false;
		
		if(init2Panel.getXValue(xLocation).compareTo("p") == 0){
			isValid = true;
		} else{
			isValid = false;
		}
		
		return isValid;
	}
	public void switchXValidation(int xLocation){
		if(init2Panel.getXValue(xLocation).compareTo("p") == 0){
			init2Panel.setXValue(xLocation, "e");
		} else{
			init2Panel.setXValue(xLocation, "p");
		}
	}
	
	public boolean getYValidation(int yLocation){
		boolean isValid = false;
		
		if(init2Panel.getYValue(yLocation).compareTo("p") == 0){
			isValid = true;
		} else{
			isValid = false;
		}
		
		return isValid;
	}
	public void switchYValidation(int yLocation){
		if(init2Panel.getYValue(yLocation).compareTo("p") == 0){
			init2Panel.setYValue(yLocation, "e");
		} else{
			init2Panel.setYValue(yLocation, "p");
		}
	}

}
