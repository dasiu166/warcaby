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
	
	private JMenu JMRuch;
	private JMenuItem JMICofnij;
	private JMenuItem JMIHistoria;
	
	private JMenu JMPomoc;
	private JMenuItem JMIInfo;
	
	//--------------------------
	
	private GUIBoard Gboard;
	private PlayersPanel playersP;
	
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
		
		JMRuch = new JMenu("Ruch"); //Pasek menu - Ruch
		JMICofnij = new JMenuItem("Cofnij"); //Pasek menu - Ruch - Cofnij
		JMICofnij.addActionListener(this);
		JMIHistoria = new JMenuItem("Historia"); //Pasek menu - Ruch - Historia
		
		JMPomoc = new JMenu("Pomoc"); //Pasek menu - Pomoc
		JMIInfo = new JMenuItem("O programie"); //Pasek menu - Pomoc - O programie
		
		this.setJMenuBar(pasekMenu);
		pasekMenu.setVisible(true);
		
		pasekMenu.add(JMGra);
		JMGra.setHorizontalAlignment(SwingConstants.RIGHT);

		JMGra.add(JMINowaGra);
		JMGra.add(JMIReset);
		JMGra.add(JMIWyjdz);
		
		pasekMenu.add(JMRuch);
		JMRuch.add(JMICofnij);
		JMRuch.add(JMIHistoria);
		
		pasekMenu.add(Box.createHorizontalGlue()); //Klej dla przesuniecia na prawo
		pasekMenu.add(JMPomoc);
		JMPomoc.setHorizontalAlignment(SwingConstants.RIGHT);
		JMPomoc.add(JMIInfo);
		
		//##PANEL GRACZY##
		playersP = new PlayersPanel();
		playersP.setVisible(true);
		this.getContentPane().add(playersP, BorderLayout.NORTH);
		
		
		//##PLANSZA##
		Gboard = new GUIBoard();
		Gboard.setVisible(true);
		JPanel midleP = new JPanel();
		midleP.setLayout(new BoxLayout(midleP, BoxLayout.PAGE_AXIS));
		this.add(midleP);
		
		midleP.add(Box.createVerticalGlue());
		midleP.add(Gboard, Box.CENTER_ALIGNMENT);
		midleP.add(Box.createRigidArea(new Dimension(0, 100)));
		

		//##OKNO##

		this.setSize(800, 600);
		this.setTitle("Warcaby");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	public void actionPerformed(ActionEvent e){
		if (e.getSource() == JMINowaGra){
			JOptionPane.showMessageDialog(null, "Nowa gra");
			Gboard.resetGUIBoard();
		}
		
		if (e.getSource() == JMIReset){
			JOptionPane.showMessageDialog(null, "Reset");
			Gboard.resetGUIBoard();
		}
		
		if (e.getSource() == JMIWyjdz){
			JOptionPane.showMessageDialog(null, "Wyjscie");
			this.dispose();
		}
		
		//-------------------------------------------
		
		if (e.getSource() == JMICofnij){
			Gboard.backMove();
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
