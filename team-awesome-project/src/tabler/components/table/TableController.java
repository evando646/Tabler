package tabler.components.table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tabler.FSM;
import tabler.FSM.FSM_STATE;

public class TableController implements ActionListener{
	
	private TableModel model;
	private TableView view;
	
	public TableController( TableModel model, TableView view )
	{
		this.model = model;
		this.view = view;
	}

	@Override
	/**
	 * when the clear button is pressed it will remove the guest from the table
	 * and clear the view
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command=e.getActionCommand();
		
		if(command.equals("Clear Table")){
			model.removeGuest();
			view.updateInfo();
		}
		else if(command.equals("Close"))
		{
			FSM._instance.Action(FSM_STATE.VIEW_FLOOR, null);
		}
	}
	
}
