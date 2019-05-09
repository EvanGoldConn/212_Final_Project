import java.util.Calendar;
import java.util.Scanner;
public class ENode{
	private Calendar date;
	private String event;
	private ENode next;
	private int month;
	private int day;
	private int year;
	private int hour;
	private int minutes;

	public ENode(Calendar d, String e, ENode n, int month1, int day1, int year1, int hour1, int minutes1){
		date = d;
		event = e;
		next = n;
		month = month1;
		day = day1;
		year = year1;
		hour = hour1;
		minutes = minutes1;

	}

	public int rMonth(){
		return month;
	}

	public int rDay(){
		return day;
	}

	public int rYear(){
		return year;
	}

	public int rHour(){
		return hour;
	}

	public int rMin(){
		return minutes;
	}

	public String returnDates(){
    	String m, d, y, h, min;
    	m = Integer.toString(month);
    	d = Integer.toString(day);
    	y = Integer.toString(year);
    	h = Integer.toString(hour);
    	min = Integer.toString(minutes);
	if(min.equals("0")) {
		min = "00";
	}
    	String output = '"'+m+" "+d+" "+y+" "+h+" "+min;
    	return output;

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


		



	 	ENode test = new ENode(userCal, "Lunch Date w/ Friend", null, year, month-1, day, hour, min);
		ENode test1 = new ENode(userCal, "School Dance", null, year, month-1, day, hour, min);
		test.setNext(test1);
		System.out.println(test.getNext().getEvent());
	}
}



