package tabler.components.guest;



//import thisSwitcherControler;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import java.awt.BorderLayout;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTable;

public class AddGuestView extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtEnterHere;//Text Field for Name of Party
	private JTextField textField_1;//Text field for Contact name
	private JTextField textField;//text field for Party size
	private JButton btnEnterGuest;
	private JTextArea textArea;
	
	private JLabel lblDate;//JLabel for the Date
	private JLabel lblTime;//JLabel for the Time
	
	private int yearAdd;
	private int dayAdd;
	private int monthAdd;
	private int hourAdd;
	private int minuteAdd;
	
	private JButton btnMP;//Month plus button
	private JButton btnMM;//month minus button
	private JButton btnDP;//Day plus button
	private JButton btnDM;//Day minus button
	private JButton btnYP;//Year plus button
	private JButton btnYM;//Year minus button 
	private JButton btnHP;//hour plus button 
	private JButton btnHM;//hour minus button
	private JButton btnMinM;//minute minus button
	private JButton btnMinP;//minute plus button
	
	private GregorianCalendar timeOfView; 
	
	
	/**
	 * Create the application.
	 */
	public AddGuestView() {
		yearAdd=0;
		dayAdd=0;
		monthAdd=0;
		monthAdd=0;
		hourAdd=0;
		minuteAdd=0;
		initialize();
		clock();
	}
	
	
	public void clock(){
		Thread clock=new Thread(){
		public void run(){
			try{
				while(true){
				GregorianCalendar time=new GregorianCalendar();
				time.add(Calendar.YEAR, yearAdd);
				time.add(Calendar.MONTH, monthAdd);
				time.add(Calendar.DAY_OF_MONTH,+dayAdd);
				time.add(Calendar.HOUR_OF_DAY, hourAdd);
				time.add(Calendar.MINUTE, minuteAdd);
				
				int year=time.get(Calendar.YEAR);
				int month= time.get(Calendar.MONTH);
				int day=time.get(Calendar.DAY_OF_MONTH);
				int hour=time.get(Calendar.HOUR_OF_DAY);
				int min=time.get(Calendar.MINUTE);
				setTimeOfView(year,month,day,hour,min);
				if(min<10){
					lblTime.setText("Time "+hour+":0"+min);
				}
				else{
					lblTime.setText("Time "+hour+":"+min);
				}
				lblDate.setText("Date: "+(month+1)+"/"+day+"/"+year);
				
				sleep(1000);
				}
			}
			catch(InterruptedException e){
				e.printStackTrace();
					
				}
			}//end run
		};//end thread
		clock.start();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setBounds(100, 100, 491, 300);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNameOfParty = new JLabel("Name of Party");
		lblNameOfParty.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNameOfParty.setBounds(24, 27, 86, 14);
		
		txtEnterHere = new JTextField();
		txtEnterHere.setBounds(24, 47, 124, 20);
		txtEnterHere.setColumns(10);
		
		JLabel lblConatctName = new JLabel("Contact Number (125) 456-7890");
		lblConatctName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConatctName.setBounds(24, 79, 195, 14);
		
		textField_1 = new JTextField();
		textField_1.setBounds(24, 103, 124, 20);
		textField_1.setColumns(10);
		
		JLabel lblNote = new JLabel("Note");
		lblNote.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNote.setBounds(24, 166, 40, 14);
		
		btnEnterGuest = new JButton("Enter Guest");
		btnEnterGuest.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnEnterGuest.setBounds(376, 228, 95, 23);
		
		textArea = new JTextArea();
		textArea.setBounds(24, 191, 124, 60);
		textArea.setLineWrap(true);
		
		lblDate = new JLabel("Date:     ");
		
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDate.setBounds(285, 47, 105, 14);//96
		this.setLayout(null);
		
		btnMP = new JButton("M+");
		btnMP.setBounds(285, 72, 43, 19);
		btnMP.setFont(new Font("Tahoma", Font.PLAIN, 6));
		this.add(btnMP);
		this.add(lblNote);
		this.add(textArea);
		this.add(btnEnterGuest);
		this.add(lblNameOfParty);
		this.add(txtEnterHere);
		this.add(lblDate);
		this.add(textField_1);
		this.add(lblConatctName);
		
		btnMM = new JButton("M-");
		btnMM.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnMM.setBounds(285, 96, 43, 19);
		this.add(btnMM);
		
		btnDP = new JButton("D+");
		btnDP.setFont(new Font("Tahoma", Font.PLAIN, 6));
		btnDP.setBounds(338, 72, 43, 19);
		this.add(btnDP);
		
		btnDM = new JButton("D-");
		btnDM.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnDM.setBounds(338, 96, 43, 19);
		this.add(btnDM);
		
		btnYP = new JButton("Y+");
		btnYP.setFont(new Font("Tahoma", Font.PLAIN, 7));
		btnYP.setBounds(391, 72, 43, 19);
		this.add(btnYP);
		
		btnYM = new JButton("Y-");
		btnYM.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnYM.setBounds(391, 96, 43, 19);
		this.add(btnYM);
		
		lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTime.setBounds(285, 126, 78, 14);
		this.add(lblTime);
		
		btnHP = new JButton("h+");
		btnHP.setFont(new Font("Tahoma", Font.PLAIN, 6));
		btnHP.setBounds(285, 148, 43, 19);
		this.add(btnHP);
		
		btnHM = new JButton("h-");
		btnHM.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnHM.setBounds(285, 172, 43, 19);
		this.add(btnHM);
		
		btnMinM = new JButton("m-");
		btnMinM.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnMinM.setBounds(338, 172, 55, 19);
		this.add(btnMinM);
		
		btnMinP = new JButton("m+");
		btnMinP.setFont(new Font("Tahoma", Font.PLAIN, 6));
		btnMinP.setBounds(338, 148, 55, 19);
		this.add(btnMinP);
		
		JLabel lblSize = new JLabel("Size");
		lblSize.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSize.setBounds(24, 134, 46, 14);
		this.add(lblSize);
		
		textField = new JTextField();
		textField.setBounds(50, 135, 43, 20);
		this.add(textField);
		textField.setColumns(10);
	}
	
	public void registerListener(GuestController controller) {//Add listener to button
		btnEnterGuest.addActionListener(controller);	
		btnMP.addActionListener(controller);//Month plus button
		btnMM.addActionListener(controller);//month minus button
		btnDP.addActionListener(controller);//Day plus button
		btnDM.addActionListener(controller);//Day minus button
		btnYP.addActionListener(controller);//Year plus button
		btnYM.addActionListener(controller);//Year minus button 
		btnHP.addActionListener(controller);//hour plus button 
		btnHM.addActionListener(controller);//hour minus button
		btnMinM.addActionListener(controller);//minute minus button
		btnMinP.addActionListener(controller);//minute plus button
	}
	
	public String getNameTextField(){
		return txtEnterHere.getText();
	}
	
	public String getContactTextField(){
		return textField_1.getText();
	}
	
	/*
	 * return a string of the party size
	 * SHould check to see that it is numeric character and at least 1 person
	 * 
	 */
	public String getSizeTextField(){
		return textField.getText();
	}
	
	public String getNoteTextField(){
		return textArea.getText();
	}
	
	/**
	 * Date and Time Buttons
	 * 
	 */
	
	public void addYear(){
		yearAdd++;
	}
	public void subtractYear(){
		if(yearAdd!=0){
			yearAdd--;
		}
	}
	
	public void addMonth(){
		monthAdd++;
	}
	public void subtractMonth(){
		if(monthAdd!=0){
			monthAdd--;
		}
	}
	public void addDay(){
		dayAdd++;
	}
	public void SubtractDay(){
		if(dayAdd!=0){
			dayAdd--;
		}
	}
	public void addHour(){
		hourAdd++;
	}
	public void SubtractHour(){
		if(hourAdd!=0){
			hourAdd--;
		}
	}
	public void addMinute(){
		minuteAdd++;
	}
	public void SubtractMinute(){
		if(minuteAdd!=0){
			minuteAdd--;
		}
	}
	
	public GregorianCalendar getViewTime(){
		if(timeOfView==null){
			return null;
		}
		return timeOfView;
	}
	
	private void setTimeOfView(int year,int month,int day,int hour,int min){
		timeOfView=new GregorianCalendar();
		timeOfView.set(year, month, day, hour, min);
	}
	

	public void displayErrorWindow(Exception error){
		if(false){
			//JOptionPane.showMessageDialog(this, "Valide sutff");
		}
		else{
			//JOptionPane.showMessageDialog(this, error);
		}
	}
}
