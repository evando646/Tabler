package test;

import tabler.components.waitlist.WaitlistModel;
import tabler.components.waitlist.WaitlistView;

public class WaitlistTest {

	public static void main(String[] args) {
		WaitlistModel newWaitlist = new WaitlistModel();
		WaitlistView newWaitlistView = new WaitlistView();
		
		System.out.println(newWaitlist);
		System.out.println(newWaitlistView);
	}

}
