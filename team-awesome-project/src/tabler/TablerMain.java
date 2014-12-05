package tabler;

import javax.swing.JFrame;

import tabler.FSM;

public class TablerMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//COMMIT TEST -kris
		
		System.out.println("This is tabler main");
				
		MainPanel mainPanel = new MainPanel();
		
		JFrame mainFrame = new JFrame();
		mainFrame.add(mainPanel);
		
		new FSM(mainPanel); //Creating the program fsm
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(1024,768);
		mainFrame.setVisible(true);
		
	}

}
