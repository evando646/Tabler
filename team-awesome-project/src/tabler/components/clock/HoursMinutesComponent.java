package tabler.components.clock;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;


@SuppressWarnings("serial")
public class HoursMinutesComponent extends JComponent {
	private static final int DEFAULT_WIDTH = 100;
	private static final int DEFAULT_HEIGHT = 200;
	
	private String hoursMinutes;
	
	public HoursMinutesComponent(ClockModel date) {
		hoursMinutes = String.format("%tI:%<tM", date.getCurrentDate());
	}
	
	public void update(ClockModel date) {
		hoursMinutes = String.format("%tI:%<tM", date.getCurrentDate());
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Font f = new Font("Serif", Font.BOLD, 70);
		g2.setFont(f);
		
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D bounds = f.getStringBounds(hoursMinutes, context);
		
		double x = (getWidth() - bounds.getWidth()) / 2;
		double y = (getHeight() - bounds.getHeight()) / 2;
		
		double ascent = -bounds.getY();
		double baseY = y + ascent;
		
		g2.drawString(hoursMinutes, (int) x, (int) baseY);
	}
	
	public Dimension getPreferredSize() { return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT); }
}
