package board;

import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import checkers.Pawn;

public class Board {
	private Field[][] board = new Field[8][8];

	/*
	 * DOWN pozniej jezeli pole bedzie biale lub puste to zwroci -1 czyli ze nie
	 * bedzie mozna go zanzaczyc
	 */
	private int rowChkField = -1;// wiersz zaznaczonego pionka
	private int colChkField = -1;// kolumna zaznaczonego pionka
	
	private LinkedList<int[]> pawnsToRemove = new LinkedList<int[]>(); //pionki oznaczone jako do usuniecia bo biciu (potrzebne do cofniecia ruchu)
	private int[] possStart = {-1,-1}; //pozycja pionka przed biciem
	
	public void setPossStart(int[] t){
		possStart=t;
	}
	public int[] getPossStart(){
		return possStart;
	}
	public void clearPawnsToRemoveList(){
		pawnsToRemove.clear();
	}
	
	public void setRowChkF(int r) {
		rowChkField = r;
	}

	public void setColChkF(int r) {
		colChkField = r;
	}

	public int getRowChkF() {
		return rowChkField;
	}

	public int getColChkF() {
		return colChkField;
	}

	public Field getField(int r, int c) {
		return board[r][c];
	}

	public void setField(int r, int c, Field f) {
		board[r][c] = f;
	}

	public void makeTestBoard1() {

		rowChkField = -1;// reset zaznaczonych
		colChkField = -1;// reset zanzaczonych
		int r = 0;
		int c = 0;
		// uzupelnianie od gory
		for (r = 0; r < 8; r++) {
			if (r % 2 == 0) {

				for (c = 0; c < 8; c++) {
					if (c % 2 != 0) {
						board[r][c] = new BlackField();// czarne pole
					} else
						board[r][c] = new WhiteField();// biale pole
				}

			} else {
				for (c = 0; c < 8; c++) {
					if (c % 2 == 0) {
						board[r][c] = new BlackField();// czarne pole
					} else
						board[r][c] = new WhiteField();// biale pole
				}
			}
		}

		/*
		 * ((BlackField) board[6][1]).addPawn(new Pawn(r, c,false));
		 * ((BlackField) board[5][2]).addPawn(new Pawn(r, c,false));
		 * ((BlackField) board[6][5]).addPawn(new Pawn(r, c,false));
		 * 
		 * ((BlackField) board[1][0]).addPawn(new Pawn(r, c,true));
		 * ((BlackField) board[1][2]).addPawn(new Pawn(r, c,true));
		 * ((BlackField) board[1][4]).addPawn(new Pawn(r, c,true));
		 */

		Pawn p1 = new Pawn(r, c, false);
		((BlackField) board[1][2]).addPawn(p1);
		//((BlackField) board[2][5]).addPawn(p1);
		//((BlackField) board[4][3]).addPawn(p1);
		//((BlackField) board[5][6]).addPawn(p1);
		//((BlackField) board[4][5]).addPawn(p1);
		Pawn p2 = new Pawn(r, c, true);
		p2.setKing();
		((BlackField) board[3][4]).addPawn(p2);

	}

	public void makeBoard() {
		/* */
		rowChkField = -1;// reset zaznaczonych
		colChkField = -1;// reset zanzaczonych
		int r = 0;
		int c = 0;
		// uzupelnianie od gory
		for (r = 0; r < 8; r++) {
			if (r % 2 == 0) {

				for (c = 0; c < 8; c++) {
					if (c % 2 != 0) {
						board[r][c] = new BlackField();// czarne pole
						if (r < 3)
							((BlackField) board[r][c]).addPawn(new Pawn(r, c,
									false)); // gora
						if (r > 4)
							((BlackField) board[r][c]).addPawn(new Pawn(r, c,
									true)); // dol

					} else
						board[r][c] = new WhiteField();// biale pole
				}

			} else {
				for (c = 0; c < 8; c++) {
					if (c % 2 == 0) {
						board[r][c] = new BlackField();// czarne pole
						// --pionki
						if (r < 3)
							((BlackField) board[r][c]).addPawn(new Pawn(r, c,
									false));// gora
						if (r > 4)
							((BlackField) board[r][c]).addPawn(new Pawn(r, c,
									true));// dol

					} else
						board[r][c] = new WhiteField();// biale pole
				}
			}
		}
	}

