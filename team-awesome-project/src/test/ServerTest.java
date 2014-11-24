package test;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

//import javax.swing.*;

import tabler.components.server.SectionModel;
import tabler.components.server.ServerModel;
import tabler.components.server.TableModel;
//import tabler.components.server.ServerQueueView;



/**
 * The ServerTest class exercises the ServerModel class which (in turn)
 * exercises the SectionModel and TableModel classes
 * 
 * @author Kristin (krismndz)
 * @author Augustine (mr-augustine)
 *
 */
public class ServerTest {
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
												positionX, positionY);
			
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
	
	public static void main (String[] args){
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
		
		
		/*ServerQueueView window = new ServerQueueView();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(700, 700);
		window.setVisible(true);*/
		
	}
}