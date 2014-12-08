package tabler;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import tabler.components.clock.ClockController;
import tabler.components.clock.ClockModel;
import tabler.components.clock.ClockView;
import tabler.components.floor.FloorController;
import tabler.components.floor.FloorModel;
import tabler.components.floor.FloorView;
import tabler.components.server.SectionModel;
import tabler.components.server.ServerModel;
import tabler.components.server.ServerQueueController;
import tabler.components.server.ServerQueueModel;
import tabler.components.server.ServerQueueView;
import tabler.components.waitlist.WaitlistController;
import tabler.components.waitlist.WaitlistModel;
import tabler.components.waitlist.WaitlistView;
import tabler.components.guest.AddGuestView;
import tabler.components.guest.GuestController;
import tabler.components.guest.GuestModel;
import test.WaitlistTest;

public class MainPanel extends JPanel {

	private ArrayList<GuestModel> guests;

	private ClockModel clockModel;
	private ClockView clockView;
	private ClockController clockController;
	
	private FloorModel floorModel;
	private FloorView floorView;
	private FloorController floorController;
	
	private WaitlistModel waitlistModel;
	private WaitlistView waitlistView;
	private WaitlistController waitlistController;
	
	private SectionModel sectionmodel;
	private ServerModel servermodel;
	private ArrayList<SectionModel> sections = null;
	private ArrayList<ServerModel> servers = null;
	
	private ServerQueueModel queuemodel;
	private ServerQueueView qview;
	private ServerQueueController queueController;
	
	private JPanel buttonPanel;
	
