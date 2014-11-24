package tabler;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import tabler.components.clock.ClockController;
import tabler.components.clock.ClockModel;
import tabler.components.clock.ClockView;
import tabler.components.floor.FloorController;
import tabler.components.floor.FloorModel;
import tabler.components.floor.FloorView;

public class MainPanel extends JPanel {

	public MainPanel()
	{
		this.setLayout(new BorderLayout());
		
		//Replace this with clock view
		ClockModel clockModel = new ClockModel();
		ClockView clockView = new ClockView(clockModel);
		ClockController clockController = new ClockController(clockModel, clockView);
		
		Timer t = new Timer(1000, clockController);
		t.start();
		
		this.add(clockView, BorderLayout.NORTH);
		
		
		//Floor View
        FloorModel floorModel = new FloorModel();
        FloorView floorView = new FloorView(floorModel.getTableList());
        FloorController floorController = new FloorController(floorModel, floorView);

        floorView.register(floorController);
        
        this.add(floorView,BorderLayout.WEST);
        
        //Waitlist
        JLabel waitlist = new JLabel("Waitlist");
        this.add(waitlist,BorderLayout.EAST);
	}
}
