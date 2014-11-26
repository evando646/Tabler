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

	
	public ServerQueueController( FloorModel model, FloorView view, ServerQueueModel queuemodel, ArrayList<GuestModel>guests,
				ServerQueueView queueview){

		this.model = model;

		this.view = view;

		this.queuemodel = queuemodel;
	
		//this.tables = model.getTableList();
	
		this.guests= guests;
	
		this.queueview = queueview;

	}

	@Override

	public void actionPerformed(ActionEvent event) {
		System.out.println("Pressed " + getActionString(event) + "\n");
		String e = getActionString(event);
		if (e.equals("choose") || e.equals("skip")
				||e.equals("revert") || e.equals("previous")){
			listAction(e);
			return;
		}
		
		int confirm = queueview.showOptions("table " + e);
		
		if(confirm == 1){

			return;
	
		}
		int len = tables.size();
	
		for(int i = 0; i < len; i++){
		
			if( tables.get(i).getTableNumber() == Integer.parseInt(event.getActionCommand())){
				if(tables.get(i).isOccupied() == true){
					queueview.unavailableError(e);
					return;
				}
				ServerQueueModel.updateQueue(tables.get(i));

				tables.get(i).assignGuest(guests.get(i));
				}
			
			else{
				continue;
			}
	
		}

	}
	public String getActionString(ActionEvent event){
		String e = event.getActionCommand();
		return e;
	}
	
	public void listAction(String e){
		
		if (e.equals("skip")){
			queueview.skip(queuemodel);
			return;
		} 
		else if (e.equals("choose")){
			int confirm = queueview.showOptions(e + " server (servername)");
			if(confirm == 0){//means yes
				int index = queueview.getChosenIndex();
				/**String table = queueview.chooseTablePopUp(queuemodel.getList().get(index));*/
				ServerQueueModel.dequeue(queuemodel.getList().get(index));
				queueview.editDefaultList(queuemodel);
				return;
			}
			else{
				return;
			}
			
		}
		else if (e.equals("previous")){
			queueview.viewPrev(queuemodel);
			return;
		}
		else if (e.equals("revert")){
			queueview.revert(queuemodel);
			return;
		}
	}


}

