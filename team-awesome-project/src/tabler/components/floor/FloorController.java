package tabler.components.floor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		// TODO Auto-generated method stub
		
	}

}
