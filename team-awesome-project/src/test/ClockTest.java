package test;

import tabler.components.clock.*;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
//import javax.swing.JOptionPane;
import javax.swing.Timer;


/** 
 * The <code>ClockTest</code> class exercises the <code>Clock</code> component.
 * It displays the current time as it would appear in the Tabler application.
 * 
 * @author Augustine (mr-augustine)
 */
public class ClockTest {

	public static void main(String[] args) {
		ClockModel model = new ClockModel();
		ClockView view = new ClockView(model);
		ClockController controller = new ClockController(model, view);
		
		Timer t = new Timer(1000, controller);
		t.start();
		
		JFrame frame = new TestFrame();
		frame.setTitle("ClockTest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(view);
		frame.setVisible(true);
		frame.pack();
		/*JOptionPane.showMessageDialog(null, "Keep this open while timestamps "
				+ "are printed to the console");*/
		//System.out.println("Done");
		//System.exit(0);
	}

}

/**
 * The <code>TestFrame</code> class serves as the background for the Clock,
 * much like the Tabler main window would.
 */
@SuppressWarnings("serial")
class TestFrame extends JFrame {
	public TestFrame() {
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		setSize(screenWidth / 2, screenHeight / 2);
		setLocationByPlatform(true);
		
	}

}