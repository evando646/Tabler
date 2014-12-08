package tabler.components.clock;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The <code>ClockView</code> class represents the View portion of the clock
 * component. It dictates how the clock is displayed in the GUI.
 * 
 * @author Augustine (mr-augustine)
 *
 */
@SuppressWarnings("serial")
public class ClockView extends JPanel {
	private static Color defaultBG = Color.gray;
	private static Font defaultDayDateFont = new Font("Serif", Font.PLAIN, 30);
	private static Font defaultHoursMinsFont = new Font("Serif", Font.BOLD, 40);
	private static Font defaultSecondsFont = new Font("Serif", Font.BOLD, 20);
	private static Font defaultAMPMFont = new Font("Serif", Font.BOLD, 20);
	
	private JLabel dayDateLabel;
	private JLabel hoursMinsLabel;
	private JLabel secondsLabel;
	private JLabel amPMLabel;
	private JPanel secAM_PM;
	
	/**
	 * Constructs a new <code>ClockView</code> object
	 * 
	 * @param date the timestamp that the clock will begin from
	 */
	public ClockView(ClockModel date) {
		this.setBackground(defaultBG);
		this.setLayout(new BorderLayout());
		
		dayDateLabel = new JLabel(String.format("%ta, %<te %<tb", 
				date.getCurrentDate()),
				JLabel.CENTER);
		hoursMinsLabel = new JLabel(String.format("%tI:%<tM", 
				date.getCurrentDate()),
				JLabel.CENTER);
		secondsLabel = new JLabel(String.format("%tS", 
				date.getCurrentDate()),
				JLabel.CENTER);
		amPMLabel = new JLabel(String.format("%Tp", 
				date.getCurrentDate()),
				JLabel.CENTER);
		
		dayDateLabel.setFont(defaultDayDateFont);
		hoursMinsLabel.setFont(defaultHoursMinsFont);
		secondsLabel.setFont(defaultSecondsFont);
		amPMLabel.setFont(defaultAMPMFont);
		
		secAM_PM = new JPanel();
		secAM_PM.setBackground(defaultBG);
		secAM_PM.setLayout(new GridLayout(2,1));
		secAM_PM.add(secondsLabel);
		secAM_PM.add(amPMLabel);
		
		this.add(dayDateLabel, BorderLayout.NORTH);
		this.add(hoursMinsLabel, BorderLayout.CENTER);
		this.add(secAM_PM, BorderLayout.EAST);
		
	}
	
	/**
	 * Updates the clock display.
	 * 
	 * @param now the current time
	 */
	public void updateView(ClockModel now) {
		dayDateLabel.setText(String.format("%ta, %<te %<tb", now.getCurrentDate()));
		hoursMinsLabel.setText(String.format("%tI:%<tM", now.getCurrentDate()));
		secondsLabel.setText(String.format("%tS", now.getCurrentDate()));
		amPMLabel.setText(String.format("%Tp", now.getCurrentDate()));
		
		this.repaint();
		//System.out.printf("Current time: %tc\n", now.getCurrentDate());
	}
	
	/**
	 * Redraws the clock.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
