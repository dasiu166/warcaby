package checkers;

public class Pawn {
	/*Klasa pionka*/
	int row,col; //pozycja na planszy
	
	
	private boolean side; //strona (true=gracz1(dol), false=gracz2(gora)) - pionki rozkladana od false(od gory)
	private boolean king=false; //true==damka
	private boolean activ;//czy pionek aktywny
	
	public Pawn(int r, int c, boolean side){
		row=r;
		col=c;
		this.side=side;
	}
	
	public Pawn(boolean side){
		this.side=side;
	}
	
	public boolean getSide(){
		return side;
	}
	public int getRow(){
		return row;
	}
	public int getCol(){
		return col;
	}
	public void setRow(int r){
		row=r;
	}
	public void setCol(int c){
		col=c;
	}
	public boolean isKing(){
		return king;
	}
	public void checkAndSetKing(int r){
		//ustwia damke gdy pionek znjadzie na ostanim przeciwleglym wierszu
		if(side){
			if (r==0) king=true;
		} else {
			if (r==7) king=true;
		}
		
	}
	
	public String toString(){
		if (side) return "0"; else return "#";
	}
	
	public static void main (String[] args){
		
	}
	
	
}
