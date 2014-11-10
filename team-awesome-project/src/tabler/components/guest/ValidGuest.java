package Guest;

/*
 * This class will check if the name field and 
 * contact number filed are valid
 * This class should be used first before createing the accual gues
 */

public class ValidGuest {
	
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
