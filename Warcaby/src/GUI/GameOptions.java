package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;

public class GameOptions extends JFrame implements ActionListener {

	
	private GameWindow gw;
	private JButton JBOk;
	private JButton JBAnuluj;
	
	private JLabel JLStrona;
	private JComboBox JCBStrona;
	private String[] opcje = {"Czerwone", "Niebieskie"};
	
	private JLabel JLGameMode;
	private JComboBox JCBGameMode;
	private String[] gameMode = {"PvP", "PvE"};

	
	private JPanel panel = new JPanel();
	private JPanel panelButtons = new JPanel();
	private JPanel panelSite = new JPanel();
	private JPanel panelGameMode = new JPanel();
	
	
	
	public GameOptions(){
		
		
		//MAIN PANEL
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		this.add(panel);
		
		
		//OPCJE
		JCBStrona = new JComboBox(opcje);
		JCBStrona.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		JCBStrona.setVisible(true);
		JLStrona = new JLabel("Strona");
		
		
		panelSite.setLayout(new BorderLayout(0,0));
		panelSite.setMaximumSize(new Dimension(350,200));
		panelSite.add(JLStrona, BorderLayout.WEST);
        //panelOptions.add(Box.createRigidArea(new Dimension(0, 0)));
        panelSite.add(JCBStrona);
       // panelOptions.add(Box.createRigidArea(new Dimension(0, 0)));
        
        JCBGameMode = new JComboBox(gameMode);
		JCBGameMode.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
		JCBGameMode.setVisible(true);
		JLGameMode = new JLabel("Typ gry");
		
		panelGameMode.setLayout(new BorderLayout(0,0));
		panelGameMode.setMaximumSize(new Dimension(350,200));
		panelGameMode.add(JLGameMode, BorderLayout.WEST);
		panelGameMode.add(JCBGameMode);
        
        panel.add(panelSite);
        panel.add(panelGameMode);
        //panel.add(Box.createRigidArea(new Dimension(0, 0)));

       // panel.add(Box.createVerticalGlue());
		//BUTTONS
		JBOk = new JButton("OK");
		JBOk.addActionListener(this);
		
		JBAnuluj = new JButton("Anuluj");
		JBAnuluj.addActionListener(this);
		
		//panelButtons.setAlignmentX(1f);
		panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));
        panelButtons.setBorder(new EmptyBorder(40,0,10,0));
        panelButtons.add(JBOk);
        panelButtons.add(Box.createRigidArea(new Dimension(5, 0)));
        panelButtons.add(JBAnuluj);
        panelButtons.add(Box.createRigidArea(new Dimension(15, 0)));
        
        panel.add(panelButtons);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
		
        this.pack();
        this.setTitle("Narazie brzydkie ale bedzie ladne;)");
		this.setSize(400, 150);
		this.setResizable(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
	public GameOptions(GameWindow g){
		gw=g;
		//MAIN PANEL
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				this.add(panel);
				
				
				//OPCJE
				JCBStrona = new JComboBox(opcje);
				JCBStrona.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
				JCBStrona.setVisible(true);
				JLStrona = new JLabel("Strona");
				
				
				panelSite.setLayout(new BorderLayout(0,0));
				panelSite.setMaximumSize(new Dimension(350,200));
				panelSite.add(JLStrona, BorderLayout.WEST);
		        //panelOptions.add(Box.createRigidArea(new Dimension(0, 0)));
		        panelSite.add(JCBStrona);
		       // panelOptions.add(Box.createRigidArea(new Dimension(0, 0)));
		        
		        JCBGameMode = new JComboBox(gameMode);
				JCBGameMode.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
				JCBGameMode.setVisible(true);
				JLGameMode = new JLabel("Typ gry");
				
				panelGameMode.setLayout(new BorderLayout(0,0));
				panelGameMode.setMaximumSize(new Dimension(350,200));
				panelGameMode.add(JLGameMode, BorderLayout.WEST);
				panelGameMode.add(JCBGameMode);
				
		        panel.add(panelSite);
		        panel.add(panelGameMode);
		        //panel.add(Box.createRigidArea(new Dimension(0, 0)));
		        
		        

		       // panel.add(Box.createVerticalGlue());
				//BUTTONS
				JBOk = new JButton("OK");
				JBOk.addActionListener(this);
				
				JBAnuluj = new JButton("Anuluj");
				JBAnuluj.addActionListener(this);
				
				//panelButtons.setAlignmentX(1f);
				panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.X_AXIS));
		        panelButtons.setBorder(new EmptyBorder(40,0,10,0));
		        panelButtons.add(JBOk);
		        panelButtons.add(Box.createRigidArea(new Dimension(5, 0)));
		        panelButtons.add(JBAnuluj);
		        panelButtons.add(Box.createRigidArea(new Dimension(15, 0)));
		        
		        panel.add(panelButtons);
		        panel.add(Box.createRigidArea(new Dimension(0, 15)));
				
		        //this.pack();
		        this.setTitle("Narazie brzydkie ale bedzie ladne;)");
				this.setSize(400, 200);
				this.setResizable(false);
		        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        this.setLocationRelativeTo(gw);
		        //this.setLocation(300, 300);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	if(e.getSource()==JBOk){
		if (gw==null){
			this.dispose();
			return;
		}
		
		boolean site,ai;
		
		
		if(JCBStrona.getSelectedItem().equals(opcje[0])){
				site=true; } else { site=false; }
		if(JCBGameMode.getSelectedItem().equals(gameMode[0])){
			ai=false; } else { ai=true; }
		
		
		gw.setEnabled(true); //odblokowanie okna
		gw.startGame(site,ai);
		this.dispose();
	}
	
	if (e.getSource()==JBAnuluj){
		gw.setEnabled(true);//odblokowanie okna
		this.dispose();
	}
		
	}
	
	public static void main(String[] args){
		 SwingUtilities.invokeLater(new Runnable() {

	            public void run() {
	                GameOptions go = new GameOptions();
	                go.setVisible(true);
	            }
	        });
	}

}
