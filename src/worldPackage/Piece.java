package worldPackage;

public class Piece {

	int xCoord;
	int yCoord;

	public Piece(int x, int y){
		xCoord = x;
		yCoord = y;
	}

	public int getXCoord(){
		return xCoord;
	}
	public void setXCoord(int input){
		xCoord = input;
	}

	public int getYCoord(){
		return yCoord;
	}
	public void setYCoord(int input){
		yCoord = input;
	}

	public boolean getXValidation(int xLocation){
		return false;
	}
	public void switchXValidation(int xLocation){
	}

	public boolean getYValidation(int xLocation){
		return false;
	}
	public void switchYValidation(int yLocation){
	}

}
