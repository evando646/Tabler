package tabler.components.table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableController implements ActionListener{
	
	private TableModel model;
	private TableView view;
	
	public TableController( TableModel model, TableView view )
	{
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
