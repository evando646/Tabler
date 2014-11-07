package tabler.components.clock;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;


@SuppressWarnings("serial")
public class DayDateComponent extends JComponent {
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_HEIGHT = 50;
	private String dayDate;
	
	public DayDateComponent(ClockModel date) {
		this.dayDate = String.format("%ta, %<te %<tb", date.getCurrentDate());
	}
	
	public void update(ClockModel date) {
		this.dayDate = String.format("%ta, %<te %<tb", date.getCurrentDate());
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Font f = new Font("Serif", Font.BOLD, 40);
		g2.setFont(f);
		
		FontRenderContext context = g2.getFontRenderContext();
		Rectangle2D bounds = f.getStringBounds(dayDate, context);
		
		double x = (getWidth() - bounds.getWidth()) / 2;
		double y = (getHeight() - bounds.getHeight()) / 2;
		
		double ascent = -bounds.getY();
		double baseY = y + ascent;
		
		g2.drawString(dayDate, (int) x, (int) baseY);
	}
	
	public Dimension getPreferredSize() { return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT); }
}
