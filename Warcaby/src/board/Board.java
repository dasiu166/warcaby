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

		if (s) {

			// gdy chcemy bic pionka do przodu
			if (tar[0] - dest[0] == 2) {
				if (tar[1] - dest[1] == 2) {// lewa strona
					Pawn tmpP = ((BlackField) board[tar[0] - 1][tar[1] - 1])
							.getPawn();
					if (tmpP == null)
						return;

					((BlackField) board[tar[0] - 1][tar[1] - 1]).removePawn();
				} else {// prawa strona
					Pawn tmpP = ((BlackField) board[tar[0] - 1][tar[1] + 1])
							.getPawn();
					if (tmpP == null)
						return;
					((BlackField) board[tar[0] - 1][tar[1] + 1]).removePawn();
				}
			}

			// gdy chcemy bic pionka do tylu
			if (tar[0] - dest[0] == -2) {
				if (tar[1] - dest[1] == -2) {// lewa strona
					Pawn tmpP = ((BlackField) board[tar[0] + 1][tar[1] + 1])
							.getPawn();
					if (tmpP == null)
						return;

					((BlackField) board[tar[0] + 1][tar[1] + 1]).removePawn();

				} else {// prawa strona
					Pawn tmpP = ((BlackField) board[tar[0] + 1][tar[1] - 1])
							.getPawn();
					if (tmpP == null)
						return;

					((BlackField) board[tar[0] + 1][tar[1] - 1]).removePawn();

				}
			}

		} else {
			// gdy chcemy bic pionka do przodu
			if (tar[0] - dest[0] == -2) {
				if (tar[1] - dest[1] == -2) {// lewa strona
					Pawn tmpP = ((BlackField) board[tar[0] + 1][tar[1] + 1])
							.getPawn();
					if (tmpP == null)
						return;

					((BlackField) board[tar[0] + 1][tar[1] + 1]).removePawn();

				} else {// prawa strona
					Pawn tmpP = ((BlackField) board[tar[0] + 1][tar[1] - 1])
							.getPawn();
					if (tmpP == null)
						return;

					((BlackField) board[tar[0] + 1][tar[1] - 1]).removePawn();

				}
			}
			// gdy chcemy bic pionka do tylu
			if (tar[0] - dest[0] == 2) {
				if (tar[1] - dest[1] == 2) {// lewa strona
					Pawn tmpP = ((BlackField) board[tar[0] - 1][tar[1] - 1])
							.getPawn();
					if (tmpP == null)
						return;

					((BlackField) board[tar[0] - 1][tar[1] - 1]).removePawn();

				} else {// prawa strona
					Pawn tmpP = ((BlackField) board[tar[0] - 1][tar[1] + 1])
							.getPawn();
					if (tmpP == null)
						return;

					((BlackField) board[tar[0] - 1][tar[1] + 1]).removePawn();

				}
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
				// !!!DAMKA---------------------------------

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

	public static void main(String[] args) {
		Board board = new Board();

		board.makeBoard();
		board.printStringBoard();
	}
}
