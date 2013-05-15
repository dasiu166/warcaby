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
	LinkedList<int[]> tmpL2 = new LinkedList<int[]>(); //lista tymczasowa
	
	RandomMove rm = new RandomMove(); //ruchy losowe
	KingAndBeatMove km = new KingAndBeatMove();
	int[] destC;
	private boolean won;
	
	public boolean isWon() {
		return won;
	}



	public void setWon(boolean won) {
		this.won = won;
	}



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
	public int[] getComputateDest(){
		return destC;
	}
	
	public void computate(){
		
		//System.out.println("procedura wystartowala");
		perToClick=false;
		run=true;
		boolean wasBeat = false;
		int[] ruch = {-1,-1,-1,-1};
		int[] tar={-1,-1,-1,-1};
		
		while(run){
		tmpL=board.PawnsToMoveList(site);
		//ruch = rm.getRandomMove(tmpL); //losowy ruch
		ruch = km.getMove(tmpL,site,board); //losowy ruch
		
		if(!wasBeat) {
			tar[0]=ruch[0];
			tar[1]=ruch[1];
			board.setAiPossStart(tar);
			}
		
		int[] dest = {ruch[2],ruch[3]};
		
		if(tmpL.isEmpty()){ //jezeli lista pusta
			//site=!site; //zmiana stron
			perToClick=true;//pozwol na klikniecia
			run=false;
			
			return;
		}
		
		if((wasBeat)&&(tmpL.getFirst()[0]==-1)){
			perToClick=true;//pozwol na klikniecia
			run=false;
			((BlackField)board.getField(destC[0], destC[1])).getPawn().checkAndSetKing(destC[0]);

			return;
		}
		
		if (wasBeat){
			if(!board.isTargetOnList(tmpL, tar)){//koniec wielobicia
				destC=dest;
				perToClick=true;//pozwol na klikniecia
				run=false;
				((BlackField)board.getField(destC[0], destC[1])).getPawn().checkAndSetKing(destC[0]);
				return;
			}
		}
		
		
		if(tmpL.getFirst()[0]==-1) { //wyjdz z petli po zwyklym przesunieciu
			//site=!site;
			
			board.doMove(tar, dest, false);
			destC=dest;
			perToClick=true;
			run=false;
			((BlackField)board.getField(destC[0], destC[1])).getPawn().checkAndSetKing(destC[0]);
			
			return;
		} else {
			
			board.doMove(tar, dest, false);
			wasBeat=true;
			tar=dest;
			destC=dest;
			//((BlackField)board.getField(destC[0], destC[1])).getPawn().checkAndSetKing(destC[0]);

		}
		
		tmpL2=board.PawnsToMoveList(!site);
		if(board.checkGameStatus(tmpL2)){
			JOptionPane.showMessageDialog(null, "Wygral komputer");
			won = true;
		}
		
			
		}

		
		
		//System.out.println("procedura zakonczona");
		long tval = System.currentTimeMillis();
		
		while(true){
			if(System.currentTimeMillis()-tval>=1000) break;
		}
		
	}
	
	public static void main(String[] args){
		

	}

}
