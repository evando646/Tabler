package tabler.components.waitlist;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;

import tabler.components.guest.GuestModel;

public class WaitlistModel {
	// Represents the reservation window (i.e., the period of time before
	// and after a reservation start time for which the reservation would
	// be considered active)
	private static int RES_WIN_MINUTES = 15;
	
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
		this.soon = new LinkedList<GuestModel>();
		this.walkins = new LinkedList<GuestModel>();
		this.remaining = new LinkedList<GuestModel>();
		//System.out.println("The WaitlistModel constructor doesn't do anything yet");
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
			if (walkins.isEmpty() || 
					newGuest.getDateCreated().after(walkins.getLast().getDateCreated())) {/*newGuest.getDateCreated().compareTo(
					walkins.getLast().getDateCreated()) == 1) {*/
				walkins.add(newGuest);
				return;
			}
			
			/* Add the walkin-guest to the beginning of the list if the guest's
			 * creation date is earlier than the guest at the head of the waitlist.
			 * Normally this would *not* be the case except if we import guests with
			 * unordered creation dates.
			 */
			else if (newGuest.getDateCreated().before(walkins.getFirst().getDateCreated())) {
				walkins.addFirst(newGuest);
				return;
			}
			
			/* This handles the cases for when the new guest has a creation date
			 that isn't later than the most recently arrived guest. To do this
			 we can iterate from the bottom of the list to determine where to 
			 insert the new guest.*/
			Iterator<GuestModel> reverseGuest = walkins.descendingIterator();
			
			while (reverseGuest.hasNext()) {
				GuestModel guest = reverseGuest.next();
				
				if (newGuest.getDateCreated().before(guest.getDateCreated())) {
					if (!reverseGuest.hasNext()) {
						walkins.add(walkins.indexOf(guest), newGuest);
					} else {
						continue;
					}
				} else {
					walkins.add(walkins.indexOf(guest) + 1, newGuest);
					return;
				}
			}
		} 
		
		/* Add guests with immediate reservations to the soon waitlist.
		 * This option likely won't get triggered, but is here just in case
		 * we relax the requirement for how far in advance a reservation must
		 * be made.
		 */ 
		else if (isSoonReservation(newGuest) == true) {
			if (soon.isEmpty() ||
					newGuest.getReservationTime().after(soon.getLast().getReservationTime())) {
				soon.add(newGuest);
				return;
			}
			
			else if (newGuest.getReservationTime().before(soon.getFirst().getReservationTime())) {
				soon.addFirst(newGuest);
				return;
			}
			
			Iterator<GuestModel> reverseGuest = soon.descendingIterator();
			
			while (reverseGuest.hasNext()) {
				GuestModel guest = reverseGuest.next();
				
				if (newGuest.getReservationTime().before(guest.getReservationTime())) {
					if (!reverseGuest.hasNext()) {
						soon.add(soon.indexOf(guest), newGuest);
					} else {
						continue;
					}
				} else {
					soon.add(soon.indexOf(guest) + 1, newGuest);
					return;
				}
			}
		} 
		
		// Add all other guests with reservations to the remaining waitlist
		else {
			if (remaining.isEmpty() || 
					newGuest.getReservationTime().after(remaining.getLast().getReservationTime())) {
				remaining.add(newGuest);
				return;
			}
			
			else if (newGuest.getReservationTime().before(remaining.getFirst().getReservationTime())) {
				remaining.addFirst(newGuest);
				return;
			}
			
			Iterator<GuestModel> reverseGuest = remaining.descendingIterator();
			
			while (reverseGuest.hasNext()) {
				GuestModel guest = reverseGuest.next();
				
				if (newGuest.getReservationTime().before(guest.getReservationTime())) {
					if (!reverseGuest.hasNext()) {
						remaining.add(remaining.indexOf(guest), newGuest);
					} else {
						continue;
					}
				} else {
					remaining.add(remaining.indexOf(guest) + 1, newGuest);
					return;
				}
			}
		}
	}

	/**
	 * Creates a string representation of the <code>WaitlistModel</code> object  
	 * 
	 * @return a string representation of the <code>WaitlistModel</code> object
	 */
	public String toString() {
		String objectString = null;
		
		objectString = "[Waitlist:\n\tSoon\n\t====\n";
		
		if (!(this.soon.isEmpty())) {
			for (GuestModel guest : this.soon) {
				objectString += ("\t" + guest + "\n");
			}
		} else {
			objectString += "\tNone\n";
		}
		
		objectString += "\n\tWalkins\n\t=======\n";
		
		if (!(this.walkins.isEmpty())) {
			for (GuestModel guest : this.walkins) {
				objectString += ("\t" + guest + "\n");
			}
		} else {
			objectString += "\tNone\n\n";
		}
		
		objectString += "\n\tRemaining\n\t========\n";
		
		if (!(this.remaining.isEmpty())) {
			for (GuestModel guest : this.remaining) {
				objectString += ("\t" + guest + "\n");
			}
		} else {
			objectString += "\tNone\n\n";
		}
		
		objectString += "]";
		
		return objectString;
	}
	
	/**
	 * Determines whether a guest's reservation window is open
	 * 
	 * @param guest a guest with a reservation
	 * @return true if the guest's reservation window is open; false otherwise
	 */
	//TODO Have this method use the ClockModel's  instance of now
	private static boolean isSoonReservation(GuestModel guest) {
		if (guest.isReservation() == false) {
			return false;
		}
		
		GregorianCalendar resWindowStart = new GregorianCalendar();
		resWindowStart.add(Calendar.MINUTE, -RES_WIN_MINUTES);
		
		GregorianCalendar resWindowEnd = new GregorianCalendar();
		resWindowEnd.add(Calendar.MINUTE, RES_WIN_MINUTES);
		
		if (guest.getReservationTime().before(resWindowStart) ||
				guest.getReservationTime().after(resWindowEnd)) {
			return false;
		}

		return true;
	}
}
