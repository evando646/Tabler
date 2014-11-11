package tabler.components.server;


import java.util.ArrayList;
import java.util.Arrays;

public class SectionObject {
	/**
	 * This is the name of a section
	 */
	private String sectionName;
	
	/**
	 * This is an array of TableObjects assigned to a section
	 */
	private ArrayList<Table> tableList;
	private Table newtable;
	
	SectionObject(String SectionName, ArrayList<Integer> Tables){
		this.sectionName = SectionName;
		this.tableList = new ArrayList<>();
		for (int i = 0; i < Tables.size(); i++){
			newtable = new Table(this.sectionName, Tables.get(i));
			this.tableList.add(newtable);
		}
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
	 *This Function returns true if a section is full. False otherwise 
	 * State variable represents the current status of open or not open
	 */
	public boolean isFull(){
		int numOpen = 0;
		for(int i = 0; i< this.tableList.size(); i++){
			if(this.tableList.get(i).state.equals("open")){
				numOpen++;
			}
			else{
				continue; 
			}
		}
		if (numOpen >= 1){
			return false;
		}else {
			return true;
		}
	}
}
