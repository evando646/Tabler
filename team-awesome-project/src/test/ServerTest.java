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
		
		try {
			inputFile = new Scanner(new File(tablesFile));
		} catch (Exception FileNotFoundException) {
			System.err.printf("Error: %s not found\n", inputFile);
			System.exit(1);
		}
		
		System.out.println("Contents of tables file\n");
		
		while (inputFile.hasNextLine()) {
			System.out.println(inputFile.nextLine());
		}
		
		return null; //new ArrayList<TableModel>();
	}
	
	public static ArrayList<SectionModel> importSections(String pathToSectionsFile) {
		if (pathToSectionsFile.equals(null)) {
			return null;
		}
		
		Scanner inputFile = null;
		
		try {
			inputFile = new Scanner(new File(sectionsFile));
		} catch (Exception FileNotFoundException) {
			System.err.printf("Error: %s not found\n", inputFile);
			System.exit(1);
		}
		
		System.out.println("Contents of sections file\n");
		
		while (inputFile.hasNextLine()) {
			System.out.println(inputFile.nextLine());
		}
		
		return null; //return new ArrayList<SectionModel>();
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
		
		System.out.println("Contents of servers file\n");
		
		while (inputFile.hasNextLine()) {
			System.out.println(inputFile.nextLine());
		}
		
		return null; //return new ArrayList<ServerModel>();
	}
	
	public static void main (String[] args){
		importTables(tablesFile);
		importSections(sectionsFile);
		importServers(serversFile);
	
		/*ServerQueueView window = new ServerQueueView();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(700, 700);
		window.setVisible(true);*/
		
	}
}