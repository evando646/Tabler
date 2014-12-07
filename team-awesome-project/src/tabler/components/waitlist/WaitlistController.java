package tabler.components.waitlist;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import tabler.FSM;
import tabler.FSM.FSM_STATE;
import tabler.components.guest.GuestModel;
import tabler.components.guest.GuestView;

public class WaitlistController implements ActionListener {
	
	private WaitlistModel model;
	private WaitlistView view;
	
	public WaitlistController(WaitlistModel model, WaitlistView view)
	{
		this.model = model;
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if ( event.getSource() instanceof JButton )
		{
			JButton btn = (JButton)event.getSource();
			if( btn.getText().isEmpty() )
			{
				FSM._instance.Action(FSM_STATE.REMOVE_GUEST, btn.getParent());
			}
			else{
				FSM._instance.Action(FSM_STATE.GUEST, ((GuestView)btn.getParent()).getModel());
				
				
			}
		}
		
	}
	
	public void addGuest( GuestModel guest )
	{
		model.addGuest(guest);
		view.updateView(model);
		view.registerListener(this);
	}

	public void removeGuest( GuestModel guest )
	{
		model.removeGuest(guest);
		view.updateView(model);
		view.registerListener(this);
	}
}
