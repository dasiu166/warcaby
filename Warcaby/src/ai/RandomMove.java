package ai;

import java.util.Iterator;
import java.util.LinkedList;

public class RandomMove {
	
	public int[] getRandomMove(LinkedList<int[]> moveL){
		//Zwraca null gdy brak ruchow, inaczej losowy ruch
		
		
		if((moveL.isEmpty())/*||((moveL.size()==1)&&(moveL.getFirst()[0]!=-1))*/) {
			return null;
			}
		
		if((moveL.size()==2)&&(moveL.getFirst()[0]==-1)){
			return moveL.get(1);
		}
		
		int[] tab = moveL.getFirst();
		
		if (tab[0]<0){ //dla zwyklego ruchu
			int index = (int)(1+ (Math.random()*(moveL.size()-1)));
			if(moveL.size()==1) return null;
			return moveL.get(index);
		} else { //dla bic
			int index = (int)((Math.random()*(moveL.size())));
			return moveL.get(index);
		}
		
		
		
		
	}

}