	public void printStringBoard() {
		int r = 0;
		int c = 0;

		for (r = 0; r < 8; r++) {
			System.out.print("\n");
			for (c = 0; c < 8; c++) {

				if (board[r][c] != null)
					System.out.print(board[r][c].toString());
				else
					System.out.print("+");
			}
		}
		System.out.println("\nWydrukowano");
	}

	public void doMove(int[] tar, int[] dest) {
		//zwykle przenisienie pionkow
		BlackField tF = (BlackField) this.getField(tar[0], tar[1]);
		BlackField dF = (BlackField) this.getField(dest[0], dest[1]);

		dF.addPawn(tF.getPawn());
		tF.removePawn();

		this.setField(tar[0], tar[1], tF);
		this.setField(dest[0], dest[1], dF);

	}

	public void doMove(int[] tar, int[] dest, boolean s) {
		BlackField tF = (BlackField) this.getField(tar[0], tar[1]);
		BlackField dF = (BlackField) this.getField(dest[0], dest[1]);

		dF.addPawn(tF.getPawn());
		tF.removePawn();

		this.setField(tar[0], tar[1], tF);
		this.setField(dest[0], dest[1], dF);
		
		int rR = tar[0]-dest[0];
		int cR = tar[1]-dest[1];
		
			if ((rR>0)&&(cR>0)){ //przod lewy
				//System.out.println("Do zbicia");
				int c = tar[1];
				for(int r=tar[0]-1;r>dest[0];r--){
						c--;
						if(((BlackField) board[r][c]).havePawn()){
							int[] t = {r,c};
							pawnsToRemove.add(t);
						}
						((BlackField) board[r][c]).removePawn();
						//System.out.println("Do zbicia "+r+"  "+c);
				}
				return;	
			}
			
			if ((rR>0)&&(cR<0)){ //przod prawy
				System.out.println("Do zbicia");
				int c = tar[1];
				for(int r=tar[0]-1;r>dest[0];r--){
						c++;
						if(((BlackField) board[r][c]).havePawn()){
							int[] t = {r,c};
							pawnsToRemove.add(t);
						}
						((BlackField) board[r][c]).removePawn();
				}
				return;	
			}
			
			if ((rR<0)&&(cR>0)){ //tyl prawy
				System.out.println("Do zbicia");
				int c = tar[1];
				for(int r=tar[0]+1;r<dest[0];r++){
						c--;
						if(((BlackField) board[r][c]).havePawn()){
							int[] t = {r,c};
							pawnsToRemove.add(t);
						}
						((BlackField) board[r][c]).removePawn();
				}
				return;	
			}
			
			if ((rR<0)&&(cR<0)){// tyl prawy
				System.out.println("Do zbicia");
				int c = tar[1];
				for(int r=tar[0]+1;r<dest[0];r++){
						c++;
						if(((BlackField) board[r][c]).havePawn()){
							int[] t = {r,c};
							pawnsToRemove.add(t);
						}
						((BlackField) board[r][c]).removePawn();
				}
				return;	
			}
	}
	
	public void backMove(boolean s){
		//if((possStart==null)||(possStart[0]==-1)||(possStart[1]==-1)) return;
		if(pawnsToRemove==null) return;
		if(!pawnsToRemove.isEmpty()){
		
			Iterator<int[]> i = pawnsToRemove.iterator();
			while(i.hasNext()){
				int[] t = i.next();
				((BlackField)board[t[0]][t[1]]).addPawn(new Pawn(!s));//zwroc odwrotny kolor
			}
	}
	}

