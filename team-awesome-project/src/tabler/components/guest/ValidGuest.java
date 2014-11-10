package tabler.components.guest;

public class ValidGuest {
	
	
	private boolean checkContact(String contact){
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
	
	private boolean checkNum(String line[]){
		
	}
	
	private boolean checkName(String name){
		if(name.length()>1){
			return true;
		}
		else{
			return false;
		}
		
	}

}
