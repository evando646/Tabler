package tabler.components.waitlist;

import java.util.LinkedList;

import tabler.components.guest.GuestModel;

public class WaitlistModel {
	// Represents the reservation window (i.e., the period of time before
	// and after a reservation start time for which the reservation would
	// be considered active)
	private static int RES_WIN_MINUTES = 15;
	
	private LinkedList<GuestModel> soon;
	private LinkedList<GuestModel> walkins;
	private LinkedList<GuestModel> remaining;
	
	public WaitlistModel() {
		System.out.println("The WaitlistModel constructor doesn't do anything yet");
	}
	
	public void addGuestToList(GuestModel newGuest) {
		if (newGuest.isReservation() == false) {
			sortedAdd(walkins, newGuest);
		}
	}
	
	private static boolean isSoonReservation(GuestModel guest) {
		if (guest.isReservation() == false) {
			return false;
		}
		
		// TODO replace this placeholder
		return false;
	}
	
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
