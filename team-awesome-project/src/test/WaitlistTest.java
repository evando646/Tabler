package test;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.JFrame;

import tabler.components.guest.GuestModel;
import tabler.components.waitlist.WaitlistModel;
import tabler.components.waitlist.WaitlistView;

public class WaitlistTest {

	private static String guestsFile = "./src/test/guests.txt";

	/**
	 * Imports pre-determined guests from a guests file
	 * 
	 * @param pathToGuestsFile path to the guests file
	 * @return a list of guests that were imported from the guests file
	 */
	public static ArrayList<GuestModel> importGuests(String pathToGuestsFile) {
		if (pathToGuestsFile == null) {
			return null;
		}
		
		Scanner inputFile = null;
		ArrayList<GuestModel> importedGuests = new ArrayList<GuestModel>();
		//Enable this variable if you want to limit the number of guests to
		//import. Also change the while condition and enable the variable
		//decrement at the end of the while loop
		//int numImportGuests = 9;
		
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
		
		// Use this while-condition to import some of the guests
		//while (inputFile.hasNextLine() && numImportGuests > 0) {
		// Use this while-condition to import all of the guests
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
			
			GregorianCalendar created = (GregorianCalendar) now.clone();
			created.add(Calendar.DAY_OF_MONTH, dayOffset);
			created.add(Calendar.HOUR, hourOffset);
			created.add(Calendar.MINUTE, minuteOffset);
			created.add(Calendar.SECOND, secondOffset);
			
			String rDayOffset = guestScanner.next();
			String rHourOffset = guestScanner.next();
			String rMinuteOffset = guestScanner.next();
			String rSecondOffset = guestScanner.next();

			GregorianCalendar reservationStart = (GregorianCalendar) now.clone();
			reservationStart.add(Calendar.DAY_OF_MONTH, (rDayOffset.equals("-") ? dayOffset : Integer.parseInt(rDayOffset)));
			reservationStart.add(Calendar.HOUR, (rHourOffset.equals("-") ? hourOffset : Integer.parseInt(rHourOffset)));
			reservationStart.add(Calendar.MINUTE, (rMinuteOffset.equals("-") ? minuteOffset : Integer.parseInt(rMinuteOffset)));
			reservationStart.add(Calendar.SECOND, (rSecondOffset.equals("-") ? secondOffset : Integer.parseInt(rSecondOffset)));
			
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
			//numImportGuests--;
		}
		
		inputFile.close();
		return importedGuests;
	}
	
	public static void testRemoveGuest(ArrayList<GuestModel> guests, WaitlistModel waitlist) {
		System.out.println("\nWill remove the following guests");
		System.out.println(guests.get(0) + "\n" + guests.get(3) + "\n" + guests.get(7));
		
		waitlist.removeGuest(guests.get(0));
		waitlist.removeGuest(guests.get(3));
		waitlist.removeGuest(guests.get(7));
		System.out.println();
		
		System.out.println(waitlist);
	}
	
	public static void main(String[] args) {
		WaitlistModel newWaitlist = new WaitlistModel();
		WaitlistView newWaitlistView = null;
		ArrayList<GuestModel> guests = null;
		
		guests = importGuests(guestsFile);

		for (GuestModel guest : guests) {
			//System.out.println(guest);
			newWaitlist.addGuest(guest);
		}
		
		newWaitlistView = new WaitlistView(newWaitlist);
		System.out.println(newWaitlist);
		
		JFrame test = new WaitlistTestFrame();
		newWaitlistView = new WaitlistView(newWaitlist);
		test.add(newWaitlistView);
		//testRemoveGuest(guests, newWaitlist);
		
		
		//System.out.println(newWaitlist);
		//System.out.println(newWaitlistView);
	}

}

@SuppressWarnings("serial")
class WaitlistTestFrame extends JFrame {
	public WaitlistTestFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		setSize(screenWidth / 5, screenHeight / 2);
		setLocationByPlatform(true);
		this.setTitle("WaitlistTest");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}

}