package tabler.components.guest;
/*
 * Roberto Mexquitic
 * CS3443 
 * TEAM AWESOME!!!!!
 */


import java.util.Calendar;
import java.util.GregorianCalendar;

public class GuestModel {
	
	private String name;
	private GregorianCalendar created;
	private GregorianCalendar reservationTime;
	private int size;
	private String note;
	private boolean reservation;
	private String contactNumber;
	
	
	/*
	 * Initialize everything
	 * Assuming that all variables are valid
	 * Need to use ValidGuest Class before making Model
	 */

	public GuestModel(String name,String note,String contactNumber,int size,GregorianCalendar created,GregorianCalendar reservationTime) throws Exception{
		if(checkName(name)==false){
			throw new Exception("Enter Valid Name\n Must be one letter long");
		}
		if(!checkContact(contactNumber)){
			throw new Exception("Enter Valid Contact Number\n Only (123) 456-7890");
		}
		

		
		this.name=name;
		this.note=note;
		this.size=size;
		this.contactNumber=contactNumber;
		this.created=created;
		this.reservationTime=reservationTime;
		reservation=false;
		//checkReservation(created,reservationTime);
		
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
	public GregorianCalendar getDateCreated(){
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
	
	public GregorianCalendar getReservationTime(){
		return reservationTime;
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
	


	@Override
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Made for testing purpose
	 * Just returns all fields
	 */
	public String toString() {
		return "GuestModel [name=" + name + ", created=" + getCreatedTimeAsString()
				+ ", reservationTime="  + ", \nsize=" + size
				+ ", note=" + note + ", reservationTime=" +getReservationTimeAsString() +"\nIS Reservation" +reservation
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
	
	
	/**
	 * For toString method
	 * @return string
	 */
	private String getReservationTimeAsString(){
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
	/**
	 * For toStringMEtod
	 * @return String
	 */
	private String getCreatedTimeAsString(){
		int year=created.get(Calendar.YEAR);
		int month=created.get(Calendar.MONTH);
		int day=created.get(Calendar.DAY_OF_MONTH);
		int hour=created.get(Calendar.HOUR);
		int minute=created.get(Calendar.MINUTE);
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
