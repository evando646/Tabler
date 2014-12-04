package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.io.File;
import java.lang.Integer;

import javax.swing.JFrame;

import tabler.components.guest.GuestModel;
import tabler.components.guest.AddGuestView;

public class GuestTest {
	private static String guestsFile = "./src/test/guests.txt";
	//private JFrame frame;

	public static ArrayList<GuestModel> importGuests(String pathToGuestsFile) {
		if (pathToGuestsFile == null) {
			return null;
		}
		
		Scanner inputFile = null;
		ArrayList<GuestModel> importedGuests = new ArrayList<GuestModel>();
		
		try {
			inputFile = new Scanner(new File(guestsFile));
		} catch (Exception FileNotFoundException) {
			System.err.printf("Error: %s not found\n", inputFile);
			System.exit(1);
		}
		
		// skip the field descriptor line
		inputFile.nextLine();
		
		//System.out.println("Contents of guests file\n");
		
		GregorianCalendar now = new GregorianCalendar();
		
		while (inputFile.hasNextLine()) {
			String nextGuest = inputFile.nextLine();
			Scanner guestScanner = new Scanner(nextGuest);
			guestScanner.useDelimiter(",");
			
			String name = guestScanner.next();
			String contact = guestScanner.next();
			int partySize = guestScanner.nextInt();
			
			int dayOffset = guestScanner.nextInt();
			int hourOffset = guestScanner.nextInt();
			int minuteOffset = guestScanner.nextInt();
			int secondOffset = guestScanner.nextInt();
			
			GregorianCalendar created = new GregorianCalendar(
					now.get(Calendar.YEAR),	now.get(Calendar.MONTH), 
					now.get(Calendar.DAY_OF_MONTH) +  + dayOffset, 
					now.get(Calendar.HOUR) + hourOffset, 
					now.get(Calendar.MINUTE) + minuteOffset, 
					now.get(Calendar.SECOND) + secondOffset);
			
			String rDayOffset = guestScanner.next();
			String rHourOffset = guestScanner.next();
			String rMinuteOffset = guestScanner.next();
			String rSecondOffset = guestScanner.next();

			GregorianCalendar reservationStart = new GregorianCalendar(
					now.get(Calendar.YEAR), now.get(Calendar.MONTH),
					now.get(Calendar.DAY_OF_MONTH) + (rDayOffset.equals("-") ? dayOffset : Integer.parseInt(rDayOffset.toString())),
					now.get(Calendar.HOUR) + (rHourOffset.equals("-") ? hourOffset : Integer.parseInt(rHourOffset.toString())),
					now.get(Calendar.MINUTE) + (rMinuteOffset.equals("-") ? minuteOffset : Integer.parseInt(rMinuteOffset.toString())),
					now.get(Calendar.SECOND)+ (rSecondOffset.equals("-") ? secondOffset : Integer.parseInt(rSecondOffset.toString())));
			
			String note;

			if (guestScanner.hasNext()) {
				note = guestScanner.next();
			} else {
				note = "";
			}
			
			GuestModel newGuest = null;
			try {
				newGuest = new GuestModel(name, note, contact, 
					partySize, created, reservationStart);
			} catch (Exception e) {
				
			}
			
			if (newGuest != null) { importedGuests.add(newGuest); }
			//System.out.println(nextGuest);
			guestScanner.close();
		}
		
		inputFile.close();
		return importedGuests;
	}

	//@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		ArrayList<GuestModel> guests = null;
		
		guests = importGuests(guestsFile);

		for (GuestModel guest : guests) {
			System.out.println(guest);
		}
		
		/*
		String name="Rob";
		String contact="512-79-4857";
		String note="Stuff and Things";
		int size=5;
		GregorianCalendar made= new GregorianCalendar();
		//month in range of 0-11 jan-December
		GregorianCalendar setRev=new GregorianCalendar(2018,2,15,8,6);//year,month,dayofmonth,hour,minute
		GuestModel model=null;


		//
		AddGuestView window=new AddGuestView();
		//model=new GuestModel();
		GuestController controller=new GuestController(window);
		
		window.registerListener(controller);
		
		window.frame.setVisible(true);
		*/
		
		

	}
}
