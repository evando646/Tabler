package tabler.components.floor;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Panel;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import tabler.components.server.*;
import tabler.components.table.TableModel;

public class FloorView extends JPanel{
	
	private static final int SCALE = 5;
	
	//private JPanel panel;
	private ArrayList<JButton> tableButtons;
	
	public FloorView(ArrayList<TableModel> tableList)
	{
		tableButtons = new ArrayList<JButton>();
		
		this.setLayout(null);
		this.setPreferredSize(new Dimension(800,600));
		
		Insets insets = this.getInsets();
		
		for( TableModel table : tableList )
		{
			JButton b = new JButton( "" + table.getTableNumber() );
			tableButtons.add(b);
			this.add(b);
			b.setBounds(table.getPositionX() * SCALE + insets.left, table.getPositionY() * SCALE + insets.top, 
					table.getWidth() * SCALE , table.getHeight() * SCALE);
		}
		
	}
	
	
	public void register (FloorController controller)
	{
		for( JButton btn : tableButtons )
		{
			btn.addActionListener(controller);
		}
	}
	public void register (ServerQueueController controller)
	{
		for( JButton btn : tableButtons )
		{
			btn.addActionListener(controller);
		}
	}


	public ArrayList<JButton> getTableButtons() {
		return tableButtons;
	}


	public void setTableButtons(ArrayList<JButton> tableButtons) {
		this.tableButtons = tableButtons;
	}
}
