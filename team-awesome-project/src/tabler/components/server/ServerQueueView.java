package tabler.components.server;

import java.util.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.JOptionPane;


import tabler.components.server.ServerQueueModel;


public class ServerQueueView extends JFrame{
	JFrame frame = new JFrame();
	ArrayList<String> servers;
	DefaultListModel list;
	public ServerQueueView (){
	}
	public int showOptions(String s){
		
		return JOptionPane.showConfirmDialog(null,
	             "Are you sure you want to seat table "+ s +"?", "choose one", JOptionPane.YES_NO_OPTION);
	}
}