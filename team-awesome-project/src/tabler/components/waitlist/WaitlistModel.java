package tabler.components.waitlist;

import java.util.LinkedList;

import tabler.components.guest.GuestModel;

public class WaitlistModel {
	// Represents the reservation window (i.e., the period of time before
	// and after a reservation start time for which the reservation would
	// be considered active)
	private static int RES_WIN_MINUTES = 15;
	
	/*
	 * The waitlist is really comprised of three lists: (1) the soon list which
	 * represent the sorted list of reservations whose windows are currently open;
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
	 * Determine the appropriate waitlist to add the guest to and then adds the guest
	 * 
	 * @param newGuest a guest with a reservation
	 */
	public void addGuestToList(GuestModel newGuest) {
		if (newGuest.isReservation() == false) {
			sortedAdd(walkins, newGuest);
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
	
	/**
	 * Adds a new guest to the specified waitlist by inserting the guest
	 * in priority order.
	 * 
	 * @param list the waitlist the guest would be added to
	 * @param newGuest a guest with a reservation
	 */
	private void sortedAdd(LinkedList<GuestModel> list, 
			GuestModel newGuest) {
		if (newGuest.isReservation() == false) {
			for (int i = 0; i < walkins.size(); i++) {
				if (newGuest.getDateCreated().compareTo(
						walkins.get(i).getDateCreated()) == -1) {
					walkins.add(i, newGuest);
					return;
				}
			}
			
			walkins.addLast(newGuest);
			return;
		} else if (isSoonReservation(newGuest) == true) {
			for (int i = 0; i < soon.size(); i++) {
				if (newGuest.getReservation().compareTo(
						soon.get(i).getReservation()) == -1) {
					soon.add(i, newGuest);
					return;
				}
			}
			
			soon.addLast(newGuest);
			return;
		} else {
			for (int i = 0; i < remaining.size(); i++) {
				if (newGuest.getReservation().compareTo(
						remaining.get(i).getReservation()) == -1) {
					remaining.add(i, newGuest);
					return;
				}
			}
			
			remaining.addLast(newGuest);
			return;
		}
	}
}
