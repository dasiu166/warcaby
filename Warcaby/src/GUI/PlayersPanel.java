package GUI;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

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
		p1GP = new JLabel("0");
		p1P.add(player1);
		p1P.add(p1GP);
		
		JPanel siteP = new JPanel();
		siteP.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
		plSite = new JLabel();
		siteP.add(plSite);
		
		JPanel p2P = new JPanel();
		p2P.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
		player2 = new JLabel("Gracz 2");
		p2GP = new JLabel("0");
		p2P.add(p2GP);
		p2P.add(player2);
		
		
		
		
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
		if(val) plSite.setText("Czerwony"); else plSite.setText("Niebieski");
	}

}
