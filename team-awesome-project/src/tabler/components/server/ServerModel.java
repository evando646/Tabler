package tabler.components.server;

import java.util.*;

public class ServerModel {
	/**
	 * This is the name of the server the user has selected
	 */
    public String ServerName;
    
    /**
	 * This is an array of tables in a section for an employee
	 */
	public SectionObject Section;
	
	/**
	 * This is a queue to keep track of Server's who are
	 * available in a Fist In First Out order
	 */
	public LinkedList<ServerModel> addToQueue = new LinkedList<ServerModel>();
	
	/**
	 * This is a queue to keep track of Server's who have been previously
	 * removed from the upcoming seat queue
	 */
	public LinkedList<ServerModel> removedFromQueue = new LinkedList<ServerModel>();
	
	/**
	 * Initializes the instance variables.
	 */
	public ServerModel(String Name, String SectionName, ArrayList<Integer> Tables) {
		this.ServerName = Name;
		this.Section = new SectionObject(SectionName, Tables);
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
		/**
		 * Adds any removed ServerModels back to the queue when sections are not full
		 */
		for(int i = 0; i < removedFromQueue.size(); i++){

			if (removedFromQueue.get(i).Section.isFull() != true){
				int k = 0;
				while(addToQueue.get(k).Section.TimeLastSeated() < removedFromQueue.get(i).Section.TimeLastSeated()){
					k++;
				}
				addToQueue.add(k,removedFromQueue.get(i));
			}
		}
		/**
		 * Removes any servers with full sections from the available queue
		 */
		for (int i = 0; i < addToQueue.size(); i++){
			if(addToQueue.get(i).Section.isFull() == true){
				removedFromQueue.add(addToQueue.get(i));
				addToQueue.remove(i);
				continue;
			}
		}
		/**
		 * Calculates the servermodel with the most recent assigned table time (needs date accommodation)
		 */
		ServerModel lastAssigned = addToQueue.get(0);
		int moveIndex = 0;
		for(int i = 1; i < addToQueue.size(); i++){
			if(addToQueue.get(i).Section.TimeLastSeated() > lastAssigned.Section.TimeLastSeated()){
				lastAssigned = addToQueue.get(i);
				moveIndex = i;
			}
		}
		update(addToQueue, lastAssigned, moveIndex);
		return;
		
	}
	/**
	 * Moves the most recent assigned servermodel to end of queue
	 * @param queue
	 * @param last
	 * @param moveIndex
	 */
	public void update(LinkedList<ServerModel> queue, ServerModel last, int moveIndex){
		queue.remove(moveIndex);
		queue.add(last);
		return;
	}
}
