import java.util.Calendar;
import java.util.Scanner;
public class ConnBook{
	private PriorityQueue events;
	private HashTable personTable;

	public ConnBook(){
		events = new PriorityQueue();
		personTable = new HashTable();
	}

	public void addPerson(String firstName, String lastName, String email, int year, String password){
		Person pname = new Person(firstName, lastName, email, year, password);
		personTable.addPerson(pname);
	}

	public void deleteAccount(String firstName, String lastName, String email, int year, String password){
		Person pname = new Person(firstName, lastName, email, year, password);
		personTable.deleteAccount(pname);
	}

	public void addEvent(Calendar userCal, String event){
		events.insertEvent(userCal, event);
	}

	public Person login(String email, String password){
		Person person = personTable.findPerson(email, password);
		return person;
	}



}//priority queue & hashTable located here 


class ConnBookMain{
	public static void main(String[] args){
		//main method goes here

		ConnBook book = new ConnBook();
		book.addPerson("Justin", "T", "jturbevil@conncoll.edu", 2022, "password");
		// book.addPerson("Evan", "Goldsmith", "egoldsmi@conncoll.edu", 2022, "myPassword");
		// book.addPerson("Zack", "Beucler", "zbeucler@conncoll.edu", 2022, "zacksPass");
		// book.addPerson("William", "Tarimo", "wtarimo@conncoll.edu", 2005, "tarimoPass");
		// //how do we access the person object thats located in the hash table, in order to get all the methods in Person.java?
		Person justin = book.login("jturbevil@conncoll.edu", "password");
		// Person evan = book.login("egoldsmi@conncoll.edu", "myPassword");
		// // System.out.println(evan.getFirstName());
		// // System.out.println(justin.getLastName()+" "+justin.getFirstName());
		// // justin.addTimelinePost("This finally works!", "Justin");
		// // justin.addTimelinePost("I'm hungry!", "Justin");
		// // justin.addTimelinePost("Testing...", "Justin!");
		// // justin.displayTimeline(5);
		// // System.out.println(justin.getEmail());
		// // System.out.println(justin.getYear());
		// // System.out.println(justin.getPassword());
		// // System.out.println(justin.getEmailKey
		// justin.addFriend("egoldsmi@conncoll.edu");
		// justin.addFriend("zbeucler@conncoll.edu");
		// justin.addFriend("wtarimo@conncoll.edu");
		// // justin.showFriends();
		// justin.removeFriend("zbeucler@conncoll.edu");
		// justin.showFriends();


		//testing event 
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
		justin.insertEvent(userCal, event);


		Calendar userCal1 = Calendar.getInstance();  //new calendar instance
		int month1, day1, year1, hour1, min1, date1;
		Scanner scan1 = new Scanner(System.in); //user input for date
		System.out.print("Please enter a month MM: ");
		month1 = scan1.nextInt();
		System.out.print("Please enter a day DD: ");
		day1 = scan1.nextInt();
		System.out.print("Please enter a year YYYY: ");
		year1 = scan1.nextInt();
		System.out.print("Please enter an hour of the day (0-23): ");
		hour1 = scan1.nextInt();
		System.out.print("Please enter the minute of the hour (00-59): ");
		min1 = scan1.nextInt();
		userCal1.set(year1, month1-1, day1, hour1, min1);
		justin.insertEvent(userCal1, "Test 1");
		justin.displayEvents();
		//end test

	}

}
