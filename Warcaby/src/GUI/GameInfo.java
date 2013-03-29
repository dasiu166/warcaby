package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GameInfo extends JPanel implements ActionListener {
//do wyslania
	private JTabbedPane JTPpanel;//zakladki
	private boolean s;
	private JPanel mPanel;//ruchy
	private JTextArea JTAmoves;
	private JScrollPane JSPmoves;
	private JPanel hPanel;//historia
	
	public GameInfo(){
		this.setLayout(new GridLayout(1,1));
		
		JTPpanel = new JTabbedPane();
		
		mPanel = new JPanel(new BorderLayout());
		JTAmoves = new JTextArea("Mo¿liwe ruchy");
		JTAmoves.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		JTAmoves.setEditable(false);
		JSPmoves = new JScrollPane(JTAmoves);
		
		
		//JSPmoves.add(JTAmoves);
		JSPmoves.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		mPanel.add(JSPmoves,BorderLayout.CENTER);
		JTPpanel.addTab("Ruchy", mPanel);
		
		hPanel = new JPanel();
		
		JTPpanel.addTab("Historia", hPanel);
		
		this.add(JTPpanel);
		
	}
	
	public void setMoves(LinkedList<int[]> lista){
		Iterator<int[]> i = lista.iterator();
		boolean rozny=true;
		String text="";
		int[] tmp={-1,-1,-1,-1};
		
		
		while(i.hasNext()){
			int[] tab = i.next();
			
			if(tab[0]==-1) continue;
			
			if((tab[0]!=tmp[0])||(tab[1]!=tmp[1])){
				tmp=tab;
				text+="\n\n<<Pionek "+tab[0]+"  "+tab[1]+" ### ";
				text+="     >>Cel "+tab[2]+"  "+tab[3]+"";
			} else {
				text+="     >>Cel "+tab[2]+"  "+tab[3]+"";
			}
			
			
			
			
		}
		JTAmoves.setText("Mozliwe ruchy\n"+text);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
