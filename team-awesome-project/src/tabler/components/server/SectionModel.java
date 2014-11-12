package tabler.components.server;


import java.util.ArrayList;

public class SectionModel {
	/**
	 * This is the name of a section
	 */
	private String sectionName;
	
	/**
	 * This is an array of TableObjects assigned to a section
	 */
	private ArrayList<TableModel> tableList;

	/**
	 * Constructs a section with an empty list of tables
	 * 
	 * @param sectionName the name of the section
	 */
	SectionModel(String sectionName) {
		this.sectionName = sectionName;
		tableList = new ArrayList<TableModel>();
	}
	
	/**
	 * Constructs a section with a set of tables
	 * 
	 * @param sectionName the name of the section
	 * @param tables a list of unique tables to add to the section
	 */
	SectionModel(String sectionName, ArrayList<TableModel> tables){
		this.sectionName = sectionName;
		this.tableList = tables;
	}
	
	public void addTable(TableModel newTable) {
		for (TableModel table : tableList) {
			if (table.equals(newTable)) {
				System.err.println("Cannot add table to section " + 
						"because it already exists in the section");
				
				// throw new InvalidTableExample (maybe)
			}
		}
		
		this.tableList.add(newTable);
	}
	
	public TableModel removeTable(int tableNumber) {
		for (TableModel table : tableList) {
			if (table.getTableNumber() == tableNumber) {
				// the remove() method should return a reference to the
				// element that was removed
				return tableList.remove(tableList.indexOf(table));
			}
		}
		
		return null;
	}
	
	/**
	 * table.time represents the updated time the table was last seated 
	 * Function returns the earliest time per section that a table 
	 * was assigned
 	 */
	public double TimeLastSeated(){
		double lastDate = this.tableList.get(0).time;
		for (int i = 1; i < this.tableList.size(); i++){
			if (this.tableList.get(i).time > lastDate){
				lastDate = this.tableList.get(i).time;
			}
			else{
				continue;
			}
		}
		return lastDate;
	}
	/**
	 * This Function returns true if a section is full. False otherwise 
	 * 
	 */
	public boolean isFull(){
		int numOccupied = 0;
		
		for (TableModel table : tableList) {
			if (table.isOccupied()) {
				numOccupied++;
			}
		}
		
		return (numOccupied == tableList.size());
	}
	
	/**
	 * Searches a section for tables that are ready for guests
	 *
	 * @return a list of available tables
	 */
	public ArrayList<TableModel> getAvailableTables() {
		ArrayList<TableModel> availableTables = new ArrayList<TableModel>();
		
		for (TableModel table : tableList) {
			if (table.isReady()) {
				availableTables.add(table);
			}
		}

		return availableTables;
	}
	
	public String getSectionName() {
		return this.sectionName;
	}
	
	public ArrayList<TableModel> getTableList() {
		return this.tableList;
	}
}
