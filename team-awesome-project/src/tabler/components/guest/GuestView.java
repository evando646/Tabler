package tabler.components.guest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GuestView extends JPanel {
	private static Color defaultBG = Color.gray;
	private static Color resGuestButtonColor = Color.red;
	private static Color walkinGuestButtonColor = Color.white;
	private static Font defaultGuestFont = new Font("Serif", Font.BOLD, 20);
	private static Font defaultPartySizeFont = new Font("Serif", Font.BOLD, 20);
	
	private JLabel guestName;
	private JLabel partySize;
	private JButton guestMode;

	public GuestView(GuestModel guest) {
		this.setBackground(defaultBG);
		this.setLayout(new BorderLayout());
		
		this.guestName = new JLabel(guest.getName(), JLabel.CENTER);
		this.partySize = new JLabel(String.format("%d", guest.getSize()), JLabel.RIGHT);
		this.guestMode = new JButton();
		
		guestMode.setBackground((guest.isReservation() ? 
				resGuestButtonColor : walkinGuestButtonColor));
		guestMode.setBorderPainted(false);
		
		this.guestName.setFont(defaultGuestFont);
		this.partySize.setFont(defaultPartySizeFont);
		
		this.add(partySize, BorderLayout.WEST);
		this.add(guestName, BorderLayout.CENTER);
		this.add(guestMode, BorderLayout.EAST);
	}

	public void updateView(GuestModel guest) {
		this.guestName.setText(guest.getName());
		this.partySize.setText(String.format("%d", guest.getSize()));
		
		this.repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
