package tabler;

import javax.swing.JFrame;

import tabler.components.guest.*;
import tabler.components.server.*;
import tabler.components.table.*;

public class FSM {
	
	public enum FSM_STATE { START, TABLE, GUEST, SERVER };
	
	private FSM_STATE prevState = null;
	private FSM_STATE curState = null;
	
	public static FSM _instance = null;
	
	private GuestModel curGuest = null;
	private GuestModel prevGuest = null;
	private TableModel curTable = null;
	private TableModel prevTable = null;
	private ServerModel curServer = null;
	private ServerModel prevServer = null;
	
	
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
		prevState = curState;
		curState = state;
		
		switch(state)
		{
		case TABLE:
			prevTable = curTable;
			curTable = (TableModel)ref;
			
			if( prevState == FSM_STATE.START || prevState == FSM_STATE.TABLE )
			{
				TableView view = new TableView(curTable);
		        TableController controller = new TableController(curTable, view);
		        
		        view.register(controller);
		
		        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        view.setSize(400,300);
		        view.setVisible(true);
			}
			else if ( prevState == FSM_STATE.GUEST )
			{
				//Assign guest to curTable;
				//This is an ending case
			}
			
			prevState = curState;
			curState = FSM_STATE.START;
			
			break;
		case GUEST:
			prevGuest = curGuest;
			curGuest = (GuestModel)ref;
			
			if (prevState == FSM_STATE.TABLE)
			{
				curTable.assignGuest( (GuestModel)ref );
			}
			
			break;
		case SERVER:
			prevServer = curServer;
			curServer = (ServerModel)ref;
			
			break;
		}
	}

}