import java.util.Scanner;
import java.util.Calendar;
public class Events{
	private ENode head;
	private ENode tail;
	private int size;

	public Events(){
		head = null;
		tail = null;
		size = 0;

	}
	

	public boolean isEmpty(){
		return size == 0;
	}

	public void addEvent(){
		Scanner scan = new Scanner(System.in); //user input for date
		Calendar userCal = Calendar.getInstance();  //new calendar instance
		Calendar now = Calendar.getInstance();
		System.out.println("\nThe current date/time is: " + now.getTime());
		int month, day, year, hour, min, date;
		String event;
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

		Scanner eventScan = new Scanner(System.in);
		System.out.print("Please enter the event: ");
		event = eventScan.nextLine();

		userCal.set(year, month-1, day, hour, min);

		long miliTime = userCal.getTimeInMillis();
		ENode u = new ENode(userCal, event, null);

	

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
		test.addEvent();
		// test.addEvent();
		test.displayEvents();

	}
}
