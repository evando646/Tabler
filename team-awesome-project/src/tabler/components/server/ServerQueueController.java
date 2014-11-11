package tabler.components.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.*;
import java.awt.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.DefaultListModel;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class ServerQueueController implements ActionListener{
	

	private TableView tableView;
	private WaitlistView waitView;
	
	public ServerQueueController ( TableView tableView, WaitlistView waitView){
		this.tableView = tableView;
		this.waitView = waitView;
	}
	
	public void actionPreformed(ActionEvent e){

		ServerModel.updateQueue();
		ServerQueueView.updateList(ServerModel.addToQueue);
	}
	
}
