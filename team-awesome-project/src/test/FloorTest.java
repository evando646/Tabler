package test;

import javax.swing.JFrame;

import tabler.components.floor.FloorController;
import tabler.components.floor.FloorModel;
import tabler.components.floor.FloorView;

public class FloorTest {

	public static void main(String[] args)
	{
        FloorModel model = new FloorModel();
        FloorView view = new FloorView(model.getTableList());
        FloorController controller = new FloorController(model, view);
        
        view.register(controller);

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setSize(1024,768);
        view.setVisible(true);

	}

}
