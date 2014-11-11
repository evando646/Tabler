package tabler.components.server;

import java.util.*;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;


public class ServerQueueView extends JFrame{
	

	public ServerModel model;
	public JPanel paneleast;
	public JFrame frame;
	public DefaultListModel<String> listModel;
	public ArrayList<ServerModel> serverModel = new ArrayList<>();
	public ServerQueueView(){
		JFrame frame = new JFrame();
		JPanel paneleast = new JPanel();
		
		add(paneleast, BorderLayout.EAST);
		paneleast.setBackground(Color.CYAN);
		
		Scanner in1 = null;
	      try {
	          in1 = new Scanner(new File("./src/tabler/components/server/serverdata.txt"));
	       } catch (Exception FileNotFoundException) {
	          System.err.println("failed to open serverdata.txt");
	          System.exit(1);
	       }

	    ArrayList<String> sections = new ArrayList<>();
	    ArrayList<String> names = new ArrayList<>();
		ArrayList<Integer> tables = new ArrayList<>();
		serverModel = new ArrayList<>();
	    listModel = new DefaultListModel<String>();
	    
		while (in1.hasNextLine()){	
	    	sections.add(in1.next());
	    	names.add(in1.next());
		}
		
		Scanner in2 = null;
	      try {
	          in2 = new Scanner(new File("./src/tabler/components/server/sections.txt"));
	       } catch (Exception FileNotFoundException) {
	          System.err.println("failed to open sections.txt");
	          System.exit(1);
	       }
	    int i = 0;
	    String tempSection;
	    while (in2.hasNextLine()){	
	    	tempSection = in2.next();
	    	while(in2.hasNextInt()){
	    		if(tempSection.equals(sections.get(i))){
	   				tables.add(in2.nextInt());
	   			}
	   		}
	    	
	    	ServerModel newModel = new ServerModel(names.get(i), sections.get(i), tables);
	    	serverModel.add(newModel);
	    	listModel.addElement(newModel.ServerName);
	    	tables.clear();
	    	i++;
	    }
		JButton button1 = new JButton(serverModel.get(0).Section.tableSeatAvailable().get(0));

		JList<String> serverList = new JList<String>(listModel);
		serverList.setVisibleRowCount(1);
		serverList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		paneleast.add(new JScrollPane(serverList), BorderLayout.EAST);

		paneleast.add(button1);
		
	}
	
	/**
	 * *
	public ServerModel getSelectedServer(){
		String s = listModel.get(serverList.getSelectedIndex());
		ServerModel ret = serverModel.get(0);
		for (int i = 1; i < listModel.size(); i++){
			if(ret.ServerName.equals(s)){
				break;
			}
			else{
			ret = serverModel.get(i);
			}
		}
		return ret;
	}*/
	
	public void updateList(LinkedList <ServerModel> queue){
		listModel.clear();
		for (int i = 0; i < queue.size(); i++){
			listModel.addElement(queue.get(i).ServerName);
		}
	}
}
