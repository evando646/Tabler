package tabler.components.clock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class ClockView extends JPanel {
	private static Color defaultBG = Color.gray;
	
	private JPanel secAM_PM;
	private DayDateComponent dayDate;
	private HoursMinutesComponent hoursMins;
	private SecondsComponent seconds;
	private AMPMComponent amPM;
	
	public ClockView(ClockModel date) {
		this.setBackground(defaultBG);
		this.setLayout(new BorderLayout());
		
		dayDate = new DayDateComponent(date);
		hoursMins = new HoursMinutesComponent(date);
		
		seconds = new SecondsComponent(date);
		amPM = new AMPMComponent(date);
		secAM_PM = new JPanel();
		secAM_PM.setBackground(defaultBG);
		secAM_PM.setLayout(new GridLayout(2,1));
		secAM_PM.add(seconds);
		secAM_PM.add(amPM);
		
		this.add(dayDate, BorderLayout.NORTH);
		this.add(hoursMins, BorderLayout.CENTER);
		this.add(secAM_PM, BorderLayout.EAST);
	}
	
	public void updateView(ClockModel now) {
		dayDate.update(now);
		seconds.update(now);
		amPM.update(now);
		hoursMins.update(now);
		
		this.repaint();
		//System.out.printf("Current time: %tc\n", now.getCurrentDate());
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//dayDate.repaint();
		//seconds.repaint();
		//amPM.repaint();
		//secAM_PM.repaint();
		//hoursMins.repaint();
	}
	/*public void registerListener(ClockController controller) {
		
	}*/
}
