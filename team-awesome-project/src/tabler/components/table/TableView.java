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
	
	private final int IPADX_CONST = 200;
	
	private JPanel panel;
	
	private JLabel tableNumLabel;
	private JTextField tableNumTextField;
	private JLabel guestNameLabel;
	private JTextField guestTextField;
	
	
	public TableView()
	{
		panel = new JPanel();
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		tableNumLabel = new JLabel("Table Number: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(tableNumLabel,c);
		
		tableNumTextField = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = IPADX_CONST;
		c.gridx = 1;
		c.gridy = 0;
		panel.add(tableNumTextField,c);
		
		guestNameLabel = new JLabel("Guest Name: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.gridx = 0;
		c.gridy = 1;
		panel.add(guestNameLabel,c);
		
		guestTextField = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = IPADX_CONST;
		c.gridx = 1;
		c.gridy = 1;
		panel.add(guestTextField,c);
		
		add(panel);
	}
	
	public void register( TableController controller)
	{
		
	}
	
}
