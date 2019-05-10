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
	*method addEvent, adds a new event
	*@param date  
	*	Calendar object with actual current date
	*@param event
	*	String with the name of the event
	*@param month1
	*	Integer with the month of the event
	*@param day1
	*	Integer with the day of the event
	*@param year1
	*	Integer with the year 
	*@param hour1
	*	Integer with the hour
	*@param minutes1
	*	Integer with the minutes
	*/
	public void addEvent(Calendar date, String event, int month1, int day1, int year1, int hour1, int minutes1){
		ENode u = new ENode(date, event, null, month1, day1, year1, hour1, minutes1);
		if(isEmpty()){ //if it is empty
			head = u; //make this new event the head ENode
		}
		else if (!isEmpty()){ //if it isn't empty 
			ENode temp = head; //make temp the head
			head = u; //set the new event to the head
			head.setNext(temp);
		}
		size++;
	}
	
	/**
	 * method getHead, gets the head of the LL
	 * @return head
	 */
	public ENode getHead() {
		return head;
	}

	/**
	 * method getSize, gets the size of the LL
	 * @return size
	 */
	public int getSize() {
		return size;
	} 

	/**
	 * method displayEvents, shows all of the events
	 */
	public void displayEvents(){
		ENode cur = head;

		if (!isEmpty()){ //if it isn't empty
			for(int i = 0; i < size; i++){ //loops through all events
				if(cur != null){ //prints out every event with the date and time
					System.out.println(cur.getDate().getTime()+" "+cur.getEvent());
					cur = cur.getNext();
				}

			}
		}
	}

	/**
	 * method getTimeInMillis, gets the time in milliseconds on ENode v
	 */
	public long getTimeInMillis(ENode v){
		return v.getMiliTime();
	}
}

class eventTest{ //test class for the events
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
