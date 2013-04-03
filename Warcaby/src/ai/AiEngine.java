package ai;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import board.BlackField;
import board.Board;

public class AiEngine {
	boolean run=false; //watek dziala gdy run = true
	Board board; //kopia stanu planszy
	boolean site; //strona dla komputera
	boolean perToClick=true;
	LinkedList<int[]> movesToDo = new LinkedList<int[]>(); //lista ruchow do wykonania
	LinkedList<int[]> tmpL = new LinkedList<int[]>(); //lista tymczasowa
	
	RandomMove rm = new RandomMove(); //ruchy losowe
	
	
	public AiEngine(Board b, boolean s){
		board=b;
		site=s;
	}
	
	
	
	public void abort(){
		run=false;
	}
	public boolean isAbort(){
		if(run) return false; else return true;
	}
	
	public boolean getSite(){
		return !site;
	}
	public boolean permisionToClick(){
		return perToClick;
	}
	
	public void computate(){
		
		
		System.out.println("procedura wystartowala");
		perToClick=false;
		run=true;
		boolean wasBeat = false;
		int[] ruch = {-1,-1,-1,-1};
		
		while(run){
		tmpL=board.PawnsToMoveList(site);
		
		if((wasBeat)&&(tmpL.getFirst()[0]==-1)){
			perToClick=true;//pozwol na klikniecia
			run=false;
			return;
		}
		
		if(tmpL.isEmpty()){ //jezeli lista pusta
			//site=!site; //zmiana stron
			perToClick=true;//pozwol na klikniecia
			run=false;
			return;
		}
		
		ruch = rm.getRandomMove(tmpL); //losowy ruch
		int[] tar = {ruch[0],ruch[1]};
		int[] dest = {ruch[2],ruch[3]};
		
		
		board.doMove(tar, dest, site);
		
		if(tmpL.getFirst()[0]==-1) { //wyjdz z petli po zwyklym przesunieciu
			//site=!site;
			perToClick=true;
			run=false;
			return;
		} else {wasBeat=true;}
		
			
		}
		((BlackField)board.getField(ruch[2], ruch[3])).getPawn().checkAndSetKing(ruch[2]);

		if(board.checkGameStatus(tmpL)){
			JOptionPane.showMessageDialog(null, "Wygrales");
		}
		
		System.out.println("procedura zakonczona");
		
		
	}
	
	public static void main(String[] args){
		

	}

}
