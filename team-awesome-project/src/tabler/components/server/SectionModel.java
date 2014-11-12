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
	
	public ArrayList<String> tableSeatAvailable(){
		ArrayList<String> returnOpen = new ArrayList<>();
		for (int i = 0; i < this.tableList.size(); i ++){
			if (this.tableList.get(i).state.equals("open")){
				returnOpen.add(tableList.get(i).toString());
			}
		}
		return returnOpen;
	}
	
	public String getSectionName() {
		return this.sectionName;
	}
	
	public ArrayList<TableModel> getTableList() {
		return this.tableList;
	}
}
