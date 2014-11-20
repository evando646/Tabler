package test;

import java.io.*;
import java.util.*;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.GregorianCalendar;
import java.io.File;
import java.util.ArrayList;

import tabler.components.server.SectionModel;
import tabler.components.server.ServerModel;
import tabler.components.server.TableModel;



public class QueueTester {
	/**
	 * 
	 * @param args
	 *
	 */

	public static void main(String[] args) {
		
		Scanner in = null;
		File file = null;
		
		Scanner in2 = null;
		File file2 = null;
		
		Scanner server = null;
		File sFile = null;
		
		Scanner table = null;
		File tFile = null;
		
		Scanner section = null;
		File secFile = null;
		
		
		try {
			file2 = new File("./src/test/servers.txt");
			in2 = new Scanner (file2);
		}catch(FileNotFoundException e){
			System.err.print("Could not file servers.txt");
		}
		
		String[] title = in2.nextLine().split(" ");
		ArrayList <ServerModel> servers = new ArrayList<>();

		while(in2.hasNextLine()){
			String [] lines  = in2.nextLine().split(" ");
			SectionModel newSection = new SectionModel(lines[0]);
			ServerModel newServer = new ServerModel(lines[1], newSection);
			
			servers.add(newServer);
		}
		
		in2.close();
		
		try{
			secFile = new File("./src/test/sections.txt");
			section = new Scanner(secFile);
		}catch(FileNotFoundException e){
			System.out.print("could not find sections.txt");
		}
		int i = 0;
		String titleSection = section.nextLine();
		while(section.hasNextLine()){
			SectionModel temp = servers.get(i).assignedSection;
			String [] readSections = section.nextLine().split("/");
			if (temp.sectionName.equals(readSections[0])){
				int length = readSections[1].length();
				ArrayList<TableModel> tablesList = new ArrayList();
				for(int k = 0; k )
			}
			
		}
		
		int talbe = 0;
		int p = 0;
		
		try {
			file = new File("./src/test/queuedata.txt");
			in = new Scanner(file);
			
		}catch (FileNotFoundException e){
			System.err.print("queuedata.txt not found");
		}
		String[] first =  in.nextLine().split(" ");
		System.out.printf(Arrays.toString(first) + "\n");
		while (in.hasNextLine()){
			String line = in.nextLine();
			String[] ary = line.split(" ");
			GregorianCalendar calendar= new GregorianCalendar(TimeZone.getDefault());
			
			talbe = Integer.parseInt(ary[0]);
			p = Integer.parseInt(ary[1]);
			
			calendar.add(Calendar.MINUTE, n1);
			
			System.out.printf(Arrays.toString(ary) + " ");
			System.out.printf("%d:%d:%d %d\n", calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND), calendar.get(GregorianCalendar.AM_PM));
			

		}
		in.close();
		

		
		
		
	}
	
		
	}
