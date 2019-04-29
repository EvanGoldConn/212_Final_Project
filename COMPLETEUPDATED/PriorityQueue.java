import java.util.Scanner;
import java.util.Calendar;
public class PriorityQueue{
	private ENode[] events;
	private int n;
	private int N;



	public PriorityQueue(){
		n = 0;
		N = 1000; 
		events = new ENode[N];
	}

	private int leftChild(int p){
		return 2*p;
	}
	private int rightChild(int p){
		return 2*p+1;
	}

	private int parent(int c){
		return c/2;
	}

	private void swap(int i, int j){
		ENode temp = events[i];
		events[i] = events[j];
		events[j] = temp;
	}

	public void insertEvent(Calendar userCal, String event){ //pass in as a calendar object, don't get user input here (do in main method)
		// Scanner scan = new Scanner(System.in); //user input for date
		// Calendar userCal = Calendar.getInstance();  //new calendar instance
		// Calendar now = Calendar.getInstance();
		// System.out.println("\nThe current date/time is: " + now.getTime());
		// int month, day, year, hour, min, date;
		// String event;
		// System.out.print("Please enter a month MM: ");
		// month = scan.nextInt();
		// System.out.print("Please enter a day DD: ");
		// day = scan.nextInt();
		// System.out.print("Please enter a year YYYY: ");
		// year = scan.nextInt();
		// System.out.print("Please enter an hour of the day (0-23): ");
		// hour = scan.nextInt();
		// System.out.print("Please enter the minute of the hour (00-59): ");
		// min = scan.nextInt();

		// Scanner eventScan = new Scanner(System.in);
		// System.out.print("Please enter the event: ");
		// event = eventScan.nextLine();

		// userCal.set(year, month-1, day, hour, min);

		// long miliTime = userCal.getTimeInMillis();
		ENode u = new ENode(userCal, event, null);

		events[n+1] = u;
		n++;
		int child = n;
		while(child > 1 && events[child].getMiliTime() < events[parent(child)].getMiliTime()){
			swap(child, parent(child));
			child = parent(child);
		}
	}

	public void displayEvents(){
		for(int i = 1; i <= n; i++){
			System.out.println(events[i].getEvent()+" "+events[i].getDate().getTime());
		}
	}

	private boolean hasSmallerChild(int i) {
		if(rightChild(i) <= n && events[rightChild(i)].getMiliTime()<events[i].getMiliTime()) {
			return true;

		}
		else if(leftChild(i) <= n && events[leftChild(i)].getMiliTime()<events[i].getMiliTime()) {
			return true;
		}
		return false;
	}

	public ENode extractMin(){
		swap(1, n); //swap root w/ last node
		n--;
		int p = 1; //location of node to bubble down
		while(hasSmallerChild(p)){
			if(rightChild(p) > n){
				swap(p, leftChild(p));
				p = leftChild(p);
			}
			else{
				if(events[leftChild(p)].getMiliTime() < events[rightChild(p)].getMiliTime()){
					swap(p, leftChild(p));
					p = leftChild(p);
				}
				else{
					swap(p, rightChild(p));
					p = rightChild(p);
				}
			}
		}
		return events[n+1];
	}

}


class priorityQueueTest{
	public static void main(String[] args){
		PriorityQueue test = new PriorityQueue();
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


		test.insertEvent(userCal, event);
		// test.insertEvent();
		// test.insertEvent();
		test.displayEvents();
		test.extractMin();
		System.out.println("\n removing.... \n");
		test.displayEvents();



	}






}
