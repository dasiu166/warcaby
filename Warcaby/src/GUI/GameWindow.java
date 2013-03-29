package GUI;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class GameWindow extends JFrame implements ActionListener{
	private JMenuBar pasekMenu;
	
	private JMenu JMGra;
	private JMenuItem JMINowaGra;
	private JMenuItem JMIWyjdz;
	private JMenuItem JMIReset;
	private JMenuItem JMITest;
	
	private JMenu JMRuch;
	private JMenuItem JMICofnij;
	private JMenuItem JMIHistoria;
	
	private JMenu JMWidok;
	private JMenuItem JMIGameInfo;
	
	private JMenu JMPomoc;
	private JMenuItem JMIInfo;
	
	//--------------------------
	
	private GUIBoard Gboard;
	private PlayersPanel playersP;
	private GameOptions Goptions;
	private GameInfo Ginfo;
	
	
	public GameWindow(){
	
		
		
		//##PASEK MENU##
		pasekMenu = new JMenuBar();
		
		JMGra = new JMenu("Gra"); //Pasek menu - Gra
		JMGra.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		JMINowaGra = new JMenuItem("Nowa gra"); //Pasek menu - Gra - Nowa gra
		JMINowaGra.addActionListener(this);
		
		JMIReset = new JMenuItem("Reset"); //Pasek menu - Gra - Reset
		JMIReset.addActionListener(this);
		
		JMIWyjdz = new JMenuItem("Wyjdz"); //Pasek menu - Gra - Wyjdz
		JMIWyjdz.addActionListener(this);
		
		JMITest = new JMenuItem("TEST");
		JMITest.addActionListener(this);
		
		JMRuch = new JMenu("Ruch"); //Pasek menu - Ruch
		JMICofnij = new JMenuItem("Cofnij"); //Pasek menu - Ruch - Cofnij
		JMICofnij.addActionListener(this);
		JMIHistoria = new JMenuItem("Historia"); //Pasek menu - Ruch - Historia
		
		JMWidok = new JMenu("Widok");
		JMIGameInfo = new JMenuItem("Pokaz/Ukryj Info");
		JMIGameInfo.addActionListener(this);
		
		JMPomoc = new JMenu("Pomoc"); //Pasek menu - Pomoc
		JMIInfo = new JMenuItem("O programie"); //Pasek menu - Pomoc - O programie
		
		this.setJMenuBar(pasekMenu);
		pasekMenu.setVisible(true);
		
		pasekMenu.add(JMGra);
		JMGra.setHorizontalAlignment(SwingConstants.RIGHT);

		JMGra.add(JMINowaGra);
		JMGra.add(JMIReset);
		JMGra.add(JMIWyjdz);
		JMGra.add(JMITest);
		
		pasekMenu.add(JMRuch);
		JMRuch.add(JMICofnij);
		//JMRuch.add(JMIHistoria);
		
		pasekMenu.add(JMWidok);
		JMWidok.add(JMIGameInfo);
		
		pasekMenu.add(Box.createHorizontalGlue()); //Klej dla przesuniecia na prawo
		pasekMenu.add(JMPomoc);
		JMPomoc.setHorizontalAlignment(SwingConstants.RIGHT);
		JMPomoc.add(JMIInfo);
		
		//##PANEL GRACZY##
		playersP = new PlayersPanel();
		playersP.setVisible(true);
		this.getContentPane().add(playersP, BorderLayout.NORTH);
		
		//##OPCJE##
		
		
		
		//##PLANSZA##
		Gboard = new GUIBoard();
		Gboard.setVisible(false);
		Gboard.setPlP(playersP);//dla licznika
		JPanel midleP = new JPanel();
		midleP.setLayout(new BoxLayout(midleP, BoxLayout.PAGE_AXIS));
		this.add(midleP);
		
		
		midleP.add(Gboard, Box.CENTER_ALIGNMENT);
		midleP.add(Box.createRigidArea(new Dimension(0, 0)));
		//midleP.add(Box.createVerticalGlue());
		//##INFO##
		Ginfo = new GameInfo();
		Ginfo.setVisible(false);
		midleP.add(Ginfo, Box.CENTER_ALIGNMENT);
		Gboard.setGinfo(Ginfo);
		

		//##OKNO##

		this.setSize(800, 730);
		this.setTitle("Warcaby");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	public void startGame(boolean strona){
		Gboard.setVisible(true);
		Gboard.resetGUIBoard(strona);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == JMINowaGra){
			//JOptionPane.showMessageDialog(null, "Nowa gra");
			this.setEnabled(false);//zablokowanie okna
			
			Goptions = new GameOptions(this);
			Goptions.setVisible(true);
			
		}
		
		if (e.getSource() == JMIReset){
			JOptionPane.showMessageDialog(null, "Reset");
			Gboard.resetGUIBoard(true);
		}
		
		if (e.getSource() == JMIWyjdz){
			JOptionPane.showMessageDialog(null, "Wyjscie");
			this.dispose();
		}
		
		if (e.getSource() == JMITest){
			Gboard.testGUIBoard();
		}
		
		//-------------------------------------------
		
		if (e.getSource() == JMICofnij){
			Gboard.backMove();
		}
		//------------------------------------------
		if (e.getSource() == JMIGameInfo){
			if(Ginfo.isVisible())
			Ginfo.setVisible(false); else Ginfo.setVisible(true);
		}
	}
	
	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                GameWindow gw = new GameWindow();
                gw.setVisible(true);
            }
        });
		
	}

}
