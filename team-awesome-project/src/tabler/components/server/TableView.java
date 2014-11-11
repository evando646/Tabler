package tabler.components.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TableView extends JFrame {
	
	private JPanel panelcenter;
	
	//This displays the info from the table
	public TableView()
	{
		panelcenter = new JPanel();
		panelcenter.setLayout(new GridLayout(2,4));
		add(panelcenter, BorderLayout.CENTER);
		panelcenter.setBackground(Color.CYAN);
	}
	public void registerListeners(ServerQueueController controller){
		
	}
}
