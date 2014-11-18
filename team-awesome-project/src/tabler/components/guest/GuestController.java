package tabler.components.guest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

public class GuestController implements ActionListener{
	private GuestView view;
	private GuestModel model;
	private static GregorianCalendar ControllerTime;
	

	
	public GuestController(GuestView view){
		this.view=view;

		
	}
	
	@Override
	//GuestModel(name,note,reservation,day,month,year,hour,minute)
	// GuestModel(String name,String note,String contactNumber,int size,GregorianCalendar created,GregorianCalendar reservationTime) throws Exception{
		
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command=e.getActionCommand();
		
		if(command.equals("Enter Guest")){
			/**try{
				model=new GuestModel(view.getNameTextField(),view.getNoteTextField(),view.)
			}
			catch(){
				
			}*/
			
		}
		else if(command.equals("Y+")){
			view.addYear();
		}
		else if(command.equals("Y-")){
			view.subtractYear();
			
		}
		else if(command.equals("M+")){
			view.addMonth();
		}
		else if(command.equals("M-")){
			view.subtractMonth();
		}
		else if(command.equals("D+")){
			view.addDay();
		}
		else if(command.equals("D-")){
			view.SubtractDay();
			
		}
		else if(command.equals("h+")){
			view.addHour();
		}
		else if(command.equals("h-")){
			view.SubtractHour();
		}
		else if(command.equals("m+")){
			view.addMinute();
		}
		else if(command.equals("m-")){
			view.SubtractMinute();
			
		}

	}
	

	
	//private check
	

}
