import java.util.Scanner;
import java.util.Calendar;
/*used for storing the events in order of their occurance (priority)*/
public class PriorityQueue{
	/*fields*/
	private ENode[] events;  //array of ENodes of events
	private int n;
	private int N;


	/*constructor method*/
	public PriorityQueue(){
		n = 0; //starts at the beginning 
		N = 1000; //capacity
		events = new ENode[N]; //creates an ENode array capable of holding 1000 events
	}
	
	/**sets the left child of p to 2 times that value
	@param p  
		integer index of the parent node
	*/
	private int leftChild(int p){
		return 2*p;
	}
	
	/**sets the right child of p to 2 times that value +1
	@param p  
		integer index of the parent node
	*/
	private int rightChild(int p){
		return 2*p+1;
	}

	/**finds the parent of c by dividing the child by 2
	@param c  
		integer index of the child node
	*/
	private int parent(int c){
		return c/2;
	}
	
	/**swaps two nodes at locations i and j
	@param i  
		integer index of the first node
	@param j
		integer index of the second node
	*/
	private void swap(int i, int j){ //essentially swaps the locations of the two events
		ENode temp = events[i]; //sets event at index i to a temp variable
		events[i] = events[j]; //sets event j to the event i
		events[j] = temp;	//sets the temp to the event j location 
	}

	/**creates new event
	@param userCal  
		Calendar object with actual current date
	@param event
		String with the name of the event
	@param month
		Integer with the month of the event
	@param day
		Integer with the day of the event
	@param year
		Integer with the year 
	@param hour
		Integer with the hour
	@param minutes
		Integer with the minutes
	*/
	public void insertEvent(Calendar userCal, String event, int month, int day, int year, int hour, int minutes){ 
		Calendar currentDate = Calendar.getInstance(); //gets the current date
		long currentMiliTime = currentDate.getTimeInMillis(); //converts the date to milliseconds

		if(userCal.getTimeInMillis() < currentMiliTime){ //if the added event has already happened 
			System.out.print(" "); //print a blank space


		}
		else{ //otherwise add the event to the priority queue
			ENode u = new ENode(userCal, event, null, month, day, year, hour, minutes);

			events[n+1] = u; //sets the new ENode u to events a index n+1
			n++;
			int child = n;
			while(child > 1 && events[child].getMiliTime() < events[parent(child)].getMiliTime()){
				//organizing the priority queue based on the time
				swap(child, parent(child)); //if the child event comes before the parent event
							    //swap the two events
				child = parent(child);
			}
		}
	}
	
	/**displays events*/
	public void displayPQEvents(){
		for(int i = 1; i <= n; i++){ //loops through all events
			System.out.println(i+"."+events[i].getEvent()); //prints the event
			System.out.println("\t"+events[i].getDate().getTime()); //prints the date
		}
	}

	/**displays the next event*/
	public void displayNextEvent(){ 
		System.out.println(events[1].getEvent()); //gets the event that is coming up next 
		System.out.println("\t"+events[1].getDate().getTime()); //displays the date
	}

	/**allows user to keep an event, delete an event or quit from the events*/
	public ENode returnObject(){
		boolean answered = true;
		for(int i = 1; i < n && answered == true; i++){ //loops through all events 
								//prints the event and asks the user
								//if they want to keep it or delete it

			System.out.println("\n"+i+"."+events[i].getEvent());
			System.out.println("\t"+events[i].getDate().getTime());
			System.out.println("|------------------------------------------------------------");
			System.out.print("|Do you want to keep this event in your calendar? Quit? (y/n/q)");


			Scanner scan = new Scanner(System.in); //initalizing scanner object
			String answer = scan.next();
			answer = answer.toLowerCase();	


			if(answer.equals("y")){ //if they want to keep the event
				ENode u = new ENode(events[i].getDate(), events[i].getEvent(), null, events[i].rMonth(), events[i].rDay(), events[i].rYear(), events[i].rHour(), events[i].rMin());
				answered = false;
				return u;
			}
			else if(answer.equals("n")){ //if they want to delete the event 
				// System.out.println("reached n case");
			}
			else if(answer.equals("q")){ //if they want leave the events 
				answered = false;
				// System.out.println("reached q case");
				return null;
			}
		} //end while lopp
		return null;
	}
		
	/**boolean method to determine if the index i has a smaller child*/
	private boolean hasSmallerChild(int i) {
		if(rightChild(i) <= n && events[rightChild(i)].getMiliTime()<events[i].getMiliTime()) {
			//case for the right child
			return true;

		}
		else if(leftChild(i) <= n && events[leftChild(i)].getMiliTime()<events[i].getMiliTime()) {
			//case for the left child
			return true;
		}
		return false; //both children are larger 
	}

	/**gets rid of the most next event*/
	public ENode extractMin(){
		swap(1, n); //swap root w/ last node
		n--;
		int p = 1; //location of node to bubble down
		while(hasSmallerChild(p)){ //while the parent has smaller children
			if(rightChild(p) > n){ //if the right child is larger than the left
				swap(p, leftChild(p)); //swap the parent and the leftChild
				p = leftChild(p);
			}
			else{
				if(events[leftChild(p)].getMiliTime() < events[rightChild(p)].getMiliTime()){
					//if the left child comes before the right child
					swap(p, leftChild(p)); //swap the left child
					p = leftChild(p);
				}
				else{
					swap(p, rightChild(p)); //swap the right child
					p = rightChild(p);
				}
			}
		}
		return events[n+1]; 
	}

}


class priorityQueueTest{ //priority queue test class to test the methods used throughout
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


		test.insertEvent(userCal, event, year, month-1, day, hour, min);
		// test.insertEvent();
		// test.insertEvent();
		test.displayPQEvents();
		test.extractMin();
		System.out.println("\n removing.... \n");
		test.displayPQEvents();
		
	}

}
