package tabler.components.server;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import tabler.components.table.*;
import tabler.components.floor.*;
import tabler.components.guest.*;


/**
 * The ServerModel represents a server that tends to a section of tables
 * 
 * @author Kristin (krismndz)
 * @author Augustine (mr-augustine)
 *
 */
public class ServerModel {
	private static String serversFile = "./src/test/servers.txt";
	/**
	 * This is the name of the server the user has selected
	 */
    public String serverName;
    
    /**
	 * This is an array of tables in a section for an employee
	 */
	public SectionModel assignedSection;
	public ServerModel(){
		
	}
	
	/**
	 * Initializes the instance variables.
	 */
	public ServerModel(String name, SectionModel section) {
		this.serverName = name;
		this.assignedSection = section;
	}
	
	/**
	 * Accessor function for the server's name
	 * 
	 * @return the server's name
	 */
	public String getServerName() {
		return serverName;
	}
	
	/** Accessor function for the server's assigned section
	 * 
	 * @return the server's assigned section
	 */
	public SectionModel getSection() {
		return assignedSection;
	}
	
	/**
	 * Creates a String representation of a ServerModel object
	 * 
	 * @return the server's String representation
	 */
	public String toString() {
		String objectString = null;
		
		objectString = "[ServerModel: name=" + getServerName() + ", section="
				+ getSection().getSectionName() + "]";
		
		return objectString;
	}
	public static ArrayList<ServerModel> importServers(
			ArrayList<SectionModel> sections) {
		if (serversFile.equals(null)) {
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
}
