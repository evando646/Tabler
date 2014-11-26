package test;

import java.awt.Dimension;

import javax.swing.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.JFrame;


import javax.swing.table.TableModel;

import tabler.components.floor.*;
import tabler.components.floor.FloorModel;
import tabler.components.guest.*;
import tabler.components.server.*;

public class QueueTest {
	private static String guestsFile = "./src/test/guests.txt";
	//private JFrame frame;

	public static ArrayList<GuestModel> importGuests(String pathToGuestsFile) {
		if (pathToGuestsFile == null) {
			return null;
		}
		
		Scanner inputFile = null;
		ArrayList<GuestModel> importedGuests = new ArrayList<GuestModel>();
		
		try {
			inputFile = new Scanner(new File(guestsFile));
		} catch (Exception FileNotFoundException) {
			System.err.printf("Error: %s not found\n", inputFile);
			System.exit(1);
		}
		
		// skip the field descriptor line
		inputFile.nextLine();
		
		//System.out.println("Contents of guests file\n");
		
		GregorianCalendar now = new GregorianCalendar();
		
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
			
			GregorianCalendar created = new GregorianCalendar(
					now.get(Calendar.YEAR),	now.get(Calendar.MONTH), 
					now.get(Calendar.DAY_OF_MONTH) +  + dayOffset, 
					now.get(Calendar.HOUR) + hourOffset, 
					now.get(Calendar.MINUTE) + minuteOffset, 
					now.get(Calendar.SECOND) + secondOffset);
			
			String rDayOffset = guestScanner.next();
			String rHourOffset = guestScanner.next();
			String rMinuteOffset = guestScanner.next();
			String rSecondOffset = guestScanner.next();

			GregorianCalendar reservationStart = new GregorianCalendar(
					now.get(Calendar.YEAR), now.get(Calendar.MONTH),
					now.get(Calendar.DAY_OF_MONTH) + (rDayOffset.equals("-") ? dayOffset : Integer.parseInt(rDayOffset.toString())),
					now.get(Calendar.HOUR) + (rHourOffset.equals("-") ? hourOffset : Integer.parseInt(rHourOffset.toString())),
					now.get(Calendar.MINUTE) + (rMinuteOffset.equals("-") ? minuteOffset : Integer.parseInt(rMinuteOffset.toString())),
					now.get(Calendar.SECOND)+ (rSecondOffset.equals("-") ? secondOffset : Integer.parseInt(rSecondOffset.toString())));
			
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
		}
		
