package tabler.components.floor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

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
				TableView view = new TableView(table);
		        TableController controller = new TableController(table, view);
		        
		        view.register(controller);
		
		        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        view.setSize(400,300);
		        view.setVisible(true);
			}
		}
		System.out.println(event.getActionCommand());
	}

}
