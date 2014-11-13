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

	public TableModel (String section, int tableNum, int capacity, int x, int y){
		this.tableNumber = tableNum;
		this.capacity = capacity;
		this.section = section;
		this.positionX = x;
		this.positionY = y;
		this.currentGuest = null;
		this.currentGuestArrived = null;
		this.state = TableState.AVAILABLE;
	}
	
	public int getTableNumber() {
		return this.tableNumber;
	}
	
	/**
	 * Creates a String representation of a TableModel object
	 * 
	 * @return the table's String representation
	 */
	public String toString(){
		String objectString = null;
		
		objectString = "[TableModel: id=" + getTableNumber() + ", capacity=" +
				getCapacity() + ", section=" + getSectionName() + ", position=(" +
				getPositionX() + "," + getPositionY() + "), state=" + getState() +
				", current_guest=(";
		
		if	(getCurrentGuest() == null) { 
			objectString += "nobody party of 0), guest_arrived= n/a";
		} else {
			objectString += getCurrentGuest().getName() + "party of " +	getCurrentGuest().getSize() + 
			"), guest_arrived=" + getCurrentGuestArrived();
		}
		
		objectString += "]";
		
		return objectString;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public String getSectionName() {
		return this.section;
	}
	
	public TableState getState() {
		return this.state;
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
