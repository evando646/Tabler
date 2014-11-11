package tabler.components.server;

import java.util.Date;
import java.util.Random;

public class Table {
	Date date;
	double time;
	int tablenum;
	String sectionName;
	int seats;
	
	public String state;

	public Table (String section, int table){
		this.sectionName = section;
		this.tablenum = table;
		this.date = new Date();
		this.time =  date.getTime();
		this.state = randomState();
		this.seats = randomSeat();

		
	}
	public String randomState(){
		Random rand = new Random();
		String [] states = {"open", "dirty", "set up", "reserved"};
		int index = rand.nextInt(4);
		return states[index];
	}
	public int randomSeat(){
		Random rand = new Random();
		int [] seats = {2, 4, 6, 8};
		int index = rand.nextInt(4);
		return seats[index];
	}
	
	public String toString(){
		return String.format("%d", this.seats);
	}
	
}
