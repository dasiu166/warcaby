package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GameOptions extends JFrame implements ActionListener {

	
	private GameWindow gw;
	private JButton JBOk;
	private JLabel JLStrona;
	
	private JComboBox JCBStrona;
	private String[] opcje = {"Czerwone", "Niebieskie"};
	
	private GridLayout GL = new GridLayout(3,3);
	
	public GameOptions(GameWindow g){
		gw=g;
		this.setTitle("Narazie brzydkie ale nedzie ladne;)");
		this.setSize(300, 150);
		this.setResizable(false);
		this.setLayout(GL);
		GL.setHgap(40);
		
		JLStrona = new JLabel("Strona");
		this.add(JLStrona);
		
		JCBStrona = new JComboBox(opcje);
		JCBStrona.setVisible(true);
		JCBStrona.setSize(80, 30);
		this.add(JCBStrona);
		
		JBOk = new JButton("OK");
		JBOk.addActionListener(this);
		this.add(JBOk);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	if(e.getSource()==JBOk){
		if(JCBStrona.getSelectedItem().equals(opcje[0]))
			gw.startGame(true); 
			else gw.startGame(false);
		this.dispose();
	}
		
	}

}