	public MainPanel()
	{
		this.setLayout(new BorderLayout());
		
		//Subpanel for waitlist and serverqueue
        JPanel subPanelEast = new JPanel();
        subPanelEast.setLayout(new BoxLayout(subPanelEast, BoxLayout.Y_AXIS));
        
        JPanel subPanelNorth = new JPanel();
        subPanelNorth.setLayout(new BoxLayout(subPanelNorth, BoxLayout.X_AXIS));
        
        this.add(subPanelNorth,BorderLayout.NORTH);
        
		//Replace this with clock view
		clockModel = new ClockModel();
		clockView = new ClockView(clockModel);
		clockController = new ClockController(clockModel, clockView);
		
		Timer t = new Timer(1000, clockController);
		t.start();
		
		subPanelNorth.add(clockView);
		
		buttonPanel = new JPanel();
	
		JButton addGuest = new JButton("Add Guest");
		addGuest.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event) {
				AddGuestView addGuest = new AddGuestView();
				
				GuestController guestController = new GuestController(addGuest);
				
				addGuest.registerListener(guestController);
				
				JFrame frame = new JFrame();
				frame.add(addGuest);
				
				guestController.setFrameToClose(frame);
				
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setSize(491, 300);
				
				frame.setVisible(true);
			}
		});
		
		buttonPanel.add(addGuest);
		
		subPanelNorth.add(buttonPanel,BorderLayout.NORTH);
		
		//Floor View
        floorModel = new FloorModel();
        floorView = new FloorView(floorModel.getTableList());
        floorController = new FloorController(floorModel, floorView);
        
        floorView.setPreferredSize(new Dimension(800,600));

        floorView.register(floorController);
        
        this.add(floorView,BorderLayout.WEST);

        //Waitlist
        guests = importGuests("./src/test/guests.txt");
        waitlistModel = new WaitlistModel();
        for (GuestModel guest : guests) {
        	waitlistModel.addGuest(guest);
        }
        waitlistView = new WaitlistView(waitlistModel);
        waitlistController = new WaitlistController(waitlistModel,waitlistView);
        
        waitlistView.registerListener(waitlistController);
 
        //waitlistView.add( new JLabel("Waitlist") );

        
        //ServerQueue
        //ServerQueueModel queuemodel = new ServerQueueModel(servers);
        sectionmodel = new SectionModel();
        servermodel = new ServerModel();
	

		sections = sectionmodel.importSections(floorModel.getTableList());
		servers = servermodel.importServers( sections);
        queuemodel = new ServerQueueModel(servers);
        qview = new ServerQueueView(queuemodel);
        System.out.println(queuemodel.toString());
        queueController = new ServerQueueController(floorModel, floorView, queuemodel, guests, qview);
        
        subPanelEast.add(qview);
        subPanelEast.add(waitlistView);
        
        this.add(subPanelEast, BorderLayout.EAST);
        floorView.register(queueController);
        qview.register(queueController);
      
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               qview.createAndShowGui();
            }
         });
	}

	/**
	 * Imports pre-determined guests from a guests file
	 * 
	 * @param pathToGuestsFile path to the guests file
	 * @return a list of guests that were imported from the guests file
	 */
	public static ArrayList<GuestModel> importGuests(String pathToGuestsFile) {
		if (pathToGuestsFile == null) {
			return null;
		}
		
		Scanner inputFile = null;
		ArrayList<GuestModel> importedGuests = new ArrayList<GuestModel>();
		//Enable this variable if you want to limit the number of guests to
		//import. Also change the while condition and enable the variable
		//decrement at the end of the while loop
		//int numImportGuests = 9;
		
		try {
			inputFile = new Scanner(new File(pathToGuestsFile));
		} catch (Exception FileNotFoundException) {
			System.err.printf("Error: %s not found\n", inputFile);
			System.exit(1);
		}
		
		// skip the field descriptor line
		inputFile.nextLine();
		
		//System.out.println("Contents of guests file\n");
		
		GregorianCalendar now = new GregorianCalendar();
		
		// Use this while-condition to import some of the guests
		//while (inputFile.hasNextLine() && numImportGuests > 0) {
		// Use this while-condition to import all of the guests
		while (inputFile.hasNextLine()) {
			String nextGuest = inputFile.nextLine();
			Scanner guestScanner = new Scanner(nextGuest);
			guestScanner.useDelimiter(",");
			
			String name = guestScanner.next();
			String contact = guestScanner.next();
			int partySize = guestScanner.nextInt();
			
			int dayOffset = guestScanner.nextInt();
			int hourOffset = guestScanner.nextInt();
			int minuteOffset = guestScanner.nextInt();
			int secondOffset = guestScanner.nextInt();
			
			GregorianCalendar created = (GregorianCalendar) now.clone();
			created.add(Calendar.DAY_OF_MONTH, dayOffset);
			created.add(Calendar.HOUR, hourOffset);
			created.add(Calendar.MINUTE, minuteOffset);
			created.add(Calendar.SECOND, secondOffset);
			
			String rDayOffset = guestScanner.next();
			String rHourOffset = guestScanner.next();
			String rMinuteOffset = guestScanner.next();
			String rSecondOffset = guestScanner.next();
	
			GregorianCalendar reservationStart = (GregorianCalendar) now.clone();
			reservationStart.add(Calendar.DAY_OF_MONTH, (rDayOffset.equals("-") ? dayOffset : Integer.parseInt(rDayOffset)));
			reservationStart.add(Calendar.HOUR, (rHourOffset.equals("-") ? hourOffset : Integer.parseInt(rHourOffset)));
			reservationStart.add(Calendar.MINUTE, (rMinuteOffset.equals("-") ? minuteOffset : Integer.parseInt(rMinuteOffset)));
			reservationStart.add(Calendar.SECOND, (rSecondOffset.equals("-") ? secondOffset : Integer.parseInt(rSecondOffset)));
			
			String note;
	
			if (guestScanner.hasNext()) {
				note = guestScanner.next();
			} else {
				note = "";
			}
			
			GuestModel newGuest = null;
			try {
				newGuest = new GuestModel(name, note, contact, 
					partySize, created, reservationStart);
			} catch (Exception e) {
				
			}
			
			if (newGuest != null) { importedGuests.add(newGuest); }
			//System.out.println(nextGuest);
			guestScanner.close();
			//numImportGuests--;
		}
		
		inputFile.close();
		return importedGuests;
	}
	
	public ArrayList<GuestModel> getGuests() {
		return guests;
	}

	public ClockModel getClockModel() {
		return clockModel;
	}

	public ClockView getClockView() {
		return clockView;
	}

	public ClockController getClockController() {
		return clockController;
	}

	public FloorModel getFloorModel() {
		return floorModel;
	}

	public FloorView getFloorView() {
		return floorView;
	}

	public FloorController getFloorController() {
		return floorController;
	}

	public WaitlistModel getWaitlistModel() {
		return waitlistModel;
	}

	public WaitlistView getWaitlistView() {
		return waitlistView;
	}

	public SectionModel getSectionmodel() {
		return sectionmodel;
	}

	public ServerModel getServermodel() {
		return servermodel;
	}

	public ArrayList<SectionModel> getSections() {
		return sections;
	}

	public ArrayList<ServerModel> getServers() {
		return servers;
	}

	public ServerQueueModel getQueuemodel() {
		return queuemodel;
	}

	public ServerQueueView getQview() {
		return qview;
	}

	public WaitlistController getWaitlistController() {
		return waitlistController;
	}

	public ServerQueueController getQueueController() {
		return queueController;
	}
}
