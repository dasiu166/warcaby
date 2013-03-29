package GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class PlayersPanel extends JPanel {
	
	JLabel player1;
	JLabel p1GP; //zbite pionki przez gracza 1
	
	JLabel plSite;
	
	
	JLabel player2;
	JLabel p2GP; //zbite pionki przez gracza 2
	
	
	public PlayersPanel(){
		JPanel p1P = new JPanel();
		p1P.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
		player1 = new JLabel("Gracz 1");
		player1.setForeground(Color.RED);
		p1GP = new JLabel("0");
		p1P.add(player1);
		p1P.add(p1GP);
		p1P.setBorder(BorderFactory.createLineBorder(Color.RED));
		
		JPanel siteP = new JPanel();
		siteP.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
		plSite = new JLabel();
		siteP.add(plSite);
		
		JPanel p2P = new JPanel();
		p2P.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
		player2 = new JLabel("Gracz 2");
		player2.setForeground(Color.BLUE);
		p2GP = new JLabel("0");
		p2P.add(p2GP);
		p2P.add(player2);
		p2P.setBorder(BorderFactory.createLineBorder(Color.BLUE));

		
		
		
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER,50,10));
		this.add(p1P);
		this.add(plSite);
		this.add(p2P);
		
		
		
		
	}
	
	public void setp1PG(int val){
		p1GP.setText(Integer.toString(val));
	}
	public void setp2PG(int val){
		p2GP.setText(Integer.toString(val));
	}
	public void setSite(boolean val){
		if(val) {
			plSite.setText("Czerwony");
			Font font = new Font("Comic Sans",Font.BOLD,20);
			plSite.setForeground(Color.RED);
			plSite.setFont(font);
			} else {
				plSite.setForeground(Color.BLUE);
				plSite.setText("Niebieski");
				}
	}

}
