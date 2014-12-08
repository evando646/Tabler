package tabler;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;

import tabler.components.guest.*;
import tabler.components.server.*;
import tabler.components.table.*;
import tabler.components.floor.*;

public class FSM {
	
	public enum FSM_STATE { START, TABLE, GUEST, SERVER, ADD_GUEST, REMOVE_GUEST, VIEW_TABLE, VIEW_FLOOR };
	
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
		System.out.println("curState: "+curState+" prevState: "+prevState + "\nRef Obj: " + ref);
		
		switch(state)
		{	
		case VIEW_TABLE:
			prevTable = curTable;
			curTable = (TableModel)ref;
			
			Component[] comps1 = mainPanelRef.getComponents();
			
			for( Component c : comps1 )
			{
				if( c instanceof FloorView )
				{
					mainPanelRef.remove(c);
				}
			}
			TableView view = new TableView(curTable);
	        TableController controller = new TableController(curTable, view);
	        
	        view.register(controller);
	
	        mainPanelRef.add(view, BorderLayout.WEST);
	        mainPanelRef.updateUI();
			break;
		case VIEW_FLOOR:
			Component[] comps2 = mainPanelRef.getComponents();
			
			for( Component c : comps2 )
			{
				if( c instanceof TableView )
				{
					mainPanelRef.remove(c);
				}
			}
			mainPanelRef.add(mainPanelRef.getFloorView(), BorderLayout.WEST);
			mainPanelRef.updateUI();
			break;
		case TABLE:
			prevTable = curTable;
			curTable = (TableModel)ref;
			
			if( curGuest == null )
			{
				int confirm = mainPanelRef.getQview().showOptions("table " + ((TableModel)ref).getTableNumber());
				
				if( confirm == 0 )
				{
					((TableModel)ref).assignGuest(mainPanelRef.getWaitlistController().getNextGuest());
					mainPanelRef.getFloorView().updateUI();
				}
				mainPanelRef.getFloorView().editBorders(mainPanelRef.getFloorModel().getTableList(), "hide");
			}
			else
			{
				System.out.println("guest selected and clicked on table");
				//Assign guest to curTable;
				//This is an ending case
				if( !curTable.isOccupied() )
				{
					System.out.printf("Assinging %s to %s\n", curGuest.toString(), curTable.toString());
					curTable.assignGuest(curGuest);
					mainPanelRef.getWaitlistModel().removeGuest(curGuest);
					mainPanelRef.getWaitlistView().updateView(mainPanelRef.getWaitlistModel());
					mainPanelRef.getWaitlistView().registerListener(mainPanelRef.getWaitlistController());
				}
				else
				{
					System.out.println("Table is occupied");
				}
				mainPanelRef.getFloorView().editBorders(mainPanelRef.getFloorModel().getTableList(), "hide");
			}
			
			curGuest = null;
			curTable = null;
			prevGuest=null;
			prevTable=null;
			
			//prevState = curState;
			prevState=FSM_STATE.START;
			curState = FSM_STATE.START;
			
			break;
		case GUEST:
			prevGuest = curGuest;
			curGuest = (GuestModel)ref;
			
			if( curGuest == prevGuest )
			{
				curGuest.getNameButton().setBackground(GuestView.defaultBG);
				curGuest = null;
			}
			else
			{
				if(prevGuest != null)
				{
				prevGuest.getNameButton().setBackground(GuestView.defaultBG);
				}
				curGuest.getNameButton().setBackground(GuestView.selectedBG);
			}
			System.out.println("curGuest: " + curGuest);
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
