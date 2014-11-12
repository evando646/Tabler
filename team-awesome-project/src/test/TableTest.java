package test;

import javax.swing.JFrame;

import tabler.components.table.*;

public class TableTest {
	
	public static void main( String[] args )
	{
        TableModel model = new TableModel();
        TableView view = new TableView();
        TableController controller = new TableController(model, view);
        
        view.register(controller);

        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setSize(400,300);
        view.setVisible(true);
	}

}
