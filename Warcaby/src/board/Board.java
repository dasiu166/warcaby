package board;

import checkers.Pawn;

public class Board {
	private Field[][] board = new Field[8][8];
	
	/*DOWN
	 * pozniej jezeli pole bedzie biale lub puste to zwroci -1
	 * czyli ze nie bedzie mozna go zanzaczyc*/
	private int rowChkField=-1;//wiersz zaznaczonego pionka
	private int colChkField=-1;//kolumna zaznaczonego pionka
	
	
	
	public void setRowChkF(int r){
		rowChkField=r;
	}
	public void setColChkF(int r){
		colChkField=r;
	}
	public int getRowChkF(){
		return rowChkField;
	}
	public int getColChkF(){
		return colChkField;
	}
	
	
	public Field getField(int r, int c){
		return board[r][c];
	}
	public void setField(int r, int c, Field f){
		board[r][c]=f;
	}
	
	
	public void makeBoard(){
		/*uwaga w opisie bede stosowal rzeczywiste odniesienie do wspolrzednych
		 * mimo iz z kodu bedzie wynikac na odwrot, dlatego ze zaczynamy liczyc
		 * od 0, a wspolrzedne rzeczywiste sa od 1
		 * 
		 * 
		 * Ogolna zasada jest taka ze w np wierszach czarne pola sa w np kolumnach
		 * a w p wierszach sa w p kolumnach*/
		rowChkField=-1;//reset zaznaczonych
		colChkField=-1;//reset zanzaczonych
		int r=0;
		int c=0;
		//uzupelnianie  od gory
		for (r=0;r<8;r++){
			if(r%2==0){
				
				for (c=0;c<8;c++){
					if(c%2!=0) {
						board[r][c]=new BlackField();//czarne pole
						if(r<3) ((BlackField)board[r][c]).addPawn(new Pawn(r,c,false)); //gora
						if(r>4) ((BlackField)board[r][c]).addPawn(new Pawn(r,c,true)); //dol

						} else
					           board[r][c]=new WhiteField();//biale pole
				}
				
			} else {
				for (c=0;c<8;c++){
					if(c%2==0) {
						board[r][c]=new BlackField();//czarne pole
						//--pionki
						if(r<3) ((BlackField)board[r][c]).addPawn(new Pawn(r,c,false));//gora
						if(r>4) ((BlackField)board[r][c]).addPawn(new Pawn(r,c,true));//dol
						
						} else
					           board[r][c]=new WhiteField();//biale pole
				}
			}
		}
	}
	
	public void printStringBoard(){
		int r=0;
		int c=0;
		
		for(r=0;r<8;r++){
			System.out.print("\n");
			for (c=0;c<8;c++){
				
				if(board[r][c] != null )System.out.print(board[r][c].toString());
					else System.out.print("+"); 
			}
		}
		System.out.println("\nWydrukowano");
	}
	
	
	
	public void doMove(int[] tar, int[] dest){
		BlackField tF = (BlackField) this.getField(tar[0], tar[1]);
		BlackField dF = (BlackField) this.getField(dest[0], dest[1]);
				
			dF.addPawn(tF.getPawn());
			tF.removePawn();
			
			this.setField(tar[0], tar[1], tF);
			this.setField(dest[0], dest[1], dF);
		
	}
	
	public boolean permisionToMove(int[] tar, int[] dest, boolean s){
		//!!!Bardzo wazne, inaczej wyjdzie blad indexu tabeli
		if ((tar[0]==-1)||(tar[1]==-1)||(dest[0]==-1)||(dest[1]==-1)) return false;
		
		
		BlackField tF = (BlackField) this.getField(tar[0], tar[1]);
		BlackField dF = (BlackField) this.getField(dest[0], dest[1]);
		
		//Sprawdza czy to kolejka aktualnego gracza
		if (tF.getPawn()==null) return false;
		if (tF.getPawn().getSide()!=s) return false;
		
		//Sprawdza czy cel ma pionek a pole docelowe nie ma
		if ((tF.getPawn()==null) || (dF.getPawn()!=null)) return false;
		
		
		//Tu zaczyna sie zabawa
		
		if (tF.getPawn().getSide()){
			//!!!Gracz 1 - (Dol planszy)-------------------
			
			if (tF.getPawn().isKing()){
				//!!!DAMKA---------------------------------
				
			} else {
				//!!!ZWYKLY PIONEK--------------------------
				
				//jezeli ruch o jeden w przod
				if (tar[0]-dest[0]==1){
					//Sprawdza czy ruch napewno jest do przodu i po skosie
					if ((tar[0]-dest[0]!=tar[1]-dest[1])&&
							(-(tar[0]-dest[0])!=tar[1]-dest[1])) return false; 
					else return true;
				}
				
				
				//gdy chcemy bic pionka do przodu
				if (tar[0]-dest[0]==2){
					if (tar[1]-dest[1]==2){//lewa strona
						Pawn tmpP = ((BlackField)board[tar[0]-1][tar[1]-1]).getPawn();
						if (tmpP.getSide()!=tF.getPawn().getSide()){
							//jezeli to pionek przeciwnika(up) to usun(down)
							((BlackField)board[tar[0]-1][tar[1]-1]).removePawn();
							return true;
						} else return false;
					} else {//prawa strona
						Pawn tmpP = ((BlackField)board[tar[0]-1][tar[1]+1]).getPawn();
						if (tmpP.getSide()!=tF.getPawn().getSide()){
							//jezeli to pionek przeciwnika(up) to usun(down)
							((BlackField)board[tar[0]-1][tar[1]+1]).removePawn();
							return true;
						}else return false;
					}
				}
			
			}
		}
		
		if (!tF.getPawn().getSide()){
			//!!!Gracz 2 - (Gora planszy)-------------------()
			
			if (tF.getPawn().isKing()){
				//!!!DAMKA---------------------------------
				
			} else {
				//!!!ZWYKLY PIONEK--------------------------
				
				//jezeli ruch o jeden w przod
				if (tar[0]-dest[0]==-1){
					//Sprawdza czy ruch napewno jest do przodu i po skosie
					if ((tar[0]-dest[0]!=tar[1]-dest[1])&&
							(-(tar[0]-dest[0])!=tar[1]-dest[1])) return false; 
					else return true;
				}
				
				//gdy chcemy bic pionka do przodu
				if (tar[0]-dest[0]==-2){
					if (tar[1]-dest[1]==-2){//lewa strona
						Pawn tmpP = ((BlackField)board[tar[0]+1][tar[1]+1]).getPawn();
						if (tmpP.getSide()!=tF.getPawn().getSide()){
							//jezeli to pionek przeciwnika(up) to usun(down)
							((BlackField)board[tar[0]+1][tar[1]+1]).removePawn();
							return true;
						} else return false;
					} else {//prawa strona
						Pawn tmpP = ((BlackField)board[tar[0]+1][tar[1]-1]).getPawn();
						if (tmpP.getSide()!=tF.getPawn().getSide()){
							//jezeli to pionek przeciwnika(up) to usun(down)
							((BlackField)board[tar[0]+1][tar[1]-1]).removePawn();
							return true;
						}else return false;
					}
				}
			
			}
		}
		
		return false;
	}
	
	
	public static void main(String[] args){
		Board board = new Board();
		
		board.makeBoard();
		board.printStringBoard();
	}
}
