package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import checkers.Pawn;

import board.BlackField;
import board.Board;

public class GUIBoard extends JPanel implements ActionListener, MouseListener {
	/*Dla testow ten panel ma zielona ramke zeby byl widoczny*/
	
	//BEGIN###PANEL
	private int X_SIZE = 500; //szerokosc panela
	private int Y_SIZE = 500; //wysokosc panela
	private int GF_SIZE = 50; //wielkosc kwadrata na planszy (pola)
	private int FIELD_NUMB=8; //ilosc pol na planszy
	
	int XSHIFT,YSHIFT;//przesuniecie pol plaszy w zaleznosci od wielkosci tego panelu
    
	
	private int BORDER1 = 25; //szerokosc pierwszej ramki planszy
	private int BORDER2 = 25;
	private int BORDER3 = 5;
	//END###---PANEL
	
	
	//BEGIN###PLANSZA
	private Rectangle[][] boardRec = new Rectangle[8][8]; //GRAFIKA PLANSZY
	private Board boardLog = new Board(); //LOGIKA PLANSZY
	private Board tmpBoard;
	private Graphics2D g2d;
	//END###---PLANSZA
	
	
	//BEGIN###INNE
	private int numbClick; //liczy klikniecia w plansze
	private boolean site=true; //strona ktora moze sie przesunac - zaczyna dol
	private Point point1st; //wsp pierwszego klikniecia
	private Point point2nd; //wsp drugiego klikniecia
	
	private int[] target = new int[2];// wsp cel [0]-row  [1]-col
	private int[] dest = new int[2];//wsp miejsca docelowego
	private LinkedList<int[]> moveList;//lista ruchow dla danej strony
	
	private PlayersPanel plP; //panel wyswietlajacy liczbe pionkow
	private GameInfo gInf;
	//END###---INNE
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        
        /*g2d.setColor(Color.DARK_GRAY);
        g2d.fill(new Rectangle(150,25,500,500));
        g2d.setColor(Color.BLACK);
        g2d.fill(new Rectangle(175,50,450,450));
        g2d.setColor(Color.GRAY);
        g2d.fill(new Rectangle(195,70,410,410));*/
        
        
        XSHIFT = (X_SIZE-(FIELD_NUMB*GF_SIZE))/2;
        YSHIFT = (Y_SIZE-(FIELD_NUMB*GF_SIZE))/2;
        //System.out.println("XSHIFT="+XSHIFT+"   YSHIFT="+YSHIFT);
        
