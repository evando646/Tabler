package tabler.components.server;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WaitlistView extends JFrame {
	
	private JPanel panelwest;
	
	//This displays the info from the table
	public WaitlistView()
	{
		panelwest = new JPanel();
		panelwest.setLayout(new GridLayout(2,4));
		
		add(panelwest, BorderLayout.WEST);
		panelwest.setBackground(Color.CYAN);
	}
	public void registerListeners(ServerController controller){
		
	}
}
