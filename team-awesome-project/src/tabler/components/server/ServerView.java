package tabler.components.server;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

public class ServerView extends JFrame{

	public ServerView(){
		JFrame frame = new JFrame();
		JPanel paneleast = new JPanel();


		
		JButton button1 = new JButton("skip");
		JButton button2 = new JButton("use");
		
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
	    	if(tempSection.equals(sections.get(i))){
	    		while(in2.hasNextInt()){
	    			tables.add(in2.nextInt());
	    		}
	    		ServerModel newModel = new ServerModel(names.get(i), sections.get(i), tables);
		}
	    
	    }
		String[]serverNames = new String [names.size()];
		for (int n = 0; n < names.size(); n++){
			serverNames[n] = names.get(i);
		}

		JList serverList = new JList(serverNames);
		serverList.setVisibleRowCount(1);
		serverList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		paneleast.add(button1);
		paneleast.add(new JScrollPane(serverList));
		paneleast.add(button2);
		
	}
}
