package tabler.components.floor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import tabler.components.table.*;

/**
 * Floor Model
 * 
 * @author frg169
 *
 */
public class FloorModel {
	
	private static final String TABLE_PATH = "./src/test/tables.txt";
	
	private ArrayList<TableModel> tableList;

	/**
	 * Constructor
	 * 
	 * @param list
	 */
	
	public FloorModel (ArrayList<TableModel>tables){
		this.tableList = tables;
	}
	public FloorModel()
	{
		tableList = new ArrayList<TableModel>();
		
		try
		{
			Scanner scan = new Scanner( new File(TABLE_PATH));
			scan.nextLine();//Skipping info line
		
			while (scan.hasNext())
			{
				String record = scan.nextLine();
				Scanner scanLine = new Scanner(record);
				
				//public TableModel (String section, int tableNum, int capacity, int x, int y, int width, int height){
				tableList.add(
						new TableModel("Unknown Section", scanLine.nextInt(), scanLine.nextInt(), 
								scanLine.nextInt(), scanLine.nextInt(), 
								scanLine.nextInt(), scanLine.nextInt() ));
			}
			scan.close();
		}
		catch( FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public ArrayList<TableModel> getTableList() {
		return tableList;
	}

	public void setTableList(ArrayList<TableModel> tableList) {
		this.tableList = tableList;
	}
}