	public int getLostPawnsNumb(boolean s) {
		// zwraca iloscionkow straconych przez dana strone
		int numb = 0;
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (board[r][c].getColor()) {
					if (((BlackField) board[r][c]).havePawn()) {
						if (((BlackField) board[r][c]).getPawn().getSide() == s) {
							numb++;
						}
					}
				}
			}
		}
		return 12 - numb;
	}

	public LinkedList<int[]> PawnsToMoveList(boolean s) {
		LinkedList<int[]> lista = new LinkedList<int[]>();
		// linked list zwraca tabele mozliwych ruchow mozliwych pionkow w
		// formacie
		// [0][1] - row i col celu (wskazanego pionka)
		// [2][3] - row i col przeznaczenia (miejsca na ktore ma sie
		// przemiescic)
		// jako ze bicia obowiazkowe to pojedyncze ruchy sa dostepne tylko wtedy
		// kiedy nie ma bica

		// SPRAWDZENIE BIC
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (board[r][c].getColor()) {// tylko pola czarne
					BlackField bF = ((BlackField) board[r][c]);
					int[] target = { r, c };

					if (bF.havePawn()) {// damki
						if (bF.getPawn().isKing()) {
							this.checkKingBeat(target, lista, s);
							continue;
						}
						
					}

					int[] v1 = { target[0] - 2, target[1] - 2 };
					int[] v2 = { target[0] + 2, target[1] - 2 };
					int[] v3 = { target[0] - 2, target[1] + 2 };
					int[] v4 = { target[0] + 2, target[1] + 2 };

					if (this.checkBeatPosibility(target, v1, s)) {
						int[] tab = { r, c, v1[0], v1[1] };
						lista.add(tab);
					}
					if (this.checkBeatPosibility(target, v2, s)) {
						int[] tab = { r, c, v2[0], v2[1] };
						lista.add(tab);
					}
					if (this.checkBeatPosibility(target, v3, s)) {
						int[] tab = { r, c, v3[0], v3[1] };
						lista.add(tab);
					}
					if (this.checkBeatPosibility(target, v4, s)) {
						int[] tab = { r, c, v4[0], v4[1] };
						lista.add(tab);
					}
				}
			}
		}

		if (lista.isEmpty()) {
			int[] one = { -1, 0, 0, 0 }; // zaznacza ze chodzi o pojedyncze
											// ruchy,potrzebne do skonczenia
											// wielobicia
			lista.add(one);
			for (int r = 0; r < 8; r++) {
				for (int c = 0; c < 8; c++) {
					if (board[r][c].getColor()) {// tylko pola czarne
						BlackField bF = ((BlackField) board[r][c]);
						int[] target = { r, c };

						// RUCH BEZ BICIA DLA DAMKI
						if (bF.havePawn()) {
							if (bF.getPawn().isKing()) {

								int v[] = new int[2];

								// perspektywa pionkow czerownych ale dziala dla
								// wszystkich
								v[0] = target[0];
								v[1] = target[1];
								// przod prawo
								while (1 == 1) {
									System.out.println("przod prawo");
									v[0]--;
									v[1]++;
									if (v[1] > 7)
										break;
									if (v[0] < 0)
										break;

									// System.out.println(v[0]+"   "+v[1]);
									if (this.checkOneMovePosibility(target, v,
											s)) {
										int[] tab = { r, c, v[0], v[1] };
										lista.add(tab);
									} else
										break;
								}
								v[0] = target[0];
								v[1] = target[1];
								// przod lewo
								System.out.println("przod lewo");
								while (1 == 1) {
									v[0]--;
									v[1]--;
									if (v[1] < 0)
										break;
									if (v[0] < 0)
										break;

									// System.out.println(v[0]+"   "+v[1]);
									if (this.checkOneMovePosibility(target, v,
											s)) {
										int[] tab = { r, c, v[0], v[1] };
										lista.add(tab);
									} else
										break;
								}
								v[0] = target[0];
								v[1] = target[1];
								// tyl lewo
								System.out.println("tyl lewo");
								while (1 == 1) {
									v[0]++;
									v[1]--;
									if (v[1] < 0)
										break;
									if (v[0] > 7)
										break;

									// System.out.println(v[0]+"   "+v[1]);
									if (this.checkOneMovePosibility(target, v,
											s)) {
										int[] tab = { target[0], target[1],
												v[0], v[1] };
										lista.add(tab);
									} else
										break;
								}
								v[0] = target[0];
								v[1] = target[1];
								// tyl prawo
								System.out.println("tyl prawo");
								while (1 == 1) {
									v[0]++;
									v[1]++;
									if (v[1] > 7)
										break;
									if (v[0] > 7)
										break;

									// System.out.println(v[0]+"   "+v[1]);
									if (this.checkOneMovePosibility(target, v,
											s)) {
										int[] tab = { r, c, v[0], v[1] };
										lista.add(tab);
									} else
										break;
								}
								// System.out.println("-----------------");

								continue; // przechodzi do nastepnej iteracji
											// columny/wiersza
							}
						}
						// ----------------------------

						int[] v1 = new int[2];
						int[] v2 = new int[2];
						if (s) {
							v1[0] = target[0] - 1;
							v1[1] = target[1] - 1;
							v2[0] = target[0] - 1;
							v2[1] = target[1] + 1;
						} else {
							v1[0] = target[0] + 1;
							v1[1] = target[1] - 1;
							v2[0] = target[0] + 1;
							v2[1] = target[1] + 1;
						}

						if (this.checkOneMovePosibility(target, v1, s)) {
							int[] tab = { r, c, v1[0], v1[1] };
							lista.add(tab);
						}
						if (this.checkOneMovePosibility(target, v2, s)) {
							int[] tab = { r, c, v2[0], v2[1] };
							lista.add(tab);
						}
					}
				}
			}
		}

		// wypis dla testow
		Iterator<int[]> i = lista.iterator();
		while (i.hasNext()) {
			int[] t = i.next();
			System.out.println("Pionek R=" + t[0] + "  C=" + t[1] + " NA POLE "
					+ t[2] + " " + t[3]);
		}
		System.out.println("--------------------------------");
		return lista;
	}

	public boolean checkOneMovePosibility(int[] tar, int[] dest, boolean s) {
		// !!!Bardzo wazne, inaczej wyjdzie blad indexu tabeli
		if ((tar[0] < 0) || (tar[1] < 0) || (dest[0] < 0) || (dest[1] < 0))
			return false;
		if ((tar[0] > 7) || (tar[1] > 7) || (dest[0] > 7) || (dest[1] > 7))
			return false;

		BlackField tF = (BlackField) this.getField(tar[0], tar[1]);
		BlackField dF = (BlackField) this.getField(dest[0], dest[1]);

		// Sprawdza czy to kolejka aktualnego gracza
		if (tF.getPawn() == null)
			return false;
		if (tF.getPawn().getSide() != s)
			return false;

		// Sprawdza czy cel ma pionek a pole docelowe nie ma
		if ((tF.getPawn() == null) || (dF.getPawn() != null))
			return false;

		if (tF.getPawn().getSide()) {
			// !!!Gracz 1 - (Dol planszy)-------------------

			if (tF.getPawn().isKing()) {
				// !!!DAMKA---------------------------------
				/*
				 * Moze poruszac sie o dowolna ilosc pol, ale tylko po skosie
				 * nie moze przeskakiwac swoich pionkow
				 */
				System.out.println("DAMKA DAMKA DAMKA");
				int rVal = tar[0] - dest[0]; // roznica wierszy celu i
												// rzeznczenia
				int cVal = tar[1] - dest[1]; // roznica kolumn celu i
												// przeznaczenia
				// return true;
				if (Math.abs(rVal) == Math.abs(cVal)) {
					if (dF.havePawn()) {
						if (dF.getPawn().getSide() == tF.getPawn().getSide())
							return false;// jesli napotka pionka koloru damki to
											// wyjdz
					}
					return true;
				} else
					return false;

			} else {
				// !!!ZWYKLY PIONEK--------------------------

				// jezeli ruch o jeden w przod
				if (tar[0] - dest[0] == 1) {
					// Sprawdza czy ruch napewno jest do przodu i po skosie
					if ((tar[0] - dest[0] != tar[1] - dest[1])
							&& (-(tar[0] - dest[0]) != tar[1] - dest[1]))
						return false;
					else
						return true;

				}
			}
		}

		if (!tF.getPawn().getSide()) {
			// !!!Gracz 1 - (Dol planszy)-------------------

			if (tF.getPawn().isKing()) {
				// !!!DAMKA---------------------------------
				/*
				 * Moze poruszac sie o dowolna ilosc pol, ale tylko po skosie
				 * nie moze przeskakiwac swoich pionkow
				 */

				int rVal = tar[0] - dest[0]; // roznica wierszy celu i
												// rzeznczenia
				int cVal = tar[1] - dest[1]; // roznica kolumn celu i
												// przeznaczenia

				// return true;
				if (Math.abs(rVal) == Math.abs(cVal)) {
					System.out.println(rVal + " --- " + cVal);
					return true;
				} else
					return false;

			} else {
				// !!!ZWYKLY PIONEK--------------------------

				if (tar[0] - dest[0] == -1) {
					// Sprawdza czy ruch napewno jest do przodu i po skosie
					if ((tar[0] - dest[0] != tar[1] - dest[1])
							&& (-(tar[0] - dest[0]) != tar[1] - dest[1]))
						return false;
					else
						return true;
				}
			}
		}

		return false;
	}

	public void checkKingBeat(int[] tar, LinkedList<int[]> l, boolean s) {
		int r = tar[0];
		int c = tar[1];
		int wb = 0;// wykryto bicie
		// przod lewo
		while (1 == 1) {
			r--;
			c--;
			if ((r < 0) || (c < 0))
				break;
			Pawn tmpP = ((BlackField) board[r][c]).getPawn();
			if (wb == 0) {// przed wykryciem bicia
				if (tmpP == null)
					continue;
				else if (tmpP.getSide() == s)
					break;
				else if (tmpP.getSide() != s) {
					wb = 1;
					continue;
				}
			}
			if (wb == 1) {// po wykryciu bicia
				if (tmpP == null) {
					int[] t = { tar[0], tar[1], r, c };
					l.add(t);
				} else
					break;
			}
		}

		// przod prawo
		r = tar[0];
		c = tar[1];
		wb = 0;// wykryto bicie
		while (1 == 1) {
			r--;
			c++;
			if ((r < 0) || (c > 7))
				break;
			Pawn tmpP = ((BlackField) board[r][c]).getPawn();
			if (wb == 0) {// przed wykryciem bicia
				if (tmpP == null)
					continue;
				else if (tmpP.getSide() == s)
					break;
				else if (tmpP.getSide() != s) {
					wb = 1;
					continue;
				}
			}
			if (wb == 1) {// po wykryciu bicia
				if (tmpP == null) {
					int[] t = { tar[0], tar[1], r, c };
					l.add(t);
				} else
					break;
			}
		}
		// tyl lewo
		r = tar[0];
		c = tar[1];
		wb = 0;// wykryto bicie
		while (1 == 1) {
			r++;
			c--;
			if ((r > 7) || (c < 0))
				break;
			Pawn tmpP = ((BlackField) board[r][c]).getPawn();
			if (wb == 0) {// przed wykryciem bicia
				if (tmpP == null)
					continue;
				else if (tmpP.getSide() == s)
					break;
				else if (tmpP.getSide() != s) {
					wb = 1;
					continue;
				}
			}
			if (wb == 1) {// po wykryciu bicia
				if (tmpP == null) {
					int[] t = { tar[0], tar[1], r, c };
					l.add(t);
				} else
					break;
			}
		}

		// tyl prawo
		r = tar[0];
		c = tar[1];
		wb = 0;// wykryto bicie
		while (1 == 1) {
			r++;
			c++;
			if ((r > 7) || (c > 7))
				break;
			Pawn tmpP = ((BlackField) board[r][c]).getPawn();
			if (wb == 0) {// przed wykryciem bicia
				if (tmpP == null)
					continue;
				else if (tmpP.getSide() == s)
					break;
				else if (tmpP.getSide() != s) {
					wb = 1;
					continue;
				}
			}
			if (wb == 1) {// po wykryciu bicia
				if (tmpP == null) {
					int[] t = { tar[0], tar[1], r, c };
					l.add(t);
				} else
					break;
			}
		}

	}

	public boolean checkBeatPosibility(int[] tar, int[] dest, boolean s) {
		// !!!Bardzo wazne, inaczej wyjdzie blad indexu tabeli
		if ((tar[0] < 0) || (tar[1] < 0) || (dest[0] < 0) || (dest[1] < 0))
			return false;
		if ((tar[0] > 7) || (tar[1] > 7) || (dest[0] > 7) || (dest[1] > 7))
			return false;

		BlackField tF = (BlackField) this.getField(tar[0], tar[1]);
		BlackField dF = (BlackField) this.getField(dest[0], dest[1]);

		if ((tF.getPawn() == null))
			return false;

		if (dF.getPawn() != null)
			return false;

		if (tF.getPawn().getSide() != s)
			return false;

		if (tF.getPawn().getSide()) {
			// !!!Gracz 1 - (Dol planszy)-------------------

			if (tF.getPawn().isKing()) {

			} else {
				// !!!ZWYKLY PIONEK--------------------------

				// gdy chcemy bic pionka do przodu
				if (tar[0] - dest[0] == 2) {
					if (tar[1] - dest[1] == 2) {// lewa strona
						Pawn tmpP = ((BlackField) board[tar[0] - 1][tar[1] - 1])
								.getPawn();
						if (tmpP == null)
							return false;
						if (tmpP.getSide() != tF.getPawn().getSide()) {
							// jezeli to pionek przeciwnika(up) to usun(down)

							return true;
						} else
							return false;
					} else {// prawa strona
						Pawn tmpP = ((BlackField) board[tar[0] - 1][tar[1] + 1])
								.getPawn();
						if (tmpP == null)
							return false;
						if (tmpP.getSide() != tF.getPawn().getSide()) {
							// jezeli to pionek przeciwnika(up) to usun(down)

							return true;
						} else
							return false;
					}
				}

				// gdy chcemy bic pionka do tylu
				if (tar[0] - dest[0] == -2) {
					if (tar[1] - dest[1] == -2) {// lewa strona
						Pawn tmpP = ((BlackField) board[tar[0] + 1][tar[1] + 1])
								.getPawn();
						if (tmpP == null)
							return false;
						if (tmpP.getSide() != tF.getPawn().getSide()) {
							// jezeli to pionek przeciwnika(up) to usun(down)
							return true;
						} else
							return false;
					} else {// prawa strona
						Pawn tmpP = ((BlackField) board[tar[0] + 1][tar[1] - 1])
								.getPawn();
						if (tmpP == null)
							return false;
						if (tmpP.getSide() != tF.getPawn().getSide()) {
							// jezeli to pionek przeciwnika(up) to usun(down)
							return true;
						} else
							return false;
					}
				}

			}
		}

		if (!tF.getPawn().getSide()) {
			// !!!Gracz 2 - (Gora planszy)-------------------()

			if (tF.getPawn().isKing()) {
				// !!!DAMKA---------------------------------
				return false;
			} else {
				// !!!ZWYKLY PIONEK--------------------------

				// gdy chcemy bic pionka do przodu
				if (tar[0] - dest[0] == -2) {
					if (tar[1] - dest[1] == -2) {// lewa strona
						Pawn tmpP = ((BlackField) board[tar[0] + 1][tar[1] + 1])
								.getPawn();
						if (tmpP == null)
							return false;
						if (tmpP.getSide() != tF.getPawn().getSide()) {
							// jezeli to pionek przeciwnika(up) to usun(down)

							return true;
						} else
							return false;
					} else {// prawa strona
						Pawn tmpP = ((BlackField) board[tar[0] + 1][tar[1] - 1])
								.getPawn();
						if (tmpP == null)
							return false;
						if (tmpP.getSide() != tF.getPawn().getSide()) {
							// jezeli to pionek przeciwnika(up) to usun(down)

							return true;
						} else
							return false;
					}
				}
				// gdy chcemy bic pionka do tylu
				if (tar[0] - dest[0] == 2) {
					if (tar[1] - dest[1] == 2) {// lewa strona
						Pawn tmpP = ((BlackField) board[tar[0] - 1][tar[1] - 1])
								.getPawn();
						if (tmpP == null)
							return false;
						if (tmpP.getSide() != tF.getPawn().getSide()) {
							// jezeli to pionek przeciwnika(up) to usun(down)

							return true;
						} else
							return false;
					} else {// prawa strona
						Pawn tmpP = ((BlackField) board[tar[0] - 1][tar[1] + 1])
								.getPawn();
						if (tmpP == null)
							return false;
						if (tmpP.getSide() != tF.getPawn().getSide()) {
							// jezeli to pionek przeciwnika(up) to usun(down)

							return true;
						} else
							return false;
					}
				}

			}
		}

		return false;
	}
	
	public boolean checkGameStatus(LinkedList<int[]> moveL){
		//zwraca true jezeli gra zakonczona (remis/wygrana)
		
		int red = this.getLostPawnsNumb(true);
		int blue = this.getLostPawnsNumb(false);
		System.out.print("RED "+red+"  BLUE "+blue);
		if(moveL.isEmpty()) return true;//jezeli brak ruchow
		if((moveL.size()==1)&&(moveL.getFirst()[0]==-1)) return true;
		//if((red==12)||(blue==12)) return true; //wygrana jezeli braknie pionkow;
		
		return false;
	}
	
	

	public static void main(String[] args) {
		Board board = new Board();

		board.makeBoard();
		board.printStringBoard();
	}
}
