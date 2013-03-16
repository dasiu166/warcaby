package GUI;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayersPanel extends JPanel {
	
	JLabel player1;
	JLabel player2;
	
	public PlayersPanel(){
		player1 = new JLabel("Gracz 1");
		player2 = new JLabel("Gracz 2");
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(player1);
		this.add(player2);
		
	}

}
