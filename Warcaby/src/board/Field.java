package board;
/*Klasa abstrakcjna okreslajaca pole na planszy*/

public class Field {
	/*wsp pola [0]-row [1]-col*/
	private int[] position = new int[2];
	/*false==white  true==black*/
	private boolean color;
	
	public void setPosition(int[] pos){
		position=pos;
	}
	public void setPosition(int r, int c){
		position[0]=r;
		position[1]=c;
	}
	public void setRow(int row){
		position[0]=row;
	}
	public void setCol(int col){
		position[1]=col;
	}
	public void setColor(boolean val){
		color=val;
	}
	
	
	public int[] getPosition(){
		return position;
	}
	public int getRow(){
		return position[0];
	}
	public int getCol(){
		return position[1];
	}
	public boolean getColor(){
		return color;
	}
	
}
