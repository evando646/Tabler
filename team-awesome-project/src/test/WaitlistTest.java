package test;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

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
		int numImportGuests = 6;
		
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
		
		while (inputFile.hasNextLine() && numImportGuests > 0) {
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
			numImportGuests--;
		}
		
		inputFile.close();
		return importedGuests;
	}
	
	public static void main(String[] args) {
		WaitlistModel newWaitlist = new WaitlistModel();
		WaitlistView newWaitlistView = new WaitlistView();
		ArrayList<GuestModel> guests = null;
		
		guests = importGuests(guestsFile);

		for (GuestModel guest : guests) {
			//System.out.println(guest);
			newWaitlist.addGuestToList(guest);
		}
		
		System.out.println(newWaitlist);
		
		//System.out.println(newWaitlist);
		//System.out.println(newWaitlistView);
	}

}
