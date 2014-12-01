package tabler.components.server;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;


import tabler.components.table.*;
import tabler.components.floor.*;
import tabler.components.guest.*;


public class ServerQueueController implements ActionListener{

	
	public FloorModel model;
	public FloorView view;
	public ServerQueueModel queuemodel;
	public ServerQueueView queueview;
	public ArrayList<TableModel> tables;
	public ArrayList<GuestModel> guests;

	/**
	 * ServerQueueController constructor
	 * @param model
	 * @param view
	 * @param queuemodel
	 * @param guests
	 * @param queueview
	 */
	public ServerQueueController( FloorModel model, FloorView view, ServerQueueModel queuemodel, ArrayList<GuestModel>guests,
				ServerQueueView queueview){

		this.model = model;
		this.view = view;
		this.queuemodel = queuemodel;	
		this.tables = model.getTableList();
		this.guests= guests;
		this.queueview = queueview;

	}

	/**
	 * actionPreformed takes in an event and evaluates to the correct change 
	 * to be implemented
	 * @param ActionEvent event
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println("Pressed " + getActionString(event) + "\n");
		String e = getActionString(event);
		
		//preliminary check for isEmpty() to avoid any NullPointerException errors
		if(queuemodel.isEmpty()){
			queueview.WaitError();
			return;
		}
		
		//Implement listAction if any one of these buttons are pressed
		if (e.equals("view") || e.equals("skip")
				||e.equals("hide") || e.equals("previous") || e.equals("view all")){
			listAction(e);
			return;
		}
		
		//if not sent to listAction, user is prompted to confirm decision to seat table
		int confirm = queueview.showOptions("table " + e);
		if(confirm == 1){
			return;
		}
		
		//size of total number of tables
		int len = tables.size();
	
		for(int i = 0; i < len; i++){
			
			//find table that corresponds to tableButton pressed
			if(tables.get(i).getTableNumber() == Integer.parseInt(event.getActionCommand())){
				
				//respond with error if table is already taken
				if(tables.get(i).isOccupied() == true){
					queueview.unavailableError(e);
					return;
				}
				
				//otherwise update activeQueue using information extracted from selected tableModel
				queuemodel.updateQueue(tables.get(i));
				
				//Temporary call for testing purposes
				//Assign the guest to note table as OCCUPIED
				tables.get(i).assignGuest(guests.get(i));
		
				//Sets all table button borders to empty
				view.editBorders(tables, "hide");
				
				//once activeQueue is updated, update and show the queueView with the re-
				//organized list data
				queueview.editShownList(queuemodel);
				
				int index = queueview.getChosenIndex();
				//Temporary test check 
				if(queuemodel.isEmpty()){
					queueview.WaitError();
					return;
				}
				
				/**view.editBorders(tables, "show");*/
				 System.out.printf("Visible Index: %d", queueview.getChosenIndex());
				}
			
			else{
				continue;
			}
		}
	}
	/**
	 * getActionString() method returns the string of the action occured
	 * @param event
	 * @return String instance of the event- button pressed
	 */
	public String getActionString(ActionEvent event){
		String e = event.getActionCommand();
		return e;
	}
	/**
	 * listAction() method results in commanding changes for any 
	 * direct ServerQueueView buttons being pressed. 
	 * Creates bold outlines on specified serverModel's available tables
	 * to be shown on the FloorView
	 * @param String e : the actionEvent string
	 */
	public void listAction(String e){
		
		int index = queueview.getChosenIndex();
		ArrayList<TableModel> tables = queuemodel.getList().get(index).getSection().availableTables();
		if (e.equals("skip")){
			if(queueview.allViewed()){
				ArrayList<TableModel> all = queueview.getAll();
				view.editBorders(all, "hide");
				queueview.setAll(false);
			}
			else {
				view.editBorders(tables, "hide");
			}
			queueview.skip(queuemodel);
			index = queueview.getChosenIndex();
			tables.clear();
			tables = queuemodel.getList().get(index).getSection().availableTables();
			/**view.editBorders(tables, e);*/
			System.out.printf("Visible Index: %d", index);
			return;
		} 
		else if (e.equals("view")){
			if(queueview.allViewed()){
				ArrayList<TableModel> all = queueview.getAll();
				view.editBorders(all, "hide");
				queueview.setAll(false);
			}
			index = queueview.getChosenIndex();
			tables = queuemodel.getList().get(index).getSection().availableTables();
			view.editBorders(tables, e);
			 System.out.printf("Visible Index: %d", index);
			return;
		}
		else if (e.equals("previous")){
			if(queueview.allViewed()){
				ArrayList<TableModel> all = queueview.getAll();
				view.editBorders(all, "hide");
				queueview.setAll(false);
			}
			else {
				view.editBorders(tables, "hide");
			}
			queueview.viewPrev(queuemodel);
			index = queueview.getChosenIndex();
			tables.clear();
			tables = queuemodel.getList().get(index).getSection().availableTables();
			 System.out.printf("Visible Index: %d", index);
			/**view.editBorders(tables, e);*/
			return;
		}
		else if (e.equals("hide")){
			if(queueview.allViewed()){
				ArrayList<TableModel> all = queueview.getAll();
				view.editBorders(all, "hide");
				queueview.setAll(false);
			}
			else{
				view.editBorders(tables, e);
				queueview.revert(queuemodel);
				System.out.printf("Visible Index: %d", index);
				return;
			}
		}
		else if (e.equals("view all")){
			ArrayList<TableModel> all = queueview.viewAll(model.getTableList());
			queueview.setAll(true);
			view.editBorders(all, e);
		}
	}


}
