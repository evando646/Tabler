package Server;

import java.util.*;

public class ServerModel {
	/**
	 * This is the name of the server the user has selected
	 */
    private String ServerName;
    
    /**
	 * This is an array of tables in a section for an employee
	 */
	private SectionObject Section;
	
	/**
	 * This is a queue to keep track of Server's who are
	 * available in a Fist In First Out order
	 */
	private LinkedList<ServerModel> addToQueue = new LinkedList<ServerModel>();
	
	/**
	 * This is a queue to keep track of Server's who have been previously
	 * removed from the upcoming seat queue
	 */
	private LinkedList<ServerModel> removedFromQueue = new LinkedList<ServerModel>();
	
	/**
	 * Initializes the instance variables.
	 */
	public ServerModel(String Name, String SectionName, ArrayList<Table> Tables) {
		ServerName = Name;
		Section = new SectionObject(SectionName, Tables);
		addToQueue.add(this);
	}

	/**
	 * This function returns a true or false value depending
	 * on if the server is next up
	 * Peak shows the head of the queue (least recent put in FIFO)
	 */
	public boolean isNext(){
		if (this.Section.isFull()){
			return false;
		}
		else 
			return true;
	}
	public void updateQueue(){
		
		for(int i = 0; i < removedFromQueue.size(); i++){
			if (removedFromQueue.get(i).Section.isFull() != true){
				int k = 0;
				while(addToQueue.get(k).Section.TimeLastSeated().before(removedFromQueue.get(i).Section.TimeLastSeated())){
					k++;
				}
				addToQueue.add(k,removedFromQueue.get(i));
			}
		}
		for (int i = 0; i < addToQueue.size(); i++){
			if(addToQueue.get(i).Section.isFull() == true){
				removedFromQueue.add(addToQueue.get(i));
				addToQueue.remove(i);
				continue;
			}
		}
		ServerModel lastAssigned = addToQueue.get(0);
		int moveIndex = 0;
		for(int i = 1; i < addToQueue.size(); i++){
			if(addToQueue.get(i).Section.TimeLastSeated().after(lastAssigned.Section.TimeLastSeated())){
				lastAssigned = addToQueue.get(i);
				moveIndex = i;
			}
		}
		update(addToQueue, lastAssigned, moveIndex);
		return;
		
	}
	public void update(LinkedList<ServerModel> queue, ServerModel last, int moveIndex){
		queue.remove(moveIndex);
		queue.add(last);
		return;
	}
	public ArrayList<String> getServerStrings(){
		ArrayList<String> string = new ArrayList<>();
		for (int i = 0; i < addToQueue.size(); i++){
			string.add(addToQueue.get(i).toString());
		}
		return string;
	}
}
