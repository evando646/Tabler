package tabler.components.server;

import java.util.Date;
import java.util.Random;

public class Table {
	Date date;
	double time;
	int tablenum;
	String sectionName;
	
	public String state;

	public Table (String section, int table){
		this.sectionName = section;
		this.tablenum = table;
		this.date = new Date();
		this.time =  date.getTime();
		this.state = randomState();

		
	}
	public String randomState(){
		Random rand = new Random();
		String [] states = {"open", "dirty", "set up", "reserved"};
		int index = rand.nextInt(4);
		return states[index];
	}
	
}
