package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.lang.Integer;

import javax.swing.JFrame;
//import Guest.ValidGuest;

public class GuestTest {
	//private JFrame frame;

	//@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		
		
		
		String name="Rob";
		String contact="512-79-4857";
		String note="Stuff and Things";
		int size=5;
		GregorianCalendar made= new GregorianCalendar();
		//month in range of 0-11 jan-December
		GregorianCalendar setRev=new GregorianCalendar(2018,2,15,8,6);//year,month,dayofmonth,hour,minute
		GuestModel model=null;


		//
		GuestView window=new GuestView();
		//model=new GuestModel();
		GuestController controller=new GuestController(window);
		
		window.registerListener(controller);
		
		window.frame.setVisible(true);

		
		

	}

}
