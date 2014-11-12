package tabler.components.server;

import java.util.GregorianCalendar;
import java.util.Random;

import tabler.components.guest.GuestModel;

public class TableModel implements Comparable<TableModel> {
	private static enum TableState { AVAILABLE, OCCUPIED, NEEDS_SERVICING, RESERVED };
	
	private int tableNumber;
	private int capacity;
	private int positionX;
	private int positionY;
	private GuestModel currentGuest;
	private GregorianCalendar currentGuestArrived;
	private TableState state;
	private String section;

	public TableModel (String section, int tableNum, int capacity){
		this.tableNumber = tableNum;
		this.capacity = capacity;
		this.section = section;
		this.positionX = 0;
		this.positionY = 0;
		this.currentGuest = null;
		this.currentGuestArrived = null;
		this.state = TableState.AVAILABLE;
	}
	
	// TODO Consider moving to the test class for TableModel
	public String randomState(){
		Random rand = new Random();
		String [] states = {"open", "dirty", "set up", "reserved"};
		int index = rand.nextInt(4);
		return states[index];
	}
	
	// TODO Consider moving to the test class for TableModel
	public int randomSeat(){
		Random rand = new Random();
		int [] seats = {2, 4, 6, 8};
		int index = rand.nextInt(4);
		return seats[index];
	}
	
	public int getTableNumber() {
		return this.tableNumber;
	}
	
	public String toString(){
		return String.format("%d", this.capacity);
	}
	
	public boolean isReady() {
		return (state.equals(TableState.AVAILABLE));
	}

	public boolean isOccupied() {
		return !(currentGuest.equals(null));
	}
	
	public int compareTo(TableModel other) {
		if (this.tableNumber == other.getTableNumber()) {
			return 0;
		} else if (this.tableNumber < other.getTableNumber()) {
			return -1;
		} else {
			return 1;
		}
	}
	
	public int getPositionX() { 
		return this.positionX;
	}
	
	public int getPositionY() {
		return this.positionY;
	}
	
	public GuestModel getCurrentGuest() {
		return this.currentGuest;
	}
	
	public GregorianCalendar getCurrentGuestArrived() {
		return this.currentGuestArrived;
	}
	
	public TableState getTableState() {
		return this.state;
	}
	
	public String getTableSection() {
		return this.section;
	}
}
