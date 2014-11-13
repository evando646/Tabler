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
	
	// TODO actually create objects based on data ingested from the file
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
			
			String nextTableEntry = inputFile.nextLine();
			Scanner tableScanner = new Scanner(nextTableEntry);
			
			tableNumber = tableScanner.nextInt();
			tableCapacity = tableScanner.nextInt();
			positionX = tableScanner.nextInt();
			positionY = tableScanner.nextInt();
			
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

	public static ArrayList<ServerModel> importServers(String pathToServersFile) {
		if (pathToServersFile.equals(null)) {
			return null;
		}
		
		Scanner inputFile = null;
		
		try {
			inputFile = new Scanner(new File(serversFile));
		} catch (Exception FileNotFoundException) {
			System.err.printf("Error: %s not found\n", inputFile);
			System.exit(1);
		}
		
		// skip the field descriptor line
		inputFile.nextLine();
		
		System.out.println("Contents of servers file\n");
		
		while (inputFile.hasNextLine()) {
			System.out.println(inputFile.nextLine());
		}
		
		return null; //return new ArrayList<ServerModel>();
	}
	
	public static void main (String[] args){
		ArrayList<TableModel> tables = null;
		
		tables = importTables(tablesFile);
		//importSections(sectionsFile, null);
		//importServers(serversFile);
		
		for (TableModel table : tables) {
			System.out.println(table);
		}
	
		/*ServerQueueView window = new ServerQueueView();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(700, 700);
		window.setVisible(true);*/
		
	}
}