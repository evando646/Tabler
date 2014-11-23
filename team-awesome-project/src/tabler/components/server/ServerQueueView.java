package tabler.components.server;
import java.util.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;


import tabler.components.server.ServerQueueModel;

import java.util.Vector;

public abstract class ServerQueueView extends JFrame{
	JFrame frame = new JFrame();
	ArrayList<String> servers;
	DefaultListModel list;
	
	ServerQueueView (ServerQueueModel active){
		list = new DefaultListModel();

	}
}
