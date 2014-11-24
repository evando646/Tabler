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
		
		new FSM(); //Creating the program fsm
		
		JFrame mainFrame = new JFrame();
		mainFrame.add(new MainPanel());
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(1024,768);
		mainFrame.setVisible(true);
		
	}

}
