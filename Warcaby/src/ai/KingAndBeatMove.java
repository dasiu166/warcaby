package ai;

import java.util.Iterator;
import java.util.LinkedList;

import board.BlackField;
import board.Board;

public class KingAndBeatMove {
	
	LinkedList<int[]> kingMove = new LinkedList<int[]>();
	RandomMove rm = new RandomMove();
	
	
	public int[] getMove(LinkedList<int[]> moveL, boolean s, Board b){
		int[] t = {0};
		kingMove.clear();
		if((moveL.isEmpty())/*||((moveL.size()==1)&&(moveL.getFirst()[0]!=-1))*/) {
			return null;
			}
		
		if((moveL.size()==2)&&(moveL.getFirst()[0]==-1)){
			return moveL.get(1);
		}
		
		//zebranie ruchow na damke
		Iterator<int[]> i = moveL.iterator();
		while(i.hasNext()){
			if(s){//dla czerwonych
				int[] m = i.next();
				if(m[0]==-1) continue;
			    if(((BlackField)b.getField(m[0], m[1])).getPawn().isKing()) continue;
			    System.out.println("M2 = "+m[2]+s);
				if(m[2]==0) kingMove.add(m);
			} else {//dla niebieskich
				int[] m = i.next();
				if(m[0]==-1) continue;
				if(((BlackField)b.getField(m[0], m[1])).getPawn().isKing()) continue;
				System.out.println("M2 = "+m[2]+s);
				if(m[2]==7) kingMove.add(m);
			}
		}
		System.out.println("ROZMIAR KINGMOVE PRZED USUWANIEM = "+kingMove.size());
		if(!kingMove.isEmpty()){ //jezeli sa jakies ruchy na damke
			
			 i = kingMove.iterator();
			 while(i.hasNext()){ //usuniecie ruchow z ktorych moglo wyjsc dalsze bicie
				if(s){//czerwone
					int[] tab = i.next();
					int[] tar = {tab[2],tab[3]};//teoretyczny cel
					int[] v1 = {tab[2]-2,tab[3]-2};
					int[] v2 = {tab[2]-2,tab[3]+2};
					
					if(b.checkBeatPosibility(tar, v1, s)) i.remove();
					if(b.checkBeatPosibility(tar, v2, s)) i.remove();
					
				} else {//niebieskie
					int[] tab = i.next();
					int[] tar = {tab[2],tab[3]};//teoretyczny cel
					int[] v1 = {tab[2]+2,tab[3]-2};
					int[] v2 = {tab[2]+2,tab[3]+2};
					
					if(b.checkBeatPosibility(tar, v1, s)) i.remove();
					if(b.checkBeatPosibility(tar, v2, s)) i.remove();
					
				}
			 }
			
		}
		System.out.println("ROZMIAR KINGMOVE NA WYJSCIU = "+kingMove.size());
		if(!kingMove.isEmpty()){
			return rm.getRandomMove(kingMove);
		} else {
			return rm.getRandomMove(moveL);
		}
		

	}
	
	
}
