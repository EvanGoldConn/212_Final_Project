import java.util.Scanner;
import java.util.Calendar;
/*creates events*/
public class Events{
	
	/*fields*/
	private ENode head;
	private ENode tail;
	private int size;

	/*constructor method*/
	public Events(){
		head = null;
		tail = null;
		size = 0;
	
	}
	
	/**
	 * method isEmpty, checks if events is empty
	 * @return size == 0
	 */
	public boolean isEmpty(){
		return size == 0;
	}

	/**
	 * method addEvent, adds an event to the linked list of events
	 * @param date, event, month1, day1, year1, hour1, minutes1
	 *  	Calender date in real time
	 * 		String event that 
	 *  			String v is post context
	 * 	String c is post creator
	 *  		String v is post context
	 * 			String c is post creator
	 *  	String v is post context
	 * 		String c is post creator
	 */
	public void addEvent(Calendar date, String event, int month1, int day1, int year1, int hour1, int minutes1){
		ENode u = new ENode(date, event, null, month1, day1, year1, hour1, minutes1);
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
		test.addEvent(userCal, event, year, month-1, day, hour, min);
		// test.addEvent();
		test.displayEvents();

	}
}
