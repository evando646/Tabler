package tabler;

import javax.swing.JFrame;

import tabler.components.guest.*;
import tabler.components.server.*;
import tabler.components.table.*;

public class FSM {
	
	public enum FSM_STATE { START, TABLE, GUEST, SERVER, ADD_GUEST, REMOVE_GUEST };
	
	private FSM_STATE prevState = FSM_STATE.START;
	private FSM_STATE curState = FSM_STATE.START;
	
	public static FSM _instance = null;
	
	private GuestModel curGuest = null;
	private GuestModel prevGuest = null;
	private TableModel curTable = null;
	private TableModel prevTable = null;
	private ServerModel curServer = null;
	private ServerModel prevServer = null;
	
	private MainPanel mainPanelRef = null;
	
	public FSM(MainPanel panel)
	{
		prevState = FSM_STATE.START;
		
		if( _instance == null )
		{
			_instance = this;
			mainPanelRef = panel;
		}
	}
	
	@SuppressWarnings("incomplete-switch")
	public void Action( FSM_STATE state , Object ref )
	{
		prevState = curState;
		curState = state;
		System.out.println("curState: "+curState+" prevState: "+prevState);
		
		switch(state)
		{
		case TABLE:
			prevTable = curTable;
			curTable = (TableModel)ref;
			
			if( prevState == FSM_STATE.START /*|| prevState == FSM_STATE.TABLE*/ )
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
				System.out.printf("Assinging %s to %s\n", curGuest.toString(), curTable.toString());
				curTable.assignGuest(curGuest);
				mainPanelRef.getWaitlistModel().removeGuest(curGuest);
				mainPanelRef.getWaitlistView().updateView(mainPanelRef.getWaitlistModel());
				mainPanelRef.getWaitlistView().registerListener(mainPanelRef.getWaitlistController());
				
				
				curGuest = null;
				curTable = null;
				prevGuest=null;
				prevTable=null;
			}
			
			//prevState = curState;
			prevState=FSM_STATE.START;
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
		case ADD_GUEST:
			mainPanelRef.getWaitlistController().addGuest((GuestModel)ref);
			prevState=FSM_STATE.START;
			curState = FSM_STATE.START;
			break;
		case REMOVE_GUEST:
			mainPanelRef.getWaitlistController().removeGuest(((GuestView)ref).getModel());
			prevGuest=null;
			curGuest=null;
			prevTable=null;
			curTable=null;
			prevState=FSM_STATE.START;
			curState = FSM_STATE.START;
			break;
		}
	}
}
