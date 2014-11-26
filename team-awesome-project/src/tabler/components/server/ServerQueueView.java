package tabler.components.server;

import java.util.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import tabler.components.table.*;
import tabler.components.floor.*;
import tabler.components.guest.*;



public class ServerQueueView extends JFrame{
	
	public JPanel panel;
	public JList list;
	public DefaultListModel lModel;
	public JScrollPane pane;
	public JButton choose;
	public JButton skip;
	public JButton prev;
	public JButton revert;
	public JScrollBar bar;
	public ArrayList<String> servers;
	public String [] possibleValues;
	int visibleIndex;
	
	public ServerQueueView (ServerQueueModel model){
		setLayout(new BorderLayout());
		servers = new ArrayList<String>();
		
		int len = model.getSize();

		for(int i = 0; i < len; i++){
			this.servers.add(model.getList().get(i).getServerName());
		}
		
		lModel = new DefaultListModel();
		list = new JList<String>(lModel);
		for(int i = 0; i < len; i ++){
			lModel.addElement(servers.get(i));

		}
		
		JScrollPane pane = new JScrollPane();
		pane.getViewport().setView(list);
		this.visibleIndex = 0;
        this.list.ensureIndexIsVisible(0);
		choose = new JButton("choose");
		skip = new JButton("skip");
		prev = new JButton("previous");
		revert = new JButton("revert");
		
		pane.setViewportView(list);

		
	}
	public void createAndShowGui(){
		
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(1);
		list.ensureIndexIsVisible(this.visibleIndex);

		pane = new JScrollPane(list);
		pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        JFrame frame = new JFrame("Foo001");
        

	    frame.add(choose, BorderLayout.NORTH);
	    frame.add(skip, BorderLayout.EAST);
	    frame.add(prev, BorderLayout.WEST);
	    frame.add(revert, BorderLayout.SOUTH);
        frame.getContentPane().add(pane, BorderLayout.CENTER);
        

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}
	
	public void editDefaultList(ServerQueueModel model){
		this.servers.clear();
		this.lModel.clear();
		int len = model.getSize();

		for(int i = 0; i < len; i++){
			this.servers.add(model.getList().get(i).getServerName());
		}
		
		this.lModel = new DefaultListModel();
		this.list = new JList<String>(lModel);
		for(int i = 0; i < len; i ++){
			lModel.addElement(servers.get(i));
		}
		createAndShowGui();
		
	}
	public int getChosenIndex(){
		return this.visibleIndex;
	}
	public void skip(ServerQueueModel model){
		if(model.getSize() == 0){
			System.out.println("Queue is empty");
			return;
		}
		if (this.visibleIndex == model.getSize()-1){
			this.visibleIndex = 0;
		}
		else{
			this.visibleIndex++;
		}
		createAndShowGui();
	}
	
	public void revert(ServerQueueModel model){
		if(model.getSize() == 0){
			System.out.println("Queue is empty");
			return;
		}
		this.visibleIndex = 0;

		createAndShowGui();
	}
	
	public void viewPrev(ServerQueueModel model){
		if(model.getSize() == 0){
			System.out.println("Queue is empty");
			return;
		}
		if (this.visibleIndex == 0){
			this.visibleIndex = model.getSize()-1;
		}else{
			this.visibleIndex--;
		}
		createAndShowGui();
	}
	
	
	public void register(ServerQueueController controller){
		choose.addActionListener(controller);
		skip.addActionListener(controller);
		prev.addActionListener(controller);
		revert.addActionListener(controller);
	}
	/**public String chooseTablePopUp(ServerModel server){
		ArrayList<TableModel> TablesList = server.getSection().getTableList();
		int len = TablesList.size();
		possibleValues = new String[len];
		for(int i = 0; i < len; i ++){
			int temp = TablesList.get(i).getTableNumber();
			String add = Integer.toString(temp);
			possibleValues[i] = add;
		}
		
		
		TablesList.toArray(new String[TablesList.size()]);
		String selectedValue = (String) JOptionPane.showInputDialog(null,"Choose one", "Input", JOptionPane.INFORMATION_MESSAGE, null,
				possibleValues, possibleValues[0]);
		return selectedValue;
	}*/
	public int showOptions(String s){
		
		return JOptionPane.showConfirmDialog(null,
	             "Are you sure you want to " + s + "?", "choose one", JOptionPane.YES_NO_OPTION);
	}
	
	public void unavailableError(String s){
		 JOptionPane.showMessageDialog(null, "Invalid table. " +s+ " is currently occupied." , "Error", JOptionPane.ERROR_MESSAGE);
	}
}