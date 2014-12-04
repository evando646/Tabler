package tabler.components.waitlist;

import tabler.components.guest.GuestModel;
import tabler.components.guest.GuestView;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * The <code>WaitlistView</code> class represents the View portion of the
 * waitlist component. It dictates how the waitlist is displayed in the GUI.
 * 
 * @author Augustine (mr-augustine)
 *
 */
@SuppressWarnings("serial")
public class WaitlistView extends JPanel {
	private GuestlistView guestlist;
	
	public WaitlistView(WaitlistModel waitlist) {
		this.setLayout(new BorderLayout());
		this.guestlist = new GuestlistView(waitlist);
		
		JScrollPane scroll = new JScrollPane(this.guestlist);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		
		this.add(scroll, BorderLayout.CENTER);
	}
}

@SuppressWarnings("serial")
class GuestlistView extends JPanel {
private int totalDisplayableGuests;
	
	public GuestlistView(WaitlistModel waitlist) {
		totalDisplayableGuests = waitlist.getSoonGuests().size() + 
				waitlist.getWalkinsGuests().size() +
				waitlist.getTodaysRemainingGuests().size();
		
		this.setLayout(new GridLayout(totalDisplayableGuests, 1, 0, 0));
		
		for (GuestModel guest : waitlist.getSoonGuests()) {
			GuestView newGuestView = new GuestView(guest);
			this.add(newGuestView);
		}
		
		for (GuestModel guest : waitlist.getWalkinsGuests()) {
			GuestView newGuestView = new GuestView(guest);
			this.add(newGuestView);
		}
		
		for (GuestModel guest : waitlist.getTodaysRemainingGuests()) {
			GuestView newGuestView = new GuestView(guest);
			this.add(newGuestView);
		}
	}
	
	public void updateView() {
		this.repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
