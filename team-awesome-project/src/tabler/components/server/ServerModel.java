package tabler.components.server;

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
	/**
	 * This is the name of the server the user has selected
	 */
    public String serverName;
    
    /**
	 * This is an array of tables in a section for an employee
	 */
	public SectionModel assignedSection;
	
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
}
