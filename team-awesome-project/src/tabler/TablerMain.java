package tabler;

import javax.swing.JFrame;

import tabler.FSM;
import tabler.components.floor.FloorController;
import tabler.components.floor.FloorModel;
import tabler.components.floor.FloorView;

public class TablerMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//COMMIT TEST -kris
		
		System.out.println("This is tabler main");
		
		new FSM();
		
        FloorModel model = new FloorModel();
        FloorView view = new FloorView(model.getTableList());
        FloorController controller = new FloorController(model, view);
        
        view.register(controller);

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setSize(1024,768);
        view.setVisible(true);
	}

}
