package tabler.components.guest;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.lang.Integer;
public class testMain {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		GuestModel testModel=new GuestModel("bob","No stuff and things",false,"(512)-123-8524");
		testModel.setReservationTime(15, 3, 2016, 9, 26);
		System.out.println(testModel.toString());
		System.out.println(testModel.reservationTimeRet());
		
		
		String a="abs";
		Integer b=Integer.getInteger(a);
		
		System.out.printf("%d",b);
		
		

	}

}
