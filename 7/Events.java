import java.util.Scanner;
import java.util.Calendar;
public class Events{
	private ENode head;
	private ENode tail;
	private int size;
	private int month;
	private int day;
	private int year;
	private int hour;
	private int minutes;

	public Events(){
		head = null;
		tail = null;
		size = 0;
		month = 0;
		day = 0;
		year = 0;
		hour = 0;
		minutes = 0;

	}
	
	public boolean isEmpty(){
		return size == 0;
	}

	public void storeDates(int month1, int day1, int year1, int hour1, int minutes1){
		month = month1;
		day = day1;
		year = year1;
		hour = hour1;
		minutes = minutes1;
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
	System.out.println(min + "\n \n");
    	String output = '"'+m+" "+d+" "+y+" "+h+" "+min;
    	return output;

    }

	public void addEvent(Calendar date, String event){
		ENode u = new ENode(date, event, null);
		if(isEmpty()){
			head = u;
		}
		else if (!isEmpty()){ 
			ENode temp = head;
			head = u;
			head.setNext(temp);
		}
		size++;
	}
	public ENode getHead() {
		return head;
	}

	public int getSize() {
		return size;
	} 

	

	public void displayEvents(){
		ENode cur = head;

		if (!isEmpty()){
			for(int i = 0; i < size; i++){
				if(cur != null){
					System.out.println(cur.getDate().getTime()+" "+cur.getEvent());
					cur = cur.getNext();
				}

			}
		}
	}


	public long getTimeInMillis(ENode v){
		return v.getMiliTime();
	}
}

class eventTest{
	public static void main(String[] args){
		Events test = new Events();
		Calendar userCal = Calendar.getInstance();  //new calendar instance
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

		String event;
		Scanner eventScan = new Scanner(System.in);
		System.out.print("Please enter the event: ");
		event = eventScan.nextLine();
		test.addEvent(userCal, event);
		// test.addEvent();
		test.displayEvents();

	}
}
