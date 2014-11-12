package tabler.components.table;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TableView extends JFrame {
	
	private JPanel panel;
	
	public TableView()
	{
		panel = new JPanel();
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		JLabel tableNumLabel = new JLabel("Table Number: ");
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		
		panel.add(tableNumLabel);
		
		JTextField tableNumTextField = new JTextField();
		
		c.gridx = 1;
		
		panel.add(tableNumTextField);
		
		JLabel guestNameLabel = new JLabel("Guest Name: ");
		
		c.gridx = 0;
		c.gridy = 1;
		
		panel.add(guestNameLabel);
		
		add(panel);
	}
	
	public void register( TableController controller)
	{
		
	}
	
}
