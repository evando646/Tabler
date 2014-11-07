package tabler.components.clock;

import java.util.Date;

public class ClockModel {
	private Date currentDate;
	
	public ClockModel() {
		this.currentDate = new Date();
	}
	
	public Date getCurrentDate() {
		return this.currentDate;
	}
	
	public void updateDate() {
		this.currentDate = new Date();
	}
}
