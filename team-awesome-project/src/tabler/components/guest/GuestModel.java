package tabler.components.guest;
/*
 * Roberto Mexquitic
 * CS3443 
 * TEAM AWESOME!!!!!
 */

import java.util.Date;
import java.util.Calendar;

public class GuestModel {
	
	private String name;
	private Date created;
	private Calendar reservationTime;
	private int size;
	private String note;
	private boolean reservation;
	private String contactNumber;
	
	
	/*
	 * Initialize everything
	 * Assuming that all variables are valid
	 * Need to use ValidGuest Class before making Model
	 */
	public GuestModel(String name,String note, boolean rev, String contactNumber){
		this.name=name;
		this.note=note;
		reservation=rev;
		this.contactNumber=contactNumber;
		created=new Date();
		
	}
	
	/*
	 * return name of the party
	 */
	public String getName(){
		return name;
	}
	
	/*
	 * return the date when this guest model 
	 * was created
	 */
	public Date getDateCreated(){
		return created;
	}

	
	/*
	 * return the contact number of the party
	 */
	public String getContactNumber(){
		return contactNumber;
	}
	
	/*
	 * return note of the party
	 */
	public String getNote(){
		return note;
	}
	
	/*
	 * return size of party
	 */
	public int getSize(){
		return size;
	}
	
	/*
	 * return true or false if party is a 
	 * Reservation or not.
	 */
	public boolean isReservation(){
		return reservation;
	}
	
	
	/*
	 * Following set Methods 
	 * will set the values of each field we called 
	 * Respectably
	 * ??? DO WE WANT TO KEEP??? 
	 */
	
	
	/*
	 * when setting the date it is set as 
	 * 0-11
	 * Jan-Dec
	 * Will assume it has not been correct for this
	 * This will set the reservation time as a Calendar class 
	 */
	public void setReservationTime(int day,int month,int year,int hour,int minute){
		reservationTime=Calendar.getInstance();
		reservationTime.set(year, month-1, day, hour, minute);
	}
	
	public void setName(String name){
		this.name=name;
	}
	

	
	public void setNote(String note){
		this.note=note;
	}
	

	
	public void setSize(int size){
		this.size=size;
	}
	
	/*
	 * every time this method is called it will switch 
	 * Whether a reservation is true or not.
	 * So the User can switch the reservation every time they click
	 * the check box or whatever it is that we will use to check a 
	 * reservation
	 */
	public void setReservation(){
		reservation=!(reservation);
	}

	@Override
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Made for testing purpose
	 * Just returns all fields
	 */
	public String toString() {
		return "GuestModel [name=" + name + ", created=" + created
				+ ", reservationTime="  + ", size=" + size
				+ ", note=" + note + ", reservation=" + reservation
				+ ", contactNumber=" + contactNumber + "]";
	}
	
	public String reservationTimeRet(){
		int year=reservationTime.get(Calendar.YEAR);
		int month=reservationTime.get(Calendar.MONTH);
		int day=reservationTime.get(Calendar.DAY_OF_MONTH);
		int hour=reservationTime.get(Calendar.HOUR);
		int minute=reservationTime.get(Calendar.MINUTE);
		if(minute<10){
			return (month+1)+"/"+day+"/"+year+" "+hour+":0"+minute;
		}
		else{
			return (month+1)+"/"+day+"/"+year+" "+hour+":"+minute;
		}
	}
	
	
}
