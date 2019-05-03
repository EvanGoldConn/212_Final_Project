import java.util.Calendar;
import java.util.Scanner;
import java.io.*;
public class ConnBook{
	private PriorityQueue events;
	private HashTable personTable;
	private boolean startProgram;

	public ConnBook(){
		events = new PriorityQueue();
		personTable = new HashTable();
		startProgram = false;
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
	
	public void setHash(String file) {
		FileIO read = new FileIO();
		personTable = read.getHash(file);
	}
	
	public void showAccounts(){
		personTable.showAccounts();
	}

	public void startProgram(){
		startProgram = true;
	}

	public void endProgram(){
		startProgram = false;
	}

	public boolean program(){
		return startProgram == true;
	}


    public void fileWriter(){
        Person[] personArray = personTable.hashTable();
        for(int i = 0;i < personTable.getSize();i++) {
            if(personArray[i] != null) {
                Person p = personArray[i];
                String data = p.getFullName() + "\n" + p.getEmail() + "\n" + p.getPassword() + p.getYear() + "\n";
                ENode curE = p.getPersonEvent().getHead();
                for(int j = 0;j < p.getPersonEvent().getSize();i++) {
                    data += curE.getDate() + " " + curE.getEvent() + ",";  //need gettimeinmillis?????
                    curE = curE.getNext();
                }
                data += "\n";
                TNode curT = p.getTimeline().getHead();
                for(int k = 0;k < p.getTimeline().getSize();i++) {
                    data += curT.getPost() + ",";
                }
                data += "\n" + p.getFriends().postOrderTrav(p.getPersonFriends().root());
                try {
                    FileWriter fileOut = new FileWriter("userdata.txt");
                    BufferedWriter bufWriter = new BufferedWriter(fileOut);
                    bufWriter.write(data);
                    
                    bufWriter.close();
                    fileOut.close();
                } 
                
                catch (IOException e) {
                    e.printStackTrace();
                }



            }
            
        }
    }

	


}//priority queue & hashTable located here 

class ConnBookTest{
	public static void main(String[] args){
		ConnBook book = new ConnBook();
		book.addPerson("Justin", "T", "jturbevil@conncoll.edu", 2022, "password");
		book.addPerson("Evan", "Goldsmith", "egoldsmi@conncoll.edu", 2022, "myPassword");
		book.addPerson("Zack", "Beucler", "zbeucler@conncoll.edu", 2022, "zacksPass");
		book.addPerson("William", "Tarimo", "wtarimo@conncoll.edu", 2005, "tarimoPass");
		boolean testbool = true;
		if(testbool == true){
			Person justin = book.login("jturbevil@conncoll.edu", "password");
			System.out.println(justin.getLastName()+" "+justin.getFirstName());
			justin.addFriend("egoldsmi@conncoll.edu");
		justin.addFriend("zbeucler@conncoll.edu");
		justin.addFriend("wtarimo@conncoll.edu");
		justin.showFriends();
		System.out.println("Removing...");
		justin.removeFriend("wtarimo@conncoll.edu");
		justin.showFriends();
		}
		// Person evan = book.login("egoldsmi@conncoll.edu", "myPassword");
		// // System.out.println(evan.getFirstName());
		// System.out.println(justin.getLastName()+" "+justin.getFirstName());
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
		// justin.showFriends();
		// System.out.println("Removing...");
		// justin.removeFriend("wtarimo@conncoll.edu");
		// justin.showFriends();
		// book.showAccounts();


		// //testing event 

		// Calendar userCal = Calendar.getInstance();  //new calendar instance
		// int month, day, year, hour, min, date;
		// Scanner scan = new Scanner(System.in); //user input for date
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
		// userCal.set(year, month-1, day, hour, min);
		
		// String event;
		// Scanner eventScan = new Scanner(System.in);
		// System.out.print("Please enter the event: ");
		// event = eventScan.nextLine();
		// justin.insertEvent(userCal, event);


		// Calendar userCal1 = Calendar.getInstance();  //new calendar instance
		// int month1, day1, year1, hour1, min1, date1;
		// Scanner scan1 = new Scanner(System.in); //user input for date
		// System.out.print("Please enter a month MM: ");
		// month1 = scan1.nextInt();
		// System.out.print("Please enter a day DD: ");
		// day1 = scan1.nextInt();
		// System.out.print("Please enter a year YYYY: ");
		// year1 = scan1.nextInt();
		// System.out.print("Please enter an hour of the day (0-23): ");
		// hour1 = scan1.nextInt();
		// System.out.print("Please enter the minute of the hour (00-59): ");
		// min1 = scan1.nextInt();
		// userCal1.set(year1, month1-1, day1, hour1, min1);
		// justin.insertEvent(userCal1, "Test 1");
		// justin.displayEvents();

		// //end event test
	}
}

// class ConnBookMain{
class ConnBookMain{
	public static void main(String[] args){
		//main method goes here



		ConnBook mainBook = new ConnBook();
		mainBook.startProgram();
		if(mainBook.program()) {
			mainBook.setHash("userdata.txt");
		}
		boolean quitProgram = false;
		Person currentUser;
		while(quitProgram == false){
			// ============== Main Menu ======================================================== \\
			System.out.println(" |--------------------------------------------------------------|");
			System.out.println(" |                  Conn's Social Network Program               |");
			System.out.println(" |--------------------------------------------------------------|");
			System.out.println(" |  Created by: Evan Goldsmith, Justin Turbeville, Brian Gould  |");
			System.out.println(" |--------------------------------------------------------------|");
			System.out.println(" |                         Main Menu                            |");
			System.out.println(" |--------------------------------------------------------------|");
			System.out.println(" |     Option A          |  Option B      |    Option C         |");
			System.out.println(" |--------------------------------------------------------------|");
			System.out.println(" |   Login to Existing   |  Create a New  |  Exit the           |");
			System.out.println(" |   User Account        |  Account       |  Program            |");
			System.out.println(" |--------------------------------------------------------------|");
			System.out.println("\n");
			
			// ============================ User Input for Main Menu ============================ \\
			System.out.println("\n \n \n \n");
				System.out.println("   |---------------------------------------------------");
			String option;
			Scanner scan = new Scanner(System.in); //user input for date

			System.out.print("   |Please enter an option (A/B/C): ");
			option = scan.next();
			option.toLowerCase();
			boolean incorrectOption;
			if(option.equals("a") || option.equals("b") || option.equals("c")){
					incorrectOption = true;
				}
			else{
				incorrectOption = false;
			}
			while(!incorrectOption){	
				Scanner rescan = new Scanner(System.in); //user input for date
				System.out.print("   |Invalid Option \n |\t Please enter an option (A/B/C): ");
				option = rescan.next();
				option.toLowerCase();
				if(option.equals("a") || option.equals("b") || option.equals("c")){
					incorrectOption = true;
				}
			}


			// ======================== Login to Existing Account =============================== \\
			
			if(option.equals("a")){
				mainBook.showAccounts();
				System.out.println("\n \n \n \n");
				System.out.println("   |---------------------------------------------------");
				String email, password;
				Scanner login = new Scanner(System.in); // user input for email and password
				System.out.print("   |Please enter your email: ");
				email = login.next();
				System.out.print("   |Please enter your password: ");
				password = login.next();
				// System.out.println(email+" "+password);
				currentUser = mainBook.login(email, password);
				// System.out.println(currentUser.getLastName());


				// ========== User Options ========== \\
				System.out.println("\n \n \n \n");
				System.out.println(" |--------------------------------------------------------------------------------------------|");
				System.out.println(" | Option 1     | Option 2      | Option 3       | Option 4     | Option 5      | Option 6    |");
				System.out.println(" |--------------------------------------------------------------------------------------------|");
				System.out.println(" | Post to User | Add new event | Display current| Add / Remove | Log out of    |             |");
				System.out.println(" | Timeline     | to attend to  | Friends        | a Friend     | Account       |             |");
				System.out.println(" |--------------------------------------------------------------------------------------------|\n\n");
				System.out.println("\n \n \n \n");
				System.out.println("   |---------------------------------------------------");

				int userChoice;
				Scanner userScan = new Scanner(System.in); //user input for date

				System.out.println("   |Please enter an option (1/2/3/4/5/6): "); //how to catch inputmismatchexceptions
				userChoice = userScan.nextInt();
				boolean incorrectChoice;
				if(userChoice == 1 || userChoice == 2 || userChoice == 3 || userChoice == 4 || userChoice == 5 || userChoice == 6){
						incorrectOption = true;
				}
				else{
					incorrectOption = false;
				}
				while(!incorrectOption){	
					Scanner rescanChoice = new Scanner(System.in); //user input for date
					System.out.println("   |Invalid Option \n |\t Please enter an option (1/2/3/4/5/6): ");
					userChoice = rescanChoice.nextInt();
					if(userChoice == 1 || userChoice == 2 || userChoice == 3 || userChoice == 4 || userChoice == 5 || userChoice == 6){
						incorrectOption = true;
					}
				}






					// ========== Posting to Timeline ========== \\
				boolean boolTimeline = true;
				while(userChoice == 1 && boolTimeline){
					String postDetails;
					Scanner timelineScan = new Scanner(System.in); //user input for date
					System.out.println("\n \n \n \n");
					System.out.println("   |---------------------------------------------------");
					System.out.print("   |Please enter details for your timeline post: ");
					postDetails = timelineScan.nextLine();
					System.out.println(postDetails);
					currentUser.addTimelinePost(postDetails, "justin");
					
					System.out.println("\n \n \n \n");
					System.out.println("   |---------------------------------------------------");
					System.out.println("   | Last three timeline posts: ");
					currentUser.displayTimeline(3);

					System.out.println("\n \n \n \n");
					System.out.println("   |---------------------------------------------------");
					System.out.println("   | Would you like to add another post? (y/n) ");
					String tAnswer = timelineScan.next();

					if(tAnswer.equals("y") || tAnswer.equals("Y")){
						boolTimeline = true;
					}
					else if(tAnswer.equals("n") || tAnswer.equals("N")){
						boolTimeline = false;
					}

				}




			}


			// ============================ Create New Account ================================== \\

			else if(option.equals("b")){
				System.out.println("\n \n \n \n");
				System.out.println("   |---------------------------------------------------");

				String firstName, lastName, email, password;
				int classYear;
				Scanner newAcc = new Scanner(System.in); // user input for email and password
				System.out.println("   |Please enter your first name: ");
				firstName = newAcc.next();
				System.out.println("   |Please enter your last name: ");
				lastName = newAcc.next();
				System.out.println("   |Please enter your email: ");
				email = newAcc.next();
				System.out.println("   |Please enter your class year: ");
				classYear = newAcc.nextInt();
				System.out.println("   |Please enter your password: ");
				password = newAcc.next();
				mainBook.addPerson(firstName, lastName, email, classYear, password);
			}



			// ================= Quit Program ================ \\
			else if(option.equals("c")){
				quitProgram = true;
				System.out.println("\n \n \n");
				System.out.println(" \t \t |--------------|");
				System.out.println(" \t \t |  Thank You   |");
				System.out.println(" \t \t | For Using Our|");
				System.out.println(" \t \t |   Program!   |");
				System.out.println(" \t \t |--------------|");
				System.out.println("\n \n \n");
			}
			else{
				System.out.println("else");
			}

		}
	}
}
		
	
