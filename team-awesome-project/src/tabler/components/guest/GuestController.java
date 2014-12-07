package tabler.components.guest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;

import tabler.FSM;
import tabler.FSM.FSM_STATE;

public class GuestController implements ActionListener{
	private AddGuestView view;
	private GuestModel model=null;
	
	private JFrame frameToClose = null;

	

	
	public GuestController(AddGuestView view){
		this.view=view;

		
	}
	
	@Override
	//GuestModel(name,note,reservation,day,month,year,hour,minute)
	// GuestModel(String name,String note,String contactNumber,int size,GregorianCalendar created,GregorianCalendar reservationTime) throws Exception{
		
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command=e.getActionCommand();
		
		if(command.equals("Enter Guest")){
			
			try{
				System.out.println("First Line\n");
				GregorianCalendar created=new GregorianCalendar();
				GregorianCalendar res = new GregorianCalendar();
				//res.add(Calendar.HOUR, 2);
				model=new GuestModel(view.getNameTextField(),view.getNoteTextField(),
						view.getContactTextField(),Integer.parseInt(view.getSizeTextField()),
						created,view.getViewTime()/*res*/);
				//System.out.println("If checkException:");
				System.out.println(model.toString());//comment this out, only for debuging purpose
				//PUT your code for storing/handeling the model
				FSM._instance.Action(FSM_STATE.ADD_GUEST, model);
				if( frameToClose != null )
				{
					frameToClose.dispose();
				}
			}
			catch(Exception a){
				System.err.println(a);
				view.displayErrorWindow(a);
			}
			
			

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
	
	public void setFrameToClose(JFrame frame)
	{
		frameToClose = frame;
	}
	
	//private check
	

}
