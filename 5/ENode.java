import java.util.Calendar;
import java.util.Scanner;
public class ENode{
	private Calendar date;
	private String event;
	private ENode next;

	public ENode(Calendar d, String e, ENode n){
		date = d;
		event = e;
		next = n;

	}


	public ENode getNext(){
		return next;

	}

	public void setNext(ENode v){
		next = v;
	}

	public long getMiliTime(){
		return date.getTimeInMillis();
	}

	public String getEvent(){
		return event;
	}

	public Calendar getDate(){
		return date;
	}

	



	

	
}

class mainMethod1{ //testing TNode class
	public static void main(String[] args){Calendar userCal = Calendar.getInstance();  //new calendar instance
		int month, day, year, hour, min, date;
		Scanner scan = new Scanner(System.in); //user input for date
		System.out.print("Please enter a month MM: ");
		month = scan.nextInt();
		System.out.print("Please enter a day DD: ");
		day = scan.nextInt();
		System.out.print("Please enter a year YYYY: ");
		year = scan.nextInt();
		System.out.print("Please enter an hour of the day (0-23): ");
		hour = scan.nextInt();
		System.out.print("Please enter the minute of the hour (00-59): ");
		min = scan.nextInt();
		userCal.set(year, month-1, day, hour, min);


		



	 	ENode test = new ENode(userCal, "Lunch Date w/ Friend", null);
		ENode test1 = new ENode(userCal, "School Dance", null);
		test.setNext(test1);
		System.out.println(test.getNext().getEvent());
	}
}



