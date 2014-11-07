package tabler.components.clock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClockController implements ActionListener {
	private ClockModel now;
	private ClockView view;
	
	public ClockController(ClockModel now, ClockView view) {
		this.now = now;
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent event) {
		now.updateDate();
		view.updateView(now);
	}

}
