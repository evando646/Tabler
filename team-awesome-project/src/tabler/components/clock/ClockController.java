package tabler.components.clock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The <code>ClockController</code> class acts as the interface between the
 * GUI and the clock model.
 * 
 * @author Augustine (mr-augustine)
 *
 */
public class ClockController implements ActionListener {
	private ClockModel now;
	private ClockView view;
	
	/**
	 * Constructs a new <code>ClockController</code> object
	 * 
	 * @param now the current time
	 * @param view the View that will be affected
	 */
	public ClockController(ClockModel now, ClockView view) {
		this.now = now;
		this.view = view;
	}
	
	/**
	 * Callback function that initiates the clock update
	 */
	public void actionPerformed(ActionEvent event) {
		now.updateDate();
		view.updateView(now);
	}

}
