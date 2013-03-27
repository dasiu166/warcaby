package GUI;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameOptionPanel extends JPanel implements ActionListener {
	private JButton JBOk;
	private JOptionPane JOPStrona;
	private String[] opcje = {"Czerwone", "Niebieskie"};
	private BorderLayout BL = new BorderLayout();
	
	public GameOptionPanel(){
		this.setSize(300, 150);
		this.setLayout(BL);
		
		JBOk = new JButton("OK");
		JBOk.addActionListener(this);
		this.add(JBOk, BL.SOUTH);
	
		
		JOPStrona = new JOptionPane(opcje);
		JOPStrona.setVisible(true);
		this.add(JOPStrona, BL.CENTER);
		

		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==JBOk){
			this.setVisible(false);
		}
	}

}
