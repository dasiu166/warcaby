package ai;

import java.util.Iterator;
import java.util.LinkedList;

import board.BlackField;
import board.Board;

public class KingAndBeatMove {
	
	LinkedList<int[]> kingMove = new LinkedList<int[]>();
	LinkedList<int[]> beatMove = new LinkedList<int[]>();
	LinkedList<int[]> normalMove = new LinkedList<int[]>();
	RandomMove rm = new RandomMove();
	
	
	public int[] getMove(LinkedList<int[]> moveL, boolean s, Board b){
		int[] t = {0};
		kingMove.clear();
		beatMove.clear();
		normalMove.clear();
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
				if(m[0]==-1)// continue;
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
			
			//--------------ZWYKLY RUCH
			if(moveL.getFirst()[0]==-1) { //operacje dla zwyklego ruchu
				//return rm.getRandomMove(moveL); //jak brak bic zwroc losowy ruch
				int index=0;
				normalMove.clear();
				Iterator<int[]> zr = moveL.iterator();
				while(zr.hasNext()){
					int[] w = zr.next();
					if(w[0]==-1) continue;
					normalMove.add(w);
				}
				
				zr = moveL.iterator();
				
				while(zr.hasNext()){
					int[] tab = zr.next();
					if(tab[0]==-1) continue; //omin pierwszy wiersz zaznaczenia ze to zwykly ruch
					
					int[] tar = {tab[2],tab[3]};
					
					if((tab[2]-1>=0)&&(tab[3]-1>=0)&&(tab[2]+1<8)&&(tab[3]+1<8)){
						//System.out.println("MOZNA SPRAWDZIC");
					if(((BlackField)b.getField(tab[2]-1, tab[3]-1)).havePawn()){
						if((((BlackField)b.getField(tab[2]-1, tab[3]-1)).getPawn().getSide()!=s)
								&& ((!((BlackField)b.getField(tab[2]+1, tab[3]+1)).havePawn())||
								((tab[0]==tab[2]+1)&&(tab[1]==tab[3]+1)))){
							//System.out.println("USUWAM");
							normalMove.remove(tab);
							//zr.remove();
							//index++;
							continue; //pomin ten ruch
						}
					}
					}
					
					if((tab[2]-1>=0)&&(tab[3]-1>=0)&&(tab[2]+1<8)&&(tab[3]+1<8)){
						//System.out.println("MOZNA SPRAWDZIC");
					if(((BlackField)b.getField(tab[2]+1, tab[3]-1)).havePawn()){
						if((((BlackField)b.getField(tab[2]+1, tab[3]-1)).getPawn().getSide()!=s)
								&& ((!((BlackField)b.getField(tab[2]-1, tab[3]+1)).havePawn())||
								((tab[0]==tab[2]-1)&&(tab[1]==tab[3]+1)))){
							//System.out.println("USUWAM");
							//zr.remove();
							normalMove.remove(tab);
							//index++;
							continue; //pomin ten ruch
						}
					}
					}
					if((tab[2]-1>=0)&&(tab[3]-1>=0)&&(tab[2]+1<8)&&(tab[3]+1<8)){
						//System.out.println("MOZNA SPRAWDZIC");
					if(((BlackField)b.getField(tab[2]-1, tab[3]+1)).havePawn()){
						if((((BlackField)b.getField(tab[2]-1, tab[3]+1)).getPawn().getSide()!=s)
								&& ((!((BlackField)b.getField(tab[2]+1, tab[3]-1)).havePawn())||
										((tab[0]==tab[2]+1)&&(tab[1]==tab[3]-1)))){
							//System.out.println("USUWAM");
							//zr.remove();
							normalMove.remove(tab);
							//index++;
							continue; //pomin ten ruch
						}
					}
					}
					
					if((tab[2]-1>=0)&&(tab[3]-1>=0)&&(tab[2]+1<8)&&(tab[3]+1<8)){
						//System.out.println("MOZNA SPRAWDZIC");
					if(((BlackField)b.getField(tab[2]+1, tab[3]+1)).havePawn()){
						if((((BlackField)b.getField(tab[2]+1, tab[3]+1)).getPawn().getSide()!=s)
								&& ((!((BlackField)b.getField(tab[2]-1, tab[3]-1)).havePawn())||
										((tab[0]==tab[2]-1)&&(tab[1]==tab[3]-1)))){
							//normalMove.remove(index);
							//System.out.println("USUWAM");
							//zr.remove();
							normalMove.remove(tab);
							//index++;
							continue; //pomin ten ruch
						}
					}
					}
					
					//normalMove.add(tab);//jak przejdzie przez wszystkie to dodaj ten ruch
					
					index++;
				}
				
				/*zr = normalMove.iterator();
				System.out.println("WYPIS Z LISTY ZWYKLYCH RUCHOW = "+normalMove.size());
				while(zr.hasNext()){
					int[] e = zr.next();
					System.out.println("RUCH ZW "+e[0]+e[1]+"  "+e[2]+e[3]);
				}*/
				if(!normalMove.isEmpty()){ 
					
					
					//BEZPIECZENSTWO RUCHU
					//kopiowanie listy ruchow
					LinkedList<int[]> backUp = new LinkedList<int[]>();
					Iterator<int[]> zz = normalMove.iterator();
					while(zz.hasNext()){
						int[] tt = zz.next();
						if(tt[0]==-1) continue;
						backUp.add(tt);
					}
					Iterator<int[]> zzz = backUp.iterator();
					while(zzz.hasNext()){
						int[] tar = zzz.next();
						
						if((tar[0]+2<8)&&(tar[1]+2<8)){
						if (((BlackField)b.getField(tar[0]+1, tar[1]+1)).havePawn()){
							if (((BlackField)b.getField(tar[0]+1, tar[1]+1)).getPawn().getSide()==s){
								if (((BlackField)b.getField(tar[0]+2, tar[1]+2)).havePawn()){
									if (((BlackField)b.getField(tar[0]+2, tar[1]+2)).getPawn().getSide()!=s){
										
										zzz.remove();
										continue;
									}
								}
							}
						}
						}
						
						if((tar[0]-2>=0)&&(tar[1]-2>=0)){
						if (((BlackField)b.getField(tar[0]-1, tar[1]-1)).havePawn()){
							if (((BlackField)b.getField(tar[0]-1, tar[1]-1)).getPawn().getSide()==s){
								if (((BlackField)b.getField(tar[0]-2, tar[1]-2)).havePawn()){
									if (((BlackField)b.getField(tar[0]-2, tar[1]-2)).getPawn().getSide()!=s){
										zzz.remove();
										continue;
									}
								}
							}
						}
						}
						
						if((tar[0]-2>=0)&&(tar[1]+2<8)){
						if (((BlackField)b.getField(tar[0]-1, tar[1]+1)).havePawn()){
							if (((BlackField)b.getField(tar[0]-1, tar[1]+1)).getPawn().getSide()==s){
								if (((BlackField)b.getField(tar[0]-2, tar[1]+2)).havePawn()){
									if (((BlackField)b.getField(tar[0]-2, tar[1]+2)).getPawn().getSide()!=s){
										zzz.remove();
										continue;
									}
								}
							}
						}
						}
						
						if((tar[0]+2<8)&&(tar[1]-2>=0)){
						if (((BlackField)b.getField(tar[0]+1, tar[1]-1)).havePawn()){
							if (((BlackField)b.getField(tar[0]+1, tar[1]-1)).getPawn().getSide()==s){
								if (((BlackField)b.getField(tar[0]+2, tar[1]-2)).havePawn()){
									if (((BlackField)b.getField(tar[0]+2, tar[1]-2)).getPawn().getSide()!=s){
										zzz.remove();
										continue;
									}
								}
							}
						}
						}
						
						
					}
					
					if(!backUp.isEmpty()){
						System.out.println("BACKUP SIZE = "+backUp.size());
						//rm.getRandomMove(backUp);
					}
					
					//BLOKOWANIE
					//sprawdzenie bic przeciwnika
					LinkedList<int[]> block = new LinkedList<int[]>();
					LinkedList<int[]> blockCombo = new LinkedList<int[]>();
					LinkedList<int[]> oponent = b.PawnsToMoveList(!s); //sprawdz bicia czlowieka
					if(oponent.getFirst()[0]!=-1){
						Iterator<int[]> o = oponent.iterator();
						while(o.hasNext()){
							int[] odest = o.next();
							
							if(!backUp.isEmpty()){
							
								Iterator<int[]> nn = backUp.iterator();
								while(nn.hasNext()){
									int[] ndest = nn.next();
									if((ndest[2]==odest[2])&&(ndest[3]==ndest[3])){
										//jak sie da zablokowac bicie to blokuj
										blockCombo.add(ndest);
									}
								}
							}
							Iterator<int[]> n = normalMove.iterator();
							while(n.hasNext()){
								int[] ndest = n.next();
								if((ndest[2]==odest[2])&&(ndest[3]==ndest[3])){
									//jak sie da zablokowac bicie to blokuj
									block.add(ndest);
								}
							}
							
						}
						
						if(!blockCombo.isEmpty()){ //blok z bezpiecznym ruchem
							return rm.getRandomMove(blockCombo);
						}
						
						if(!block.isEmpty()){ //jak mozna cosik zablokowac to zwroc losowy z puli
							return rm.getRandomMove(block);
						}
						
					}
					//if(!backUp.isEmpty()) rm.getRandomMove(backUp); else 
				return rm.getRandomMove(normalMove);
				
				
				} else return rm.getRandomMove(moveL);
			
			}
			//----------OPERACJE DLA BIC
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
