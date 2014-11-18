package test;

import java.io.File;
import java.util.Scanner;

import javax.swing.JFrame;

import tabler.components.table.*;

public class TableTest {
	
	public static void main( String[] args )
	{
		final String TABLE_PATH = "./src/test/tables.txt";
		
		try
		{
			Scanner scan = new Scanner( new File(TABLE_PATH) );
			scan.nextLine();//Skipping info line
			String line = scan.nextLine();
			scan.close();
			
			Scanner scanLine = new Scanner(line);
			
			TableModel table = new TableModel("Unknown Section", scanLine.nextInt(), scanLine.nextInt(), 
							scanLine.nextInt(), scanLine.nextInt(), 
							scanLine.nextInt(), scanLine.nextInt() );
			scanLine.close();
		
	        TableModel model = new TableModel();
	        TableView view = new TableView(table);
	        TableController controller = new TableController(model, view);
	        
	        view.register(controller);
	
	        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        view.setSize(400,300);
	        view.setVisible(true);
		}
		catch ( Exception e )
		{
			e.printStackTrace();
		}
	}

}
