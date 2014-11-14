package Guest;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.lang.Integer;

import javax.swing.JFrame;
//import Guest.ValidGuest;

public class GuestTest {
	//private JFrame frame;

	//@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		
		
		
		String name="Rob";
		String contact="512-789-4857";
		String note="Stuff and Things";
		int size=5;
		GregorianCalendar made= new GregorianCalendar();
		//month in range of 0-11 jan-December
		GregorianCalendar setRev=new GregorianCalendar(2018,2,15,8,6);//year,month,dayofmonth,hour,minute
		GuestModel model=null;
		
		try {
			model=new GuestModel(name,note,contact,size,made,setRev);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(model.toString());
		
		/**
		GuestView window=new GuestView();
		GuestModel model=new GuestModel();
		GuestController controller=new GuestController(window,model);
		
		window.registerListener(controller);
		
		window.frame.setVisible(true);
		//window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//window.setSize(400, 300);
		//window.setVisible(true);
		*/
		

	}

}
