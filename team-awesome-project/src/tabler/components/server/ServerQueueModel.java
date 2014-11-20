package tabler.components.server;

import java.util.ArrayList;
import java.util.LinkedList;

import tabler.components.server.ServerModel;

public class ServerQueueModel {
	private LinkedList<ServerModel> activeQueue;
	private ArrayList<ServerModel> fullServers;
	
	/**
	 * Constructer for initial creation and addition of servers
	 * @param ArrayList<ServerModel>servers
	 */
	public ServerQueueModel(ArrayList<ServerModel> servers) {
		//1. Create queue 
		activeQueue = new LinkedList<ServerModel>();
		//2. Put ArrayList of ServerModels in active
		activeQueue.addAll(servers);
		
		
	}
	/**
	 * Constructer for post initial addition and deletion of ServerModels
	 * @param TableModel table
	 */
	public void addQueue(ServerModel server){
		//1. Add server to queue
		activeQueue.add(server);
	}
	
	public void dequeue(ServerModel server){
		int i = activeQueue.indexOf(sever);
		activeQueue.remove(i);
		if(server.assignedSection.isFull() == true){
			fullServers.add(server);
		}
		else
			activeQueue.add(server);
		return;
	}
	
	public String toString(){
		int len = activeQueue.size();
		ArrayList<String> servers = new ArrayList<>();
		for (i = 0; i < len; i++){
			servers.add(activeQueue.get(i).serverName;
			System.out.println(servers.get(i));
		}
		
	}

	public ServerModel isNext(){
		if (this.assignedSection.isFull()){
			return false;
		}
		else 
			return true;
	}
	
}