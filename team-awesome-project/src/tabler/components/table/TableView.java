package tabler.components.table;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TableView extends JFrame {
	
	private JPanel panel;
	
	//This displays the info from the table
	public TableView()
	{
		panel = new JPanel();
		panel.setLayout(new GridLayout(2,4));
	}
	
}
