package tabler;

import tabler.components.guest.*;
import tabler.components.server.*;
import tabler.components.table.*;

public class FSM {
	
	public enum FSM_STATE { TABLE, GUEST, SERVER };
	
	public static FSM _instance = null;
	
	private GuestModel guest;
	private TableModel table;
	private ServerModel server;
	
	
	public FSM()
	{
		if( _instance == null )
		{
			_instance = this;
		}
	}
	
	public static void Action( FSM_STATE state , Object ref )
	{
		switch(state)
		{
		case TABLE:
			break;
		case GUEST:
			break;
		case SERVER:
			break;
		}
	}

}