		inputFile.close();
		return importedGuests;
	}
	private static String tablesFile = "./src/test/tables.txt";
	private static String sectionsFile = "./src/test/sections.txt";
	private static String serversFile = "./src/test/servers.txt";
	/**
	 * Imports tables listed in the tables file
	 * 
	 * @param pathToTablesFile path to the file containing the list of tables
	 * @return a list of tables
	 */
	public static ArrayList<TableModel> importTables(String pathToTablesFile) {
		if (pathToTablesFile.equals(null)) {
			return null;
		}
		
		Scanner inputFile = null;
		ArrayList<TableModel> importedTables = new ArrayList<TableModel>();
		
		try {
			inputFile = new Scanner(new File(tablesFile));
		} catch (Exception FileNotFoundException) {
			System.err.printf("Error: %s not found\n", inputFile);
			System.exit(1);
		}
		
		// skip the field descriptor line
		inputFile.nextLine();
		
		//System.out.println("Contents of tables file\n");
		
		while (inputFile.hasNextLine()) {
			int tableNumber = 0;
			int tableCapacity = 0;
			int positionX = 0;
			int positionY = 0;
			
			//Modified my MW
			int width = 0;
			int height = 0;
			
			String nextTableEntry = inputFile.nextLine();
			Scanner tableScanner = new Scanner(nextTableEntry);
			
			tableNumber = tableScanner.nextInt();
			tableCapacity = tableScanner.nextInt();
			positionX = tableScanner.nextInt();
			positionY = tableScanner.nextInt();
			width = tableScanner.nextInt();
			height = tableScanner.nextInt();
			
			// When creating a new table, use a placeholder section value. The
			// section value will be added when the sections are imported.
			TableModel newTable = new TableModel("Unknown Section", tableNumber, 
												tableCapacity,
												positionX, positionY, width, height);
			
			importedTables.add(newTable);
			tableScanner.close();
			//System.out.println(inputFile.nextLine());
		}
		
		if (!inputFile.equals(null)) { inputFile.close(); }
		
		return importedTables;
	}
	/**
	 * Imports the sections listed in the sections file and assigns the specified
	 * tables to the sections as described in the file.
	 * 
	 * @param pathToSectionsFile path to the file containing the list of sections
	 * @param tables a list of tables to be assigned to the sections
	 * @return a list of sections with assigned tables
	 */
	public static ArrayList<SectionModel> importSections(String pathToSectionsFile, 
			ArrayList<TableModel> tables) {
		if (pathToSectionsFile.equals(null)) {
			return null;
		}
		
		Scanner inputFile = null;
		ArrayList<SectionModel> importedSections = new ArrayList<SectionModel>();
		
		try {
			inputFile = new Scanner(new File(sectionsFile));
		} catch (Exception FileNotFoundException) {
			System.err.printf("Error: %s not found\n", inputFile);
			System.exit(1);
		}
		
		// skip the field descriptor line
		inputFile.nextLine();
		
		//System.out.println("Contents of sections file\n");
		
		while (inputFile.hasNextLine()) {
			String sectionName = inputFile.next();
			
			SectionModel newSection = new SectionModel(sectionName);
			
			// Add the corresponding table (identified in the file by tableNumber)
			// to the new section AND updated the table's section number
			while (inputFile.hasNextInt()) {
				int nextTableNumber = inputFile.nextInt();
				
				for (TableModel table : tables) {
					if (table.getTableNumber() == nextTableNumber) {
						newSection.addTable(table);
						table.setSection(sectionName);
					}
				}
			}
			
			importedSections.add(newSection);
			
			//System.out.println(inputFile.nextLine());
		}
		
		return importedSections;
	}

	/**
	 * Imports the servers listed in the servers file and assign to each server a
	 * reference to the section they are responsible for.
	 * 
	 * @param pathToServersFile path to the file containing the list of servers
	 * @param sections a list of sections to be assigned to the servers
	 * @return a list of servers with assigned sections
	 */
	public static ArrayList<ServerModel> importServers(String pathToServersFile,
			ArrayList<SectionModel> sections) {
		if (pathToServersFile.equals(null)) {
			return null;
		}
		
		Scanner inputFile = null;
		ArrayList<ServerModel> importedServers = new ArrayList<ServerModel>();
		
		try {
			inputFile = new Scanner(new File(serversFile));
		} catch (Exception FileNotFoundException) {
			System.err.printf("Error: %s not found\n", inputFile);
			System.exit(1);
		}
		
		// skip the field descriptor line
		inputFile.nextLine();
		
		//System.out.println("Contents of servers file\n");
		
		while (inputFile.hasNextLine()) {
			String sectionName = inputFile.next();
			String serverName = inputFile.next();
			
			for (SectionModel section : sections) {
				if (section.getSectionName().equals(sectionName)) {
					ServerModel newServer = new ServerModel(serverName, section);
					importedServers.add(newServer);
				}
			}
			
			//System.out.println(inputFile.nextLine());
		}
		
		return importedServers;
	}
	public static void main(String[] args){
		
		ArrayList<GuestModel> guests = null;
		
		guests = importGuests(guestsFile);

		for (GuestModel guest : guests) {
			System.out.println(guest);
		}
		
		ArrayList<TableModel> tables = null;
		ArrayList<SectionModel> sections = null;
		ArrayList<ServerModel> servers = null;
		
		tables = importTables(tablesFile);
		sections = importSections(sectionsFile, tables);
		servers = importServers(serversFile, sections);
		for (ServerModel server : servers) {
			System.out.println(server);
		}
		
		for (SectionModel section : sections) {
			System.out.println(section);
		}
		
		for (TableModel table : tables) {
		System.out.println(table);
		}
		
        FloorModel model = new FloorModel(tables); 
        FloorView view = new FloorView(model.getTableList());

        
        ServerQueueModel queuemodel = new ServerQueueModel(servers);
        ServerQueueView qview = new ServerQueueView(queuemodel);
        System.out.println(queuemodel.toString());
        ServerQueueController queueController = new ServerQueueController(model, view, queuemodel, guests, qview);
        
        view.register(queueController);
        qview.register(queueController);
        
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setSize(1024,768);
        view.setVisible(true);
        
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               qview.createAndShowGui();
            }
         });

	}
}
