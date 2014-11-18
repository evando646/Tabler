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
	
	private JLabel tableCapacityLabel;
	private JTextField tableCapacityTextField;
	
	private JLabel guestNameLabel;
	private JTextField guestNameTextField;
	
	private JLabel guestPartySizeLabel;
	private JTextField guestParySizeTextField;
	
	private JLabel guestArrivedLabel;
	private JTextField guestArrivedTextField;
	
	private JLabel tableStateLabel;
	private JTextField tableStateTextField;
	
	private JLabel sectionLabel;
	private JTextField sectionTextField;
	
	
	public TableView( TableModel table )
	{
		panel = new JPanel();
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		tableNumLabel = new JLabel("Table Number: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.gridy = 0;
		c.gridx = 0;
		panel.add(tableNumLabel,c);
		
		tableNumTextField = new JTextField();
		tableNumTextField.setText( "" +  table.getTableNumber() );
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = IPADX_CONST;
		c.gridy = 0;
		c.gridx = 1;
		panel.add(tableNumTextField,c);
		
		tableCapacityLabel = new JLabel("Table Capacity");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.gridy = 1;
		c.gridx = 0;
		panel.add(tableCapacityLabel,c);
		
		tableCapacityTextField = new JTextField();
		tableCapacityTextField.setText( "" + table.getCapacity() );
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = IPADX_CONST;
		c.gridy = 1;
		c.gridx = 1;
		panel.add(tableCapacityTextField,c);
		
		guestNameLabel = new JLabel("Guest Name: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.gridy = 2;
		c.gridx = 0;
		panel.add(guestNameLabel,c);
		
		guestNameTextField = new JTextField();
		guestNameTextField.setText( (table.getCurrentGuest() != null) ? table.getCurrentGuest().getName() : "" );
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = IPADX_CONST;
		c.gridy = 2;
		c.gridx = 1;
		panel.add(guestNameTextField,c);
		
		guestPartySizeLabel = new JLabel("Guest Party Size: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.gridy = 3;
		c.gridx = 0;
		panel.add(guestPartySizeLabel,c);
		
		guestParySizeTextField = new JTextField();
		guestParySizeTextField.setText( "" + ( (table.getCurrentGuest() != null) ? table.getCurrentGuest().getSize() : 0 ) );
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = IPADX_CONST;
		c.gridy = 3;
		c.gridx = 1;
		panel.add(guestParySizeTextField,c);

		guestArrivedLabel = new JLabel("Guest Arrival Time: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.gridy = 4;
		c.gridx = 0;
		panel.add(guestArrivedLabel,c);
		
		guestArrivedTextField = new JTextField();
		guestArrivedTextField.setText( (table.getCurrentGuestArrived() != null) ? table.getCurrentGuestArrived().toString() : "" );
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = IPADX_CONST;
		c.gridy = 4;
		c.gridx = 1;
		panel.add(guestArrivedTextField,c);
		
		tableStateLabel = new JLabel("Table State: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.gridy = 5;
		c.gridx = 0;
		panel.add(tableStateLabel,c);
		
		tableStateTextField = new JTextField();
		tableStateTextField.setText( "" + table.getState() );
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = IPADX_CONST;
		c.gridy = 5;
		c.gridx = 1;
		panel.add(tableStateTextField,c);
		
		sectionLabel = new JLabel("Floor Section: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.gridy = 6;
		c.gridx = 0;
		panel.add(sectionLabel,c);
		
		sectionTextField = new JTextField();
		sectionTextField.setText( table.getTableSection() );
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = IPADX_CONST;
		c.gridy = 6;
		c.gridx = 1;
		panel.add(sectionTextField,c);
		
		add(panel);
	}
	
	public void register( TableController controller)
	{
		
	}
	
}
