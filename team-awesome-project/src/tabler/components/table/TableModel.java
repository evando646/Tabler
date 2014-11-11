package tabler.components.table;

import javax.swing.JButton;

/**
 * Table Model
 * 
 * @author frg169
 *
 */
public class TableModel {
	
	public enum TableState { AVAILABLE, OCCUPIDE, DIRTY };
	
	private TableState state;

	private String GuestName;
	private int size;
	
	private int x;
	private int y;
	
	private JButton button;
	
	/**
	 * Constructor
	 * 
	 * @param button
	 * @param x
	 * @param y
	 * @param size
	 */
	public TableModel(JButton button, int x, int y, int size)
	{
		this.state = TableState.AVAILABLE;
		this.GuestName = "";
		this.button = button;
		this.x = x;
		this.y = y;
		this.size = size;
	}
	
	public TableState getState() {
		return state;
	}

	public void setState(TableState state) {
		this.state = state;
	}

	public String getGuestName() {
		return GuestName;
	}

	public void setGuestName(String guestName) {
		GuestName = guestName;
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

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}

}
