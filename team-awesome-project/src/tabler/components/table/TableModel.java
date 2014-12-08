package tabler.components.table;

import java.awt.Color;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JButton;

import tabler.components.guest.GuestModel;

public class TableModel implements Comparable<TableModel> {
	public static Color availableBg = Color.cyan;
	public static Color occupiedBg = Color.red;
	
	public static enum TableState { AVAILABLE, OCCUPIED, NEEDS_SERVICING, RESERVED };
	
	private int tableNumber;
	private int capacity;
	private int positionX;
	private int positionY;
	private int width;
	private int height;
	private GuestModel currentGuest;
	private GregorianCalendar currentGuestArrived;
	private TableState state;
	private String section;
	private JButton button;

	public TableModel (String section, int tableNum, int capacity, int x, int y, int width, int height){
		this.tableNumber = tableNum;
		this.capacity = capacity;
		this.section = section;
		this.positionX = x;
		this.positionY = y;
		this.width = width;
		this.height = height;
		this.currentGuest = null;
		this.currentGuestArrived = null;
		this.state = TableState.AVAILABLE;
	}
	
	public TableModel()
	{
		//Empty Constructor for testing purposes
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
	/**
	 * Adds new guest to table and changes the table state to occupied - kristin
	 * @param newGuest
	 */
	public void assignGuest(GuestModel newGuest) {
		if (!newGuest.equals(null)) {
			currentGuest = newGuest;
			currentGuestArrived = new GregorianCalendar();
			System.out.printf("Successfully assigned\n");
			state = TableState.OCCUPIED;
			button.setBackground(occupiedBg);
		}
	}
	
	public GuestModel removeGuest() {
		GuestModel removedGuest = currentGuest;
		
		currentGuest = null;
		currentGuestArrived = null;
		
		this.setTableState(TableState.NEEDS_SERVICING);
		
		button.setBackground(availableBg);
		
		return removedGuest;
	}
	
	public void setSection(String sectionName) {
		this.section = sectionName;
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
		return !(currentGuest == null);
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
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public GuestModel getCurrentGuest() {
		return this.currentGuest;
	}
	
	public GregorianCalendar getCurrentGuestArrived() {
		return this.currentGuestArrived;
	}
	
//	public String getCurrentGuestArrived(){
//		if(currentGuestArrived!=null){
//			int hour=currentGuestArrived.get(Calendar.HOUR_OF_DAY);
//			int min=currentGuestArrived.get(Calendar.MINUTE);
//			if(min<10){
//				return (hour+":0"+min);
//			}
//			else{
//				return(hour+":"+min);
//			}
//		}
//		else{
//			return null;
//		}
//	}
	
	public TableState getTableState() {
		return this.state;
	}
	
	public void setTableState(TableState state) {
		if (state != null) {
			this.state = state;
		}
	}
	
	public String getTableSection() {
		return this.section;
	}
	
	public void setButton( JButton button )
	{
		this.button = button;
	}
}
