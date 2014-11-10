package tabler.components.clock;

import java.util.Date;

/**
 * The <code>ClockModel</code> class represents the current state of the clock.
 * 
 * @author Augustine (mr-augustine)
 */
public class ClockModel {
	private Date currentDate;
	
	/**
	 * Constructs a new clock object.
	 */
	public ClockModel() {
		this.currentDate = new Date();
	}
	
	/**
	 * Accessor function for the <code>currentDate</code>
	 * 
	 * @return the current timestamp
	 */
	public Date getCurrentDate() {
		return this.currentDate;
	}
	
	/**
	 * Updates the date
	 */
	public void updateDate() {
		this.currentDate = new Date();
	}
}
