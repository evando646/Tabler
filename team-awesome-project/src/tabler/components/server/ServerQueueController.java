package tabler.components.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import tabler.FSM;
import tabler.components.floor.*;
import tabler.components.table.*;
import tabler.components.guest.*;

public class ServerQueueController implements ActionListener{
	
	public FloorModel model;
	public FloorView view;
	public ServerQueueModel queuemodel;
	public ArrayList<TableModel> tables;
	public ArrayList<GuestModel> guests;
	
	public ServerQueueController( FloorModel model, FloorView view, ServerQueueModel queuemodel, ArrayList<GuestModel>guests){
		this.model = model;
		this.view = view;
		this.queuemodel = queuemodel;
		this.tables = model.getTableList();
		this.guests= guests;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println(event.getActionCommand());
		int len = tables.size();
		for(int i = 0; i < len; i++){
			if( tables.get(i).getTableNumber() == Integer.parseInt(event.getActionCommand())){
				queuemodel.updateQueue(tables.get(i));
				tables.get(i).assignGuest(guests.get(i));
			}
			else{
				continue;
			}
		}
	}

}
