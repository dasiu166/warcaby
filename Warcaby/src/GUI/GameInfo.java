package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JEditorPane;
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
	private JEditorPane JEPmoves;
	private JScrollPane JSPmoves;
	private JPanel hPanel;//historia
	
	public GameInfo(){
		this.setLayout(new GridLayout(1,1));
		
		JTPpanel = new JTabbedPane();
		
		//RUCHY
		mPanel = new JPanel(new BorderLayout());
		JEPmoves = new JEditorPane();
		JEPmoves.setContentType("text/html");
		JEPmoves.setText("<center>Mozliwe ruchy</center>");
		JEPmoves.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		JEPmoves.setEditable(false);
		JSPmoves = new JScrollPane(JEPmoves);
		
		JSPmoves.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		mPanel.add(JSPmoves,BorderLayout.CENTER);
		JTPpanel.addTab("Ruchy", mPanel);
		
		
		//HISTORIA
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
				text+="<br> Pionek <b>"+(tab[0]+1)+" &nbsp "+(tab[1]+1)+"</b> ### ";
				text+="&nbsp ->> Cel <b>"+(tab[2]+1)+" &nbsp "+(tab[3]+1)+"</b> ";
			} else {
				text+="&nbsp ->> Cel <b>"+(tab[2]+1)+" &nbsp "+(tab[3]+1)+"</b> ";
			}
			
			}
		
		JEPmoves.setText("<center>Mozliwe ruchy</center>"+text);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
