package tabler.components.waitlist;

import java.util.Iterator;
import java.util.LinkedList;

import tabler.components.guest.GuestModel;

public class WaitlistModel {
	// Represents the reservation window (i.e., the period of time before
	// and after a reservation start time for which the reservation would
	// be considered active)
	//private static int RES_WIN_MINUTES = 15;
	
	/*
	 * The waitlist is really comprised of three lists: (1) the soon list which
	 * represents the sorted list of reservations whose windows are currently open;
	 * (2) the walkins list which represents the sorted list of guests who did not
	 * create a reservation beforehand; and (3) the remaining list which represents
	 * the sorted list of reservations whose windows have not opened yet.
	 */
	private LinkedList<GuestModel> soon;
	private LinkedList<GuestModel> walkins;
	private LinkedList<GuestModel> remaining;
	
	public WaitlistModel() {
		System.out.println("The WaitlistModel constructor doesn't do anything yet");
	}
	
	/**
	 * Determines the appropriate waitlist to add the guest to 
	 * and then adds the guest
	 * 
	 * @param newGuest a guest to add to the waitlist
	 */
	public void addGuestToList(GuestModel newGuest) {
		if (newGuest == null) {
			return;
		}
		
		// Add walk-in guests to the walkin list
		if (newGuest.isReservation() == false) {
			
			/* Add the walk-in guest to the end of the list if the walkins list
			 is empty OR the guest is the latest to have arrived. Normally this
			 would always be the case; except if we import guests with unordered
			 creation dates.*/
			if (walkins.isEmpty() || newGuest.getDateCreated().compareTo(
					walkins.getLast().getDateCreated()) == 1) {
				walkins.add(newGuest);
				return;
			}
			
			/* This handles the cases for when the new guest has a creation date
			 that isn't later than the most recently arrived guest. To do this
			 we can iterate from the bottom of the list to determine where to 
			 insert the new guest.*/
			Iterator<GuestModel> reverseGuest = walkins.descendingIterator();
			
			while (reverseGuest.hasNext()) {
				GuestModel guest = reverseGuest.next();
				
				if (newGuest.getDateCreated().compareTo(guest.getDateCreated()) == 1) {
					if (!(guest.equals(walkins.getLast()))) {
						walkins.add(walkins.indexOf(guest), newGuest);
						break;
					}
					
					// This part is redundant since we already take care of
					// appending a new guest in the if-block above this while-loop
					// TODO consider advancing the iterator to the second-to-last guest
					else {
						walkins.add(newGuest);
						break;
					}
				}
			}

			return;
		} 
		
		/* Add guests with immediate reservations to the soon waitlist.
		 * This option likely won't get triggered, but is here just in case
		 * we relax the requirement for how far in advance a reservation must
		 * be made.
		 */ 
		else if (isSoonReservation(newGuest) == true) {
			for (int i = 0; i < soon.size(); i++) {
				if (newGuest.getReservationTime().compareTo(
						soon.get(i).getReservationTime()) == -1) {
					soon.add(i, newGuest);
					return;
				}
			}
			
			soon.addLast(newGuest);
			return;
		} 
		
		// Add all other guests with reservations to the remaining waitlist
		else {
			for (int i = 0; i < remaining.size(); i++) {
				if (newGuest.getReservationTime().compareTo(
						remaining.get(i).getReservationTime()) == -1) {
					remaining.add(i, newGuest);
					return;
				}
			}
			
			remaining.addLast(newGuest);
			return;
		}
	}

	
	/**
	 * Determines whether a guest's reservation window is open
	 * 
	 * @param guest a guest with a reservation
	 * @return true if the guest's reservation window is open; false otherwise
	 */
	private static boolean isSoonReservation(GuestModel guest) {
		if (guest.isReservation() == false) {
			return false;
		}
		
		// TODO replace this placeholder
		return false;
	}
}
