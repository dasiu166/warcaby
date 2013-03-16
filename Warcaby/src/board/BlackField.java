package board;

import checkers.Pawn;

public class BlackField extends Field{
	
	private Pawn pawn;
	
	public BlackField(){
		setColor(true);
	}
	
	
	public void addPawn(Pawn p){
		pawn=p;
	}
	public void removePawn(){
		pawn=null;
	}
	public Pawn getPawn(){
		return pawn;
	}
	
	public boolean havePawn(){
		if (pawn==null) return false; else return true;
	}
	
	public String toString(){
		return "#";
	}
	
	public static void main(String[] args){
		Field f;
		f = new BlackField();
		
	}
}
