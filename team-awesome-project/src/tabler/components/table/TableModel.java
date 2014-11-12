package tabler.components.table;

import javax.swing.JButton;

/**
 * TableModel Model
 * 
 * @author frg169
 *
 */
public class TableModel {
	
	public enum TableState { AVAILABLE, OCCUPIDE, DIRTY };
	
	private int tableNumber;
	
	private TableState state;

	private String guestName;
	private int size;
	
	private int x;
	private int y;

	
	/**
	 * Constructor
	 * 
	 * @param button
	 * @param x
	 * @param y
	 * @param size
	 */
	public TableModel(int x, int y, int tableNum, int size)
	{
		this.state = TableState.AVAILABLE;
		this.guestName = "";
		this.x = x;
		this.y = y;
		this.tableNumber = tableNum;
		this.size = size;
	}
	
	public TableModel()
	{
		//Testing
	}
	
	public TableState getState() {
		return state;
	}

	public void setState(TableState state) {
		this.state = state;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		guestName = guestName;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
