package tabler.components.floor;

import java.awt.Insets;
import java.awt.Panel;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import tabler.components.table.TableModel;
import tabler.components.server.*;
public class FloorView extends JFrame{
	
	private static final int SCALE = 5;
	
	private JPanel panel;
	private ArrayList<JButton> tableButtons;
	
	public FloorView(ArrayList<TableModel> tableList)
	{
		panel = new JPanel();
		
		panel.setLayout(null);
		
		Insets insets = panel.getInsets();
		
		for( TableModel table : tableList )
		{
			JButton b = new JButton( "" + table.getTableNumber() );
			panel.add(b);
			b.setBounds(table.getPositionX() * SCALE + insets.left, table.getPositionY() * SCALE + insets.top, 
					table.getWidth() * SCALE , table.getHeight() * SCALE);
		}
		
		add(panel);
	}
	
	
	public void register (ServerQueueController controller)
	{
		
	}

}
