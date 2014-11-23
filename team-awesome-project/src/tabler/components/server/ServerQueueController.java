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
	public ServerQueueView queueview;
	public ArrayList<TableModel> tables;
	public ArrayList<GuestModel> guests;
	
	public ServerQueueController( FloorModel model, FloorView view, ServerQueueModel queuemodel, ArrayList<GuestModel>guests,
				ServerQueueView queueview){
		this.model = model;
		this.view = view;
		this.queuemodel = queuemodel;
		this.tables = model.getTableList();
		this.guests= guests;
		this.queueview = queueview;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String e = event.getActionCommand();
		int confirm = queueview.showOptions(e);
		if(confirm == 1){
			System.exit(0);
		}
	
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
