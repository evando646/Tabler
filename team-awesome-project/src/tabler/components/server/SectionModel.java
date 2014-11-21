package tabler.components.server;


import java.util.ArrayList;
import java.util.GregorianCalendar;
import tabler.components.table.*;

/**
 * The SectionModel represents a collection of tables that can be assigned
 * to a server.
 * 
 * @author Kristin (krismndz)
 * @author Augustine (mr-augustine)
 *
 */
public class SectionModel {
	/**
	 * This is the name of a section
	 */
	public String sectionName;
	
	/**
	 * This is an array of TableObjects assigned to a section
	 */
	public ArrayList<TableModel> tableList;

	/**
	 * Constructs a section with an empty list of tables
	 * 
	 * @param sectionName the name of the section
	 */
	public SectionModel(String sectionName) {
		this.sectionName = sectionName;
		tableList = new ArrayList<TableModel>();
	}
	
	/**
	 * Constructs a section with a set of tables
	 * 
	 * @param sectionName the name of the section
	 * @param tables a list of unique tables to add to the section
	 */
	public SectionModel(String sectionName, ArrayList<TableModel> tables){
		this.sectionName = sectionName;
		this.tableList = tables;
	}
	
	/**
	 * Adds a table to a section
	 * 
	 * @param newTable a table to be added to the section
	 */
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
	 * Identifies the time at which the most recent guest was seated at a 
	 * table in a section.
     *
     * @return the time that the most recent guest was seated in the section
 	 */
	public GregorianCalendar timeLastSeated(){
		GregorianCalendar mostRecentSeatedTime = null;
		
		if (tableList.size() > 0) {
			mostRecentSeatedTime = tableList.get(0).getCurrentGuestArrived();
		}
		
		for (TableModel table : tableList) {
			if (mostRecentSeatedTime.compareTo(table.getCurrentGuestArrived()) == -1) {
				mostRecentSeatedTime = table.getCurrentGuestArrived();
			}
		}
		
		return mostRecentSeatedTime;
	}
	
	/**
	 * This Function returns true if a section is full. False otherwise 
	 * 
	 * @return true if the section is full; false otherwise
	 */
	public boolean isFull() {
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
	public ArrayList<TableModel> availableTables() {
		ArrayList<TableModel> availableTables = new ArrayList<TableModel>();
		
		for (TableModel table : tableList) {
			if (table.isReady()) {
				availableTables.add(table);
			}
		}

		return availableTables;
	}
	
	/**
	 * Accessor function for the section name
	 * 
	 * @return the section name
	 */
	public String getSectionName() {
		return this.sectionName;
	}
	
	/**
	 * Accessor function for the table list
	 * 
	 * @return a list of all tables in the section
	 */
	public ArrayList<TableModel> getTableList() {
		return this.tableList;
	}
	
	/**
	 * Creates a String representation of a SectionModel object
	 * 
	 * @return the section's String representation
	 */
	public String toString() {
		String objectString = null;
		
		objectString = "[SectionModel: name=" + getSectionName() + ", tables={";
		
		for (int i = 0; i < tableList.size(); i++) {
			objectString += tableList.get(i).getTableNumber();
			
			if (i < tableList.size() - 1) {
				objectString += ", ";
			}
		}

		objectString += "}]";
		
		return objectString;
	}
}
