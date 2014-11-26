package tabler.components.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.GregorianCalendar;

import tabler.components.table.*;
import tabler.components.floor.*;
import tabler.components.guest.*;

public class ServerQueueModel{
	public static LinkedList<ServerModel> activeQueue;
	public static ArrayList<ServerModel> fullServers;
	
	public ServerQueueModel(){
		activeQueue = new LinkedList<ServerModel>();
	}
	/**
	 * Constructor for initial creation and addition of servers
	 * @param ArrayList<ServerModel>servers
	 */
	public ServerQueueModel(ArrayList<ServerModel> servers) {
		//1. Create queue 
		activeQueue = new LinkedList<ServerModel>();
		//2. Put ArrayList of ServerModels in active
		activeQueue.addAll(servers);
		
		fullServers = new ArrayList<ServerModel>();
		
		
	}
	
	/**
	 * Called upon table button selection
	 * Method updateActiveQueue updates activeQueue for the server who's table is sent in
	 * @param table
	 */
	public static void updateQueue(TableModel table){
		String section = table.getSectionName();

		int len = activeQueue.size();
		for (int i = 0; i < len; i ++){
			
			if (activeQueue.get(i).assignedSection.sectionName.equals(section)){
				dequeue(activeQueue.get(i));
				break;
			}
		}
		
	}
	
	/**
	 * Called upon table state change
	 * Method updateFullServers receives a table who's state has just changed
	 * and removes or places ServerModel objects from the fullServers List
	 * depending on table state consequence
	 * @param  table
	 */
	public void updateFullServers(TableModel table){
		
	}
	/**
	 * Constructor for post initial addition and deletion of ServerModels
	 * @param TableModel table
	 */
	public void addQueue(ServerModel server){
		//1. Add server to queue
		GregorianCalendar tServer = server.assignedSection.timeLastSeated();
		int len = activeQueue.size();
		int i = 0;
		for (i = 0; i <len; i++){
			ServerModel curr = activeQueue.get(i);
			if (curr.assignedSection.timeLastSeated().after(tServer)){
				continue;
			}
			else{
				
				break;
			}
		}
		activeQueue.add(i, server);
		System.out.printf("Added %s to activeQueue\n", server.getServerName());
		System.out.println("ActiveQueue=" + activeQueue.toString());
	}
	
	public static void dequeue(ServerModel server){
		int i = activeQueue.indexOf(server);
		activeQueue.remove(i);
		if(server.assignedSection.almostFull() == true){
			fullServers.add(server);
			System.out.printf("Added %s to fullServers\n", server.getServerName());
			System.out.println("ActiveQueue=" + activeQueue.toString());
			System.out.println("FullServers=" + fullServers.toString());
			
			return;
		}
		else
			activeQueue.add(server);
			System.out.printf("%s is the tail of activeServers\n", server.getServerName());
			System.out.println("ActiveQueue=" + activeQueue.toString());
		return;
	}
	
	public String toString(){
		int len = activeQueue.size();
		String servers[] = new String[len];
		
		for (int i = 0; i < len; i++){
			servers[i] = activeQueue.get(i).serverName;
		}
		return Arrays.toString(servers);
		
	}

	public ServerModel isNext(){
		return activeQueue.peekFirst();
	}
	public static LinkedList<ServerModel> getList(){
		return activeQueue;
	}
	public int getSize(){
		return activeQueue.size();
	}
}