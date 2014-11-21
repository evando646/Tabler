package tabler.components.floor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import tabler.FSM;
import tabler.components.table.TableController;
import tabler.components.table.TableModel;
import tabler.components.table.TableView;

public class FloorController implements ActionListener{
	
	private FloorModel model;
	private FloorView view;
	
	public FloorController( FloorModel model, FloorView view )
	{
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		for( TableModel table : model.getTableList())
		{
			if( table.getTableNumber() == Integer.parseInt(event.getActionCommand()) )
			{
				FSM._instance.Action(FSM.FSM_STATE.TABLE, table);
			}
		}
		System.out.println(event.getActionCommand());
	}

}
