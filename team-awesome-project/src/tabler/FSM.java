package tabler;

import javax.swing.JFrame;

import tabler.components.guest.*;
import tabler.components.server.*;
import tabler.components.table.*;

public class FSM {
	
	public enum FSM_STATE { START, TABLE, GUEST, SERVER };
	
	private FSM_STATE prevState;
	
	public static FSM _instance = null;
	
	private GuestModel guest;
	private TableModel table;
	private ServerModel server;
	
	
	public FSM()
	{
		prevState = FSM_STATE.START;
		
		if( _instance == null )
		{
			_instance = this;
		}
	}
	
	public void Action( FSM_STATE state , Object ref )
	{
		switch(state)
		{
		case TABLE:
			table = (TableModel)ref;
			TableView view = new TableView(table);
	        TableController controller = new TableController(table, view);
	        
	        view.register(controller);
	
	        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        view.setSize(400,300);
	        view.setVisible(true);
			prevState = FSM_STATE.TABLE;
			break;
		case GUEST:
			if (prevState == FSM_STATE.TABLE)
			{
				table.assignGuest( (GuestModel)ref );
			}
			break;
		case SERVER:
			
			break;
		}
	}

}
