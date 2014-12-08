package tabler.components.floor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import tabler.FSM;
import tabler.FSM.FSM_STATE;
import tabler.components.server.*;
import tabler.components.table.TableModel;
import tabler.components.table.TableModel.TableState;
import tabler.components.server.*;
public class FloorView extends JPanel{
	
	private static final int SCALE = 5;
	
	private Border oldBorder = null;
	
	private JPanel panel;
	private ArrayList<JButton> tableButtons;
	
	public FloorView(ArrayList<TableModel> tableList)
	{
		tableButtons = new ArrayList<JButton>();
		
		//panel = new JPanel();
		
		this.setLayout(null);
		
		Insets insets = this.getInsets();
		
		for( final TableModel table : tableList )
		{
			JButton b = new JButton( "" + table.getTableNumber() );
			table.setButton(b);
			if( table.getState() == TableState.AVAILABLE )
			{
				b.setBackground(Color.CYAN);
			}
			tableButtons.add(b);
			this.add(b);
			b.setBounds(table.getPositionX() * SCALE + insets.left, table.getPositionY() * SCALE + insets.top, 
					table.getWidth() * SCALE , table.getHeight() * SCALE);
			
			b.addMouseListener( new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					if ( SwingUtilities.isRightMouseButton(e) )
					{
						FSM._instance.Action(FSM_STATE.VIEW_TABLE, table );
					}
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
		}

	}
	
	/**
	 * editBorders() method results in attempted creation or removal 
	 * of borders around a set of table buttons
	 * @param ArrayList<TableModel> TablesList: desired tables to edit
	 * @param String S: instruction
	 * @author Kristin
	 */
	public void editBorders(ArrayList<TableModel> TablesList, String S){
		Border border;
		JPanel contentPane = (JPanel) this.getRootPane().getContentPane();
		if (S.equals("hide")){
			border =  oldBorder;
		}
		else{
			border = new LineBorder(Color.black, 7);
		}
		int bsize = this.tableButtons.size();
		int lsize = TablesList.size();
		for(int i = 0; i < bsize; i ++){
			for (int j = 0; j < lsize; j++){
				if (this.tableButtons.get(i).getText().equals(Integer.toString(TablesList.get(j).getTableNumber()))){
					if ( oldBorder == null )
					{
						oldBorder = tableButtons.get(i).getBorder();
					}
					tableButtons.get(i).setBorder(border);
				}
			}
		}
		contentPane.repaint();
	}
	/**
	 * Reinstated register method for floor buttons for testing
	 * @param FloorController controller
	 * @author Kristin
	 */
	public void register (FloorController controller)
	{
		for( JButton btn : tableButtons )
		{
			btn.addActionListener(controller);
		}
	}
	/**
	 * Reinstated register method of floor buttons for ServerQueueController
	 * @param ServerQueueController controller
	 */
	public void register (ServerQueueController controller)
	{
		for( JButton btn : tableButtons )
		{
			btn.addActionListener(controller);
		}
	}

	/**
	 * Method gets and returns all table buttons
	 * @return ArrayList<JButton> 
	 */
	public ArrayList<JButton> getTableButtons(){
		return tableButtons;
	}
}
