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
public GuestModel(){
		
	}

	public void createGuest(String name,String note, boolean rev, String contactNumber,int day,int month,int year,int hour,int minute) throws Exception{
		if(checkName(name)==false){
			throw new Exception("Enter Valid Name\n Must be one letter long");
		}
		if(!checkContact(contactNumber)){
			throw new Exception("Enter Valid Contact Number\n Only (123) 456-7890");
		}
		
		this.name=name;
		this.note=note;
		reservation=rev;
		this.contactNumber=contactNumber;
		created=new Date();
		reservationTime=Calendar.getInstance();
		reservationTime.set(year, month-1, day, hour, minute);
		
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
	
	public void setContactNumber(String contactNum){
		contactNumber=contactNum;
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
	
	public int compareTo(GuestModel other) {
		if (this.isReservation() && other.isReservation()) {
			int comparison = this.getReservationTime().compareTo(
					other.getReservationTime());
			
			if (comparison == 0) {
				return (this.getDateCreated().compareTo(other.getDateCreated()));
			} else {
				return comparison;
			}
		} else if (!(this.isReservation()) && !(other.isReservation())) {
			return (this.getDateCreated().compareTo(other.getDateCreated()));
		} else {
			return (this.getReservationTime().compareTo(other.getReservationTime()));
		}
	}
	
	public String getReservationTime(){
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
	
	/*
	 * Validation Methods
	 * 
	 */
	
	
	/*
	 * Use this method to check if the contact and name fields are both valid
	 * Both must be valid to continue on.
	 */
	public static boolean checkGuest(String name,String contact){
		if(checkContact(contact)&&checkName(name)){
			return true;
		}
		else{
			return false;
		}
	}
	
	/*
	 * will filter the contact string to leave only digits
	 * Ideal input 1234567890 (123) 456 7890 (450)-897-4578
	 * Only "." "()" "-" are allowed anything else will return false
	 * Must be Ten digit number 
	 */
	private static boolean checkContact(String contact){
		if(contact.length()<10){
			return false;
		}
		else{
			
			String delims=" |\\(|\\)|\\-|\\.";
			String[] holdString=contact.split(delims);
			
			if(checkNum(holdString)){
					
					
			}
			else{
				return false;
			}
		}
		return true;
	}
	
	/*
	 * this method will check that the char in the line[] are all 
	 * numberic numbers and that their are only and only 10 of them
	 */
	private static boolean checkNum(String line[]){
		int count=0;

		for(int i=0;i<line.length;i++){
		
			for(int j=0;j<line[i].length();j++){
				if(!isInteger(String.valueOf(line[i].charAt(j)))){
					return false;
				}
				count++;
			}
		}
		if(count!=10){//if thier are not exactly ten then false
			return false;
		}
		
		return true;
	}
	
	/*
	 * This will check if the char string is a number or not
	 * if it is it will return true otherwise false
	 */
	private static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    
	    return true;
	}
	
	
	/*
	 * this will check the name field 
	 * as long as it is not empty it will be true
	 */
	private static boolean checkName(String name){
		if(name.length()>0){
			return true;
		}
		else{
			return false;
		}
		
	}
	
}
