package tabler.components.server;

import java.util.*;

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
}