        g2d.setColor(Color.DARK_GRAY);
        g2d.fill(new Rectangle(XSHIFT-BORDER1-BORDER2,YSHIFT-BORDER1-BORDER2,500,500));
        g2d.setColor(Color.BLACK);
        g2d.fill(new Rectangle(XSHIFT-BORDER1,YSHIFT-BORDER1,450,450));
        g2d.setColor(Color.GRAY);
        g2d.fill(new Rectangle(XSHIFT-BORDER3,YSHIFT-BORDER3,410,410));
        
        
       for (int r=0;r<8;r++){
    	   for (int c=0;c<8;c++){
    		   
    		   
    		   if (boardLog.getField(r, c) instanceof BlackField) {
    			   //czarne pola
   			   	boardRec[r][c] = new Rectangle(XSHIFT+(c*50), YSHIFT+(r*GF_SIZE), GF_SIZE, GF_SIZE);

   					if((boardLog.getRowChkF()==r)&&(boardLog.getColChkF()==c)){
   					g2d.setColor(Color.orange);
   					g2d.fill(boardRec[r][c]);
   					}else{
   					g2d.setColor(Color.black);
   					g2d.fill(boardRec[r][c]);
   					}
   					//rysowanie pionkow
   					if(((BlackField)boardLog.getField(r, c)).havePawn()){
   						Pawn tmpPawn = ((BlackField)boardLog.getField(r, c)).getPawn();
   						
   						
   						if(!tmpPawn.getSide()){
   							//gorne pionki
   							g2d.setColor(Color.BLUE);
   							g2d.fill(new Ellipse2D.Float(
   									XSHIFT+(c*GF_SIZE),
   									YSHIFT+(r*GF_SIZE), 
   									GF_SIZE, 
   									GF_SIZE));
   							g2d.setColor(Color.BLACK);
   							g2d.fill(new Ellipse2D.Float(
   									XSHIFT+(c*GF_SIZE)+3,
   									YSHIFT+(r*GF_SIZE)+3, 
   									GF_SIZE-6, 
   									GF_SIZE-6));
   							g2d.setColor(Color.BLUE);
   							g2d.fill(new Ellipse2D.Float(
   									XSHIFT+(c*GF_SIZE)+6,
   									YSHIFT+(r*GF_SIZE)+6, 
   									GF_SIZE-12, 
   									GF_SIZE-12));
   							if(tmpPawn.isKing()){
   								g2d.setColor(Color.WHITE);
   	   							g2d.fill(new Ellipse2D.Float(
   	   									XSHIFT+(c*GF_SIZE)+18,
   	   									YSHIFT+(r*GF_SIZE)+18, 
   	   									GF_SIZE-36, 
   	   									GF_SIZE-36));
   							}
   						} else {
   							//dolne pionki
   							g2d.setColor(Color.RED);
   							g2d.fill(new Ellipse2D.Float(
   									XSHIFT+(c*GF_SIZE),
   									YSHIFT+(r*GF_SIZE), 
   									GF_SIZE, 
   									GF_SIZE));
   							g2d.setColor(Color.BLACK);
   							g2d.fill(new Ellipse2D.Float(
   									XSHIFT+(c*GF_SIZE)+3,
   									YSHIFT+(r*GF_SIZE)+3, 
   									GF_SIZE-6, 
   									GF_SIZE-6));
   							g2d.setColor(Color.RED);
   							g2d.fill(new Ellipse2D.Float(
   									XSHIFT+(c*GF_SIZE)+6,
   									YSHIFT+(r*GF_SIZE)+6, 
   									GF_SIZE-12, 
   									GF_SIZE-12));
   							if(tmpPawn.isKing()){
   								g2d.setColor(Color.WHITE);
   	   							g2d.fill(new Ellipse2D.Float(
   	   									XSHIFT+(c*GF_SIZE)+18,
   	   									YSHIFT+(r*GF_SIZE)+18, 
   	   									GF_SIZE-36, 
   	   									GF_SIZE-36));
   							}
   						}
   					}
   					
    		   } else {
    			   	//biale pola
    			   	boardRec[r][c] = new Rectangle(XSHIFT+(c*50), YSHIFT+(r*GF_SIZE), GF_SIZE, GF_SIZE);
   					g2d.setColor(Color.white);
   					g2d.fill(boardRec[r][c]);
    		   }
    	   }
       }
	}	
	
	
	public GUIBoard(){
		
		
		this.setMinimumSize(new Dimension(X_SIZE, Y_SIZE));
		this.setMaximumSize(getMinimumSize());
		this.setPreferredSize(getMinimumSize());
		this.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		this.addMouseListener(this);
		boardLog.makeBoard();
		tmpBoard=boardLog;
	}
	
	public void resetGUIBoard(boolean s){
		//resetuje plansze
		boardLog.makeBoard();
		site=s;
		plP.setSite(site);
		repaint();
	}
	
	public void testGUIBoard(){
		//resetuje plansze
		boardLog.makeTestBoard1();
		repaint();
	}
	public void backMove(){
		//cofa ostatni ruch
		if((boardLog.getPossStart()==null)) return;

		if((boardLog.getPossStart()[0]==-1)) return;
		
		if(numbClick==1) {
			JOptionPane.showMessageDialog(null, "Nie wykonales ruchu");
			return;
		}
		
			
		boardLog.doMove(dest, boardLog.getPossStart());
		System.out.println("\n Cofam z  ----- "+dest[0]+dest[1]+" na "+boardLog.getPossStart()[0]+boardLog.getPossStart()[1]);
		boardLog.backMove(!site);
		site=!site; //cofniecie zmiany stron
		
		plP.setSite(site);
		plP.setp1PG(boardLog.getLostPawnsNumb(false));//licznik zbic dla gracza 1
		plP.setp2PG(boardLog.getLostPawnsNumb(true));//licznik zbic dla gracza 2
		boardLog.clearPawnsToRemoveList();
		boardLog.setPossStart(null);
		repaint();
	}
	public void setPlP(PlayersPanel p){
		plP=p;
		plP.setSite(site);//wskazanie na panelu czyja kolej
	}
	public void setGinfo(GameInfo g){
		gInf=g;
	}
	
	
	
	public void actionPerformed(ActionEvent e){
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {	
		
		if (numbClick==0){
			tmpBoard=boardLog;
			moveList=boardLog.PawnsToMoveList(site);
			

			
			
		}
		
		
		numbClick++;
		
		if(numbClick==1){
			
			boolean is=false;
			point1st=e.getPoint();
			target=CheckAndsetLightField(point1st);
			
			if((target[0]==-1)||(target[1]==-1)) {
				numbClick=0;
				return;
				}
			Iterator<int[]> i = moveList.iterator();
			while(i.hasNext()){
				int[] t = i.next();
				if((t[0]==target[0])&&(t[1]==target[1])){
					boardLog.setPossStart(null);
					boardLog.clearPawnsToRemoveList();
					is=true;
					boardLog.setPossStart(target);//dla cofania ruchu
					break;
				}
			}
			if(!is){
				JOptionPane.showMessageDialog(null, "Tym pionkiem nie ma ruchu");
				numbClick=0;
			}
			gInf.setMoves(moveList);//wpisanie listy ruchow na zakladke
			repaint();
		}
		
		
		if(numbClick==2){//po podwojnym kliknieciu
			
			boolean is=false;
			point2nd=e.getPoint();
			dest=CheckAndsetLightField(point2nd);
			//JOptionPane.showMessageDialog(null, "RUCH z "+target[0]+" "+target[1]+" DO "+dest[0]+" "+dest[1]);
			if((dest[0]==-1)||(dest[1]==-1)) {
				numbClick=0;
				return;}
			
			if (boardLog.getField(dest[0], dest[1]).getColor()){
				if (((BlackField)boardLog.getField(dest[0], dest[1])).havePawn()){
					if (((BlackField)boardLog.getField(dest[0], dest[1])).getPawn().getSide()==site){
						Iterator<int[]> i = moveList.iterator();
						while(i.hasNext()){
							int[] t = i.next();
							if((t[0]==dest[0])&&(t[1]==dest[1])){
								numbClick=1;
								target=dest;
								return;	
							}
						}
						numbClick=0;
						JOptionPane.showMessageDialog(null, "Tym pionkiem nie ma ruchu");
						return;
						
					}
				}
			}
			
			Iterator<int[]> i = moveList.iterator();
			while(i.hasNext()){
				int[] t = i.next();
				if((t[2]==dest[0])&&(t[3]==dest[1])){
					is=true;
				}
			}
			
			
			if(is){ //jezeli przeznaczenie jest na liscie
		
				boardLog.doMove(target, dest,site); //!!!TU uruchamaia sie caly mechaniz warcab
				
				
				plP.setp1PG(boardLog.getLostPawnsNumb(false));//licznik zbic dla gracza 1
				plP.setp2PG(boardLog.getLostPawnsNumb(true));//licznik zbic dla gracza 2
				plP.setSite(!site);//wskazanie na panelu czyja kolej
				
				if(moveList.getFirst()[0]!=-1){ //jezeli nasz ruch nie byl zwyklym przesunieciem
				moveList=boardLog.PawnsToMoveList(site);//pobranie kolejnych mozliwych ruchow dla nas
								
					//jezeli lista ruchow pusta lub sa na niej tylko pojedyncze to znaczy ze koniec wielobicia
					if(moveList.isEmpty()||(moveList.getFirst()[0]==-1)){
						
						if(site)site=false;else site=true;//zamiana stron
						numbClick=0;//zerowanie klikniec
						//(down) sprawdz czy pionke moze stac sie damka
						((BlackField)boardLog.getField(dest[0], dest[1])).getPawn().checkAndSetKing(dest[0]);
					
					} else {//jezeli jest wielobicie
						
						plP.setSite(site);//wskazanie na panelu czyja kolej (teraz znowu nasza)
						target=dest; //uaktualnienie celu zeby nie klikac znowa na pionka ktorym bijemy
						numbClick=1; //przejscie odrazu do drugiego klikniecia
						
					} 
				} else {
					//jezeli zwykle przesuniecie to zmien strony i wyzeruj klikniecia
					if(site)site=false;else site=true;
					numbClick=0;
					plP.setSite(site);//wskazanie na panelu czyja kolej
					//(down) sprawdz czy pionke moze stac sie damka
					((BlackField)boardLog.getField(dest[0], dest[1])).getPawn().checkAndSetKing(dest[0]);

					}
				//sprawdzanie wygranej
				moveList=boardLog.PawnsToMoveList(site);
				//gInf.setMoves(moveList);//wpisanie listy ruchow na zakladke
				if(boardLog.checkGameStatus(moveList)){
					JOptionPane.showMessageDialog(null, "Wygrales");
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "Na to pole nie ma ruchu");
				numbClick=0;
				}
			
			
		}
		
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//JOptionPane.showMessageDialog(null, "dziala");
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public int[] CheckAndsetLightField(Point p){
		/*Jezeli kliknieto w plansze to ustawia klikniete pole jako podswietlone
		 * (tylko pola czarne, opercja na planszy logicznej)
		 * Ponadto
		 * zwraca za kazdym razem wspolrzedne kliknietego pola (na planszy)*/
		int row;
		int col;
		int[] tab = new int[2];
		for(int r=0;r<8;r++){
			for(int c=0;c<8;c++){
				if (boardRec[r][c].contains(p)){ 
					row = (int)((boardRec[r][c].getMinY()-YSHIFT)/GF_SIZE);
					col = (int)((boardRec[r][c].getMinX()-XSHIFT)/GF_SIZE);
					
					if (boardLog.getField(row, col).getColor()){ //tylko czarne (true)
						boardLog.setColChkF(col);//zaznaczenie pola
						boardLog.setRowChkF(row);//zanzaczenie pola
						
						tab[0]=row;
						tab[1]=col;
						
						repaint();
						return tab;
					}
					
				}
			}
		}
		//jesli biale pole, albo poza plansza zwraca -1
		tab[0]=-1;
		tab[1]=-1;
		return tab;
		
		
	}
	
	
}
