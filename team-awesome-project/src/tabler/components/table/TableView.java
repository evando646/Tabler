package tabler.components.table;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TableView extends JPanel {
	
	private final int IPADX_CONST = 200;
	
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
	
	private JButton clearTableBtn;
	
	private JButton close;
	
	private TableModel table;
	
	
	public TableView( TableModel table )
	{	
		this.table = table;
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		tableNumLabel = new JLabel("Table Number: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.gridy = 0;
		c.gridx = 0;
		this.add(tableNumLabel,c);
		
		tableNumTextField = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = IPADX_CONST;
		c.gridy = 0;
		c.gridx = 1;
		this.add(tableNumTextField,c);
		
		tableCapacityLabel = new JLabel("Table Capacity");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.gridy = 1;
		c.gridx = 0;
		this.add(tableCapacityLabel,c);
		
		tableCapacityTextField = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = IPADX_CONST;
		c.gridy = 1;
		c.gridx = 1;
		this.add(tableCapacityTextField,c);
		
		guestNameLabel = new JLabel("Guest Name: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.gridy = 2;
		c.gridx = 0;
		this.add(guestNameLabel,c);
		
		guestNameTextField = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = IPADX_CONST;
		c.gridy = 2;
		c.gridx = 1;
		this.add(guestNameTextField,c);
		
		guestPartySizeLabel = new JLabel("Guest Party Size: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.gridy = 3;
		c.gridx = 0;
		this.add(guestPartySizeLabel,c);
		
		guestParySizeTextField = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = IPADX_CONST;
		c.gridy = 3;
		c.gridx = 1;
		this.add(guestParySizeTextField,c);

		guestArrivedLabel = new JLabel("Guest Arrival Time: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.gridy = 4;
		c.gridx = 0;
		this.add(guestArrivedLabel,c);
		
		guestArrivedTextField = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = IPADX_CONST;
		c.gridy = 4;
		c.gridx = 1;
		this.add(guestArrivedTextField,c);
		
		tableStateLabel = new JLabel("Table State: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.gridy = 5;
		c.gridx = 0;
		this.add(tableStateLabel,c);
		
		tableStateTextField = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = IPADX_CONST;
		c.gridy = 5;
		c.gridx = 1;
		this.add(tableStateTextField,c);
		
		sectionLabel = new JLabel("Floor Section: ");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.gridy = 6;
		c.gridx = 0;
		this.add(sectionLabel,c);
		
		sectionTextField = new JTextField();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = IPADX_CONST;
		c.gridy = 6;
		c.gridx = 1;
		this.add(sectionTextField,c);
		
		clearTableBtn = new JButton("Clear Table");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.gridy = 7;
		c.gridx = 0;
		this.add(clearTableBtn,c);
		
		close = new JButton("Close");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.gridy = 8;
		c.gridx = 0;
		this.add(close,c);
		
		updateInfo();
	}
	
	public void updateInfo()
	{
		tableNumTextField.setText( "" +  table.getTableNumber() );
		tableCapacityTextField.setText( "" + table.getCapacity() );
		guestNameTextField.setText( (table.getCurrentGuest() != null) ? table.getCurrentGuest().getName() : "" );
		guestParySizeTextField.setText( "" + ( (table.getCurrentGuest() != null) ? table.getCurrentGuest().getSize() : 0 ) );
		String text = "";
		if( table.getCurrentGuestArrived() != null )
		{
			int hour=table.getCurrentGuestArrived().get(Calendar.HOUR_OF_DAY);
			int min=table.getCurrentGuestArrived().get(Calendar.MINUTE);
			if(min<10){
				text =+ hour+":0"+min;
			}
			else{
				text =+ hour+":"+min;
			}
		}
		guestArrivedTextField.setText( text );
		tableStateTextField.setText( "" + table.getState() );
		sectionTextField.setText( table.getTableSection() );
		
		this.updateUI();
	}
	
	public void register( TableController controller)
	{
		clearTableBtn.addActionListener(controller);
		close.addActionListener(controller);
	}
	
}
