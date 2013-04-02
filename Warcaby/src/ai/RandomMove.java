package ai;

import java.util.Iterator;
import java.util.LinkedList;

public class RandomMove {
	
	public int[] getRandomMove(LinkedList<int[]> moveL){
		//Zwraca null gdy brak ruchow, inaczej losowy ruch
		int[] t = {-1,-1,-1,-1};
		if(moveL.isEmpty()) {
			return null;
			}
		
		int[] tab = moveL.getFirst();
		
		if (tab[0]<0){ //dla zwyklego ruchu
			int index = (int)(1+ (Math.random()*(moveL.size()-1)));
			return moveL.get(index);
		} else { //dla bic
			int index = (int)((Math.random()*(moveL.size())));
			return moveL.get(index);
		}
		
		
		
		
	}

}
