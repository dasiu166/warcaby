package ai;

import java.util.Iterator;
import java.util.LinkedList;

import board.BlackField;
import board.Board;

public class KingAndBeatMove {
	
	LinkedList<int[]> kingMove = new LinkedList<int[]>();
	LinkedList<int[]> beatMove = new LinkedList<int[]>();
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
			    //System.out.println("M2 = "+m[2]+s);
				if(m[2]==0) kingMove.add(m);
			} else {//dla niebieskich
				int[] m = i.next();
				if(m[0]==-1) continue;
				if(((BlackField)b.getField(m[0], m[1])).getPawn().isKing()) continue;
				//System.out.println("M2 = "+m[2]+s);
				if(m[2]==7) kingMove.add(m);
			}
		}
		//System.out.println("ROZMIAR KINGMOVE PRZED USUWANIEM = "+kingMove.size());
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
		//System.out.println("ROZMIAR KINGMOVE NA WYJSCIU = "+kingMove.size());
		if(!kingMove.isEmpty()){
			return rm.getRandomMove(kingMove); //zwroc ruch na damke (ma pierwszenstwo)
		} else {
			//return rm.getRandomMove(moveL);
			Iterator<int[]> z = moveL.iterator();
			if(moveL.getFirst()[0]==-1) return rm.getRandomMove(moveL); //jak brak bic zwroc losowy ruch
			
			int[] index = new int[moveL.size()]; //ilosc kolejnych bic, dla bicia o danym indexie zgodnym z indexem tabeli
			int nrIndex = 0;
			int kierunek;
			int lastKierunek=0;
			boolean brakBic = false;
			
			while(z.hasNext()){ 
				int[] tab =z.next();
				int[] tar = {tab[2],tab[3]}; //teoretyczny cel
				
				int[] v1 = { tar[0] - 2, tar[1] - 2 };
				int[] v2 = { tar[0] + 2, tar[1] - 2 };
				int[] v3 = { tar[0] - 2, tar[1] + 2 };
				int[] v4 = { tar[0] + 2, tar[1] + 2 };
				
				if(((v1[0]<0)||(v1[1]<0)||(v1[0]>7)||(v1[1]>7))){ 
					//lastKierunek=1;
					continue;
					}
				if (((BlackField)b.getField(v1[0]+1, v1[1]+1)).havePawn()) {
					if(((BlackField)b.getField(v1[0]+1, v1[1]+1)).getPawn().getSide()!=s
							&&(!((BlackField)b.getField(v1[0], v1[1])).havePawn()))
					{index[nrIndex]++;
					nrIndex++;
					continue;
					}
				}  
					if ((v2[0]<0)||(v2[1]<0)||(v2[0]>7)||(v2[1]>7)) continue;
					if (((BlackField)b.getField(v2[0]-1, v2[1]+1)).havePawn()) {
						if(((BlackField)b.getField(v2[0]-1, v2[1]+1)).getPawn().getSide()!=s
								&&(!((BlackField)b.getField(v2[0], v2[1])).havePawn()))
						{index[nrIndex]++;
						nrIndex++;
						continue;}
					} 
						if ((v3[0]<0)||(v3[1]<0)||(v3[0]>7)||(v3[1]>7)) continue;
						if (((BlackField)b.getField(v3[0]+1, v3[1]-1)).havePawn()) {
							if(((BlackField)b.getField(v3[0]+1, v3[1]-1)).getPawn().getSide()!=s
									&&(!((BlackField)b.getField(v3[0], v3[1])).havePawn()))
							{index[nrIndex]++;
							nrIndex++;
							continue;}
						}  
							if ((v4[0]<0)||(v4[1]<0)||(v4[0]>7)||(v4[1]>7)) continue;
							if (((BlackField)b.getField(v4[0]-1, v4[1]-1)).havePawn()) {
								if(((BlackField)b.getField(v4[0]-1, v4[1]-1)).getPawn().getSide()!=s
										&&(!((BlackField)b.getField(v4[0], v4[1])).havePawn()))
								{index[nrIndex]++;
								nrIndex++;
								continue;}
							} else
					nrIndex++;
				
			}
			
			for(int h=0;h<index.length;h++){
				System.out.println("Ilosc ruchow "+index[h]+" index "+h +" z "+index.length);
				if(index[h]>=1) return moveL.get(h); //zwroc ruch dla pierwszego wykrytego bicia > 1
			}
		}
		return rm.getRandomMove(moveL); //jakby co zwroc calkiem losowy
		
		

	}
	
	
}
