package tabler.components.floor;

import java.util.ArrayList;

import tabler.components.table.*;

/**
 * Floor Model
 * 
 * @author frg169
 *
 */
public class FloorModel {
	
	private ArrayList<TableModel> tableList;

	/**
	 * Constructor
	 * 
	 * @param list
	 */
	public FloorModel(ArrayList<TableModel> list)
	{
		this.tableList = list;
	}

	public ArrayList<TableModel> getTableList() {
		return tableList;
	}

	public void setTableList(ArrayList<TableModel> tableList) {
		this.tableList = tableList;
	}
}
