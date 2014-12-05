package tabler.components.waitlist;

import tabler.components.guest.GuestModel;
import tabler.components.guest.GuestView;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

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
	
	public void registerListener(WaitlistController controller)
	{
		guestlist.registerListener(controller);
	}
	
	public void updateView(WaitlistModel waitlist)
	{
		guestlist.updateView(waitlist);
	}
}

@SuppressWarnings("serial")
class GuestlistView extends JPanel {
private int totalDisplayableGuests;
	
	public GuestlistView(WaitlistModel waitlist) {
		init(waitlist);
	}
	
	public void updateView(WaitlistModel waitlist) {
		this.removeAll();
		init(waitlist);
		this.repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	public void registerListener(ActionListener controller)
	{
		Component[] guests = this.getComponents();
		
		for( Component c : guests)
		{
			GuestView view = (GuestView)c;
			view.getGuestMode().addActionListener(controller);
			view.getGuestName().addActionListener(controller);
		}
	}
	
	private void init(WaitlistModel waitlist)
	{
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
}
