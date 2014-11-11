package test;

import javax.swing.*;

import tabler.components.server.ServerQueueView;




public class ServerTest extends JFrame{
	
	public static void main (String[] args){


	
		ServerQueueView window = new ServerQueueView();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(700, 700);
		window.setVisible(true);
		
	}
}