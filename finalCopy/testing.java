import java.util.Calendar;
import java.util.Scanner;
import java.io.*;
public class ConnBook{
	private PriorityQueue events;
	private HashTable personTable;
	private boolean startProgram;

	public ConnBook(){
		events = new PriorityQueue(); //initalizing new priority queue
		personTable = new HashTable(); //initalizing new hash table 
		startProgram = false; //boolean that initalizes file writing and reading 
	}

	public PriorityQueue returnEvents(){
		return events;
	}

	public HashTable returnTable(){
		return personTable;
	}
	/**
	 * @param firstName
	 * 		first name of person being added 
	 * @param lastName
	 * 		last name of person being added
	 * @param email
	 * 		email of person being added 
	 * @param year
	 * 		year of person being added 
	 * @param password
	 * 		password of person being added
	 * 
	 *
	 */ 
	public void addPerson(String firstName, String lastName, String email, int year, String password){
		Person pname = new Person(firstName, lastName, email, year, password); //initalizes new person object
		personTable.addPerson(pname); //adding object to hash table 
	}
	/**
	 * @param firstName
	 * 		first name of person being removed 
	 * @param lastName
	 * 		last name of person being removed
	 * @param email
	 * 		email of person being removed 
	 * @param year
	 * 		year of person being removed 
	 * @param password
	 * 		password of person being removed
	 * 
	 *
	 */ 
	// public void deleteAccount(String firstName, String lastName, String email, int year, String password){
	// 	Person pname = new Person(firstName, lastName, email, year, password);
	// 	personTable.deleteAccount(pname);
	// }
	//adds event to priority queue. 
	//used for global campus events that all users can see
	public void addEvent(Calendar userCal, String event, int month, int day, int year, int hour, int minutes){
		events.insertEvent(userCal, event, month, day, year, hour, minutes);
	}

	//login method, requires email and password and returns Person object if password and email match
	public Person login(String email, String password){
		// System.out.println(email+" "+password);
		Person person = personTable.findPerson(email, password);
		return person;
	}


	// ============== Used for File Reading =======
	public void setHash(String file){
		FileIO read = new FileIO();
		personTable = read.getHash(file);
	}
	public void setEvents(String file){
 		FileIO read = new FileIO();
 		events = read.getEvents(file);
 	}
 	// =============================================

 	//for admin use only 
	public void showAccounts(){
		personTable.showAccounts();
	}
	//indicates that the program is starting, used for file reading in. 
	public void startProgram(){
		startProgram = true;
	}
	//indicates that the program is ending, used for file writing out.
	public void endProgram(){
		startProgram = false;
	}
	//returns status of the program 
	public boolean program(){
		return startProgram == true;
	}

	//file writing object. Takes no params, but writes out a data file
	public void fileWriter(){
        Person[] personArray = personTable.hashTable();
        String data = "";
        for(int i = 0;i < personArray.length;i++) { //iterating based on the length of the person array 
            if(personArray[i] != null) { //if the current person isn't null (so there is an actual account)
                Person p = personArray[i];
                data = data + p.getFullName() + "\n" + p.getEmail() + "\n" + p.getPassword() + "\n" + p.getYear() + "\n"; //retrieves person's first name, last name, email, and password
                ENode curE = p.getPersonEvent().getHead(); //starting at node head to iterate through users events 
                for(int j = 0;j < p.getPersonEvent().getSize();j++) { //iterating based on size @ of events 
				
                	data += curE.returnDates()+ " ";  //returns the dates of the current node in the SLL, adds them to data file 
				String tempEvent = curE.getEvent() +'"' + ",";  //returns the event name of the current node in teh SLL, adds them to data file  
                	tempEvent = tempEvent.substring(1,tempEvent.length()); 
				data += tempEvent; 
				curE = curE.getNext();
                }
                data += "\n";
                TNode curT = p.getTimeline().getHead(); //starting node at current head 

                for(int k = 0;k < p.getTimeline().getSize();k++) {//for loop iterates based on size of timeline 
                    data += curT.getPost() +'"'+","; //writing data post to file 
                    curT = curT.getNext();
                }

                p.getPersonFriends().emptyString();//setting friends string (in bstfriends) to empty 
                if(p.getPersonFriends().size() > 0){
                	p.getPersonFriends().printingFriends(p.getPersonFriends().root()); //creates a string of person's friends in BSTFriends class
	                data += "\n" + p.getPersonFriends().returnString() + "\n"; //calls for the string of persons friend in BSTFriends class 
            	}
            	else if(p.getPersonFriends().size() == 0){ 
            		data = data + "\n \n"; //if user has no friends, adds a new line to account for empty space 
            	}
                

            }
            
        }
        try { //writes file out 
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



	


}//priority queue & hashTable located here 


class ConnBookMain{
	public static void main(String[] args){
		//main method goes here

		ConnBook mainBook = new ConnBook();
		mainBook.startProgram();


		// ====== reading in text file ===== 
		if(mainBook.program()) { 
			mainBook.setEvents("events.txt");
			mainBook.setHash("userdata.txt");
			
		}
		// ----------------------------------- \\


		boolean quitProgram = false;
		
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
			option = option.toLowerCase();
			boolean incorrectOption;
			if(option.equals("a") || option.equals("b") || option.equals("c") || option.equals("adlog")){
					incorrectOption = true;
				}
			else{
				incorrectOption = false;
			}
			while(!incorrectOption){ //avoiding error, using while loop 
				Scanner rescan = new Scanner(System.in); //user input for date
				System.out.print("   |Invalid Option \n |\t Please enter an option (A/B/C): ");
				option = rescan.next();
				option = option.toLowerCase();
				if(option.equals("a") || option.equals("b") || option.equals("c") || option.equals("adlog")){
					incorrectOption = true;
				}
			}


			// ======================== Login to Existing Account =============================== \\
			
			if(option.equals("a") || option.equals("A")){
				
				System.out.println("\n \n \n \n");
				System.out.println("   |---------------------------------------------------");

				// ======== Getting user emial and password =======
				String email, password;
				Scanner login = new Scanner(System.in); // user input for email and password
				System.out.print("   |Please enter your email: ");
				email = login.next();
				System.out.print("   |Please enter your password: ");
				password = login.next();


				// ======= Logging user into account =======
				Person currentUser = mainBook.login(email, password);
				boolean homeScreen = false;
				while(currentUser == null && !homeScreen){
					System.out.println("\n \n \n \n");
					System.out.println("   |---------------------------------------------------|");
					System.out.println("   | Error: Account Not Found - Please Enter Different |");
					System.out.println("   |                            Email and Password     |");
					System.out.println("   |---------------------------------------------------|");


					// ========== Rescanning for working email and password =========
					Scanner reLogin = new Scanner(System.in); // user input for email and password
					System.out.print("   |Please enter your email: ");
					email = reLogin.next();
					System.out.print("   |Please enter your password: ");
					password = reLogin.next();
					currentUser = mainBook.login(email, password); //logging user in 
					if(currentUser == null){
						System.out.print("   |Return back to home screen? (y/n))");
						String loginAnswer = reLogin.next();
						loginAnswer = loginAnswer.toLowerCase();
						if(loginAnswer.equals("y")){
							homeScreen = true;

						}
					}

				}

				// ====================================== If User SUCCESSFULLY Logged in ======================================== \\
				boolean loggedIn = false;
				boolean logInScreen = true;
				if(currentUser != null){
					loggedIn = true;
				}
				while(currentUser != null && loggedIn){

					
					while(logInScreen){
						// ========================= Home Screen Display ========================== \\

						System.out.println("\n \n \n \n");
						System.out.println("|-------------|--------------------------|");
						System.out.println("| Welcome Back: "+currentUser.getFullName()+"!"); //calling name method 


						System.out.println("\n \n \n ");
						System.out.println("|----------------------------");
						System.out.println("| Next Campus Event:");
						mainBook.returnEvents().displayNextEvent(); //displaying events from priority queue
						// mainBook.returnEvents().displayPQEvents();
						// events.displayPQEvents();

						
						System.out.println("\n \n \n ");
						System.out.println("|----------------------------");
						System.out.println("| Most recent timeline posts:");

						currentUser.loginTimeline(); //displaying user's personal events 
						System.out.println("\n \n \n ");
						System.out.println("|----------------------");
						System.out.println("| Your upcoming events:");


						currentUser.displayEvents(); //

						System.out.println("\n \n \n \n ");
						logInScreen = false;
					}

					





					// ========== User Options ========== \\
					
					System.out.println(" |--------------------------------------------------------------------------------------------|");
					System.out.println(" | Option 1     | Option 2      | Option 3       | Option 4     | Option 5      | Option 6    |");
					System.out.println(" |--------------------------------------------------------------------------------------------|");
					System.out.println(" | Post to User | Add new event | Display current| Add / Remove | Display Home  | Log out of  |");
					System.out.println(" | Timeline     | to attend to  | Friends        | a Friend     | Screen        | Account     |");
					System.out.println(" |--------------------------------------------------------------------------------------------|\n\n");
					System.out.println("\n \n \n \n");
					System.out.println("   |---------------------------------------------------");

					String userChoice;
					Scanner userScan = new Scanner(System.in); //user input for date

					System.out.print("   |Please enter an option (1/2/3/4/5/6): "); //how to catch inputmismatchexceptions
					userChoice = userScan.next();
					boolean incorrectChoice;
					if(userChoice.equals("1") || userChoice.equals("2") || userChoice.equals("3") || userChoice.equals("4") || userChoice.equals("5") || userChoice.equals("6")){
							incorrectOption = true; //if true, will re-ask the question 
					}
					else{
						incorrectOption = false;
					}
					while(!incorrectOption){ //avoiding user error 
						Scanner rescanChoice = new Scanner(System.in); //user input for date
						System.out.print("   |Invalid Option \n |\t Please enter an option (1/2/3/4/5/6): ");
						userChoice = rescanChoice.next();
						if(userChoice.equals("1") || userChoice.equals("2") || userChoice.equals("3") || userChoice.equals("4") || userChoice.equals("5") || userChoice.equals("6")){
							incorrectOption = true; //ends while loop 
						}
					}




					// ======================== Option 1: Posting to Timeline ========================= \\
					boolean boolOption1 = true;
					while(userChoice.equals("1") && boolOption1){
						String postDetails;
						Scanner timelineScan = new Scanner(System.in); //user input for date
						System.out.println("\n \n \n \n");
						System.out.println("   |---------------------------------------------------");
						System.out.print("   |Please enter details for your timeline post: ");
						postDetails = timelineScan.nextLine(); //user inpiut for timeline post details
						currentUser.addTimelinePost(postDetails, currentUser.getFirstName()); //adding to user timeline 
						
						System.out.println("\n \n \n \n");
						System.out.println("   |---------------------------------------------------");
						System.out.println("   | Last three timeline posts: ");
						currentUser.displayTimeline(3); //displays last three timeline post 

						System.out.println("\n \n \n \n");
						System.out.println("   |---------------------------------------------------");
						System.out.print("   | Would you like to add another post? (y/n) ");
						String tAnswer = timelineScan.next();
						tAnswer = tAnswer.toLowerCase();

						if(tAnswer.equals("y")){
							boolOption1 = true;
						}
						else if(tAnswer.equals("n")){
							boolOption1 = false;
						}

					}

					// ========================== Option 2: Adding Event to Attend to ============================= \\
					boolean boolOption2 = true; //boolean for option 2 while loop 

					// ==== Boolean for While Loops ====== \\
					boolean boolUpload = false; // for creating a new event and uploading 
					boolean boolDownload = false; //for downloading existing event to personal events
					while(userChoice.equals("2") && boolOption2){


						boolean boolEventQ = true; //boolean for asking qusetion preventing user to error out
						while(boolEventQ){
							Scanner eventQ = new Scanner(System.in);
							System.out.println("|-----------------------------------------------------------------");
							System.out.print("| Upload New Event(n) | Download Existing Event(d) | Return(r) (n/d/r)");
							String eventQA = eventQ.next();
							eventQA = eventQA.toLowerCase();
							if(eventQA.equals("n")){
								boolUpload = true;
								boolEventQ = false;
							}
							else if(eventQA.equals("d")){
								boolDownload = true;
								boolEventQ = false;
							}
							else if(eventQA.equals("r")){
								boolUpload = false;
								boolDownload = false;
								boolEventQ = false;
								boolOption2 = false;

							}
						}


						// ================ Creating a New Event and Uploading it ============= \\
						while(boolUpload){

							System.out.println("|--------------------------------------------------|");
							System.out.println("|Please input the month, day, year, hour, and minute");
							// ========= User input calendar object ============ \\

							Calendar userCal = Calendar.getInstance();  //new calendar instance
							int month, day, year, hour, min, date; String event;
							Scanner calendarScan = new Scanner(System.in); //user input for date
							System.out.print("|Please enter a month MM: ");
							month = calendarScan.nextInt();
							System.out.print("|Please enter a day DD: ");
							day = calendarScan.nextInt();
							System.out.print("|Please enter a year YYYY: ");
							year = calendarScan.nextInt();
							System.out.print("|Please enter an hour of the day (0-23): ");
							hour = calendarScan.nextInt();
							System.out.print("|Please enter the minute of the hour (00-59): ");
							min = calendarScan.nextInt();
							userCal.set(year, month-1, day, hour, min);

							
							Scanner eventScan = new Scanner(System.in);
							System.out.print("Please enter the event: ");
							event = eventScan.nextLine();
							

							// ============== adding to user event and campus event list ========== \\

							currentUser.insertPersonalEvent(userCal, event, month-1, day, year, hour, min); //adding to user's event list
							mainBook.addEvent(userCal, event, month-1, day, year, hour, min); //adding to campus list
							boolean curUpload = true;
							while(curUpload){
								System.out.println("\n \n \n \n");
								System.out.println("   |-------------------------------------------|");
								System.out.print("   | Would you like to add another event? (y/n)| ");
								String eAnswer = eventScan.next();
								eAnswer = eAnswer.toLowerCase();
								if(eAnswer.equals("y")){
									boolUpload = true;
									curUpload = false;
								}
								else if(eAnswer.equals("n")){
									boolUpload = false;
									curUpload = false;
								}
							}

						}


						// ================ Downloading an Existing Event and Adding it  ============= \\
						while(boolDownload){
							ENode curEvent = mainBook.returnEvents().returnObject();
							if(curEvent == null){
								boolDownload = false;
							}
							else if(curEvent != null){


							Calendar curDate = curEvent.getDate();
							String strEvent = curEvent.getEvent();


							//returned from the current node event, adding it to users SLL of personal events 
							currentUser.insertPersonalEvent(curDate, strEvent, curEvent.rMonth(), curEvent.rDay(), curEvent.rYear(), curEvent.rHour(), curEvent.rMin());



							boolean curDownload = true;
							while(curDownload){
								System.out.println("   |-------------------------------------------|");
								System.out.print("   | Would you like to add another event? (y/n)| ");
								Scanner downloadScanner = new Scanner(System.in);
								String downloadAnswer = downloadScanner.next();
								downloadAnswer = downloadAnswer.toLowerCase();
								if(downloadAnswer.equals("y")){
									boolDownload = true;
									curDownload = false;
								}
								else if(downloadAnswer.equals("n")){
									boolDownload = false;
									curDownload = false;
								}
							}
						}
							System.out.println("\n \n \n");

							currentUser.displayEvents();

						}

	
					}

					// ====================== Option 3: Displaying Numbered List of Friends + timelines ================== \\
					boolean boolOption3 = true;
					while(userChoice.equals("3") && boolOption3){
						System.out.println("\n \n \n \n");
						System.out.println("|------------------------------------------|");
						System.out.println("|Displaying Friends and Timelines...");
						currentUser.showFriends(mainBook.returnTable()); //displays the table of friends
						boolOption3 = false;

					}

					// ======================= Option 4: Add / Remove Friends =========================== \\
					boolean boolOption4 = true;
					while(userChoice.equals("4") && boolOption4){

						// ======== Add or Remove? ========== \\
						boolean arFriend = true;
						String opt4Response = "";
						while(arFriend){
							Scanner response = new Scanner(System.in);
							System.out.println("\n \n \n");
							System.out.println("   |--------------------------------------------------|");
							System.out.print("   | Would you like to Add or Remove a friend? (a/r):");
							opt4Response = response.next();
							opt4Response = opt4Response.toLowerCase();
							if(opt4Response.equals("a") || opt4Response.equals("r")){
								arFriend = false;
							}
						}

						// ====== Getting user Input ====== \\
						
						String friendEmail;
						Scanner friendScan = new Scanner(System.in); //user input for date
						System.out.println("\n \n \n \n");
						System.out.println("   |---------------------------------------------------");
						System.out.print("   |Please Enter Your Friends Email:");
						friendEmail = friendScan.next();

						// ============== Adding a Friend ============ \\
						
						if(opt4Response.equals("a")){
							currentUser.addFriend(friendEmail);
						}
						// ============== Removing a Friend ============ \\
						
						else if(opt4Response.equals("r")){
							currentUser.removeFriend(friendEmail);
						}



						System.out.println("\n \n");
						System.out.println("|-----------------------");
						System.out.println("| Current Friends List:");
						currentUser.printFriends();

						boolean arFriendQ = true;
						while(arFriendQ){
							System.out.println("\n \n \n \n");
							System.out.println("   |-------------------------------------------------------");
							System.out.print("   | Would you like to Add or Remove another friend? (y/n) ");
							String fAnswer = friendScan.next();

							if(fAnswer.equals("y")){
								boolOption4 = true;
								arFriendQ = false;
							}
							else if(fAnswer.equals("n")){
								boolOption4 = false;
								arFriendQ = false;
							}
						}



					}


					// ================== Option 5: Re-Display Home Screen ============= \\ 

					if(userChoice.equals("5")){
						loggedIn = true; //boolean controlling while loop to log in 
						logInScreen = true;



					}

					// =============== Optino 6: Log out of Account ================ \\

					if(userChoice.equals("6")){
						loggedIn = false; //boolean controlling while loop to log in 
					}





				}
			}


			// ============================ Create New Account ================================== \\

			else if(option.equals("b") || option.equals("B")){
				boolean creatingAccount = true;
				System.out.println("\n \n \n \n");
				System.out.println("   |---------------------------------------------------");



				// ======================= First Read In - Preventing User Error ================= \\
				String firstName, lastName, email, password, classYear; //initalizing vars
					
				Scanner newAcc = new Scanner(System.in);
				System.out.print("   |Please enter your first name: ");
				firstName = newAcc.next();
				firstName = firstName.toLowerCase();
				System.out.print("   |Please enter your last name: ");
				lastName = newAcc.next();
				lastName = lastName.toLowerCase();
				System.out.print("   |Please enter your email (Capitals Not Allowed): ");
				email = newAcc.next();
				email = email.toLowerCase();
				System.out.print("   |Please enter your class year: "); // READS IN AS STRING TO AVOID MISMATCHEXCEPTION for now, convert later
				classYear = newAcc.next();
				classYear = classYear.toLowerCase();
				System.out.print("   |Please enter your password (Capitals Not Allowed): ");
				password = newAcc.next();
				password = password.toLowerCase();

				if(firstName.compareTo("a") >= 0 || lastName.compareTo("a") >= 0 || email.compareTo("a") >= 0 || classYear.compareTo("a") <= -48 || password.compareTo("a") >= 0){
					creatingAccount = true;
				}
				System.out.println(firstName.compareTo("a")+" "+lastName.compareTo("a")+" "+email.compareTo("a")+" "+classYear.compareTo("a")+" "+password.compareTo("a"));
				if(firstName.compareTo("a") >= 0 && lastName.compareTo("a") >= 0 && email.compareTo("a") >= 0 && classYear.compareTo("a") <= -40 && password.compareTo("a") >= 0){
					int year = Integer.parseInt(classYear);
					mainBook.addPerson(firstName, lastName, email, year, password);
					creatingAccount = false;
				}

				
				while(creatingAccount){
					
					System.out.println("\n \n \n \n");
					System.out.println("   |------------------------------------------------------------------------");
					System.out.println("   | Some details you entered did not follow guidelines. Please enter again:");
					
					Scanner reAcc = new Scanner(System.in);
					System.out.print("   |Please enter your first name: ");
					firstName = newAcc.next();
					firstName = firstName.toLowerCase();
					System.out.print("   |Please enter your last name: ");
					lastName = reAcc.next();
					lastName = lastName.toLowerCase();
					System.out.print("   |Please enter your email(CAPITALS NOT ALLOWED): ");
					email = reAcc.next();
					email = email.toLowerCase();
					System.out.print("   |Please enter your class year: "); // READS IN AS STRING TO AVOID MISMATCHEXCEPTION for now, convert later
					classYear = reAcc.next();
					classYear = classYear.toLowerCase();
					System.out.print("   |Please enter your password(CAPITALS NOT ALLOWED): ");
					password = reAcc.next();
					password = password.toLowerCase();

					// ==== Guarentees user inputted a character for the strings, and a year for the class year ===== \\
					if(firstName.compareTo("a") >= 0 && lastName.compareTo("a") >= 0 && email.compareTo("a") >= 0 && classYear.compareTo("a") <= -48 && password.compareTo("a") >= 0){
						int year = Integer.parseInt(classYear);
						mainBook.addPerson(firstName, lastName, email, year, password);
						creatingAccount = false;
					}
				}
			}



			// ================= Quit Program ================ \\
			
			else if(option.equals("c") || option.equals("C")){
				quitProgram = true;
				mainBook.endProgram();

				if(!mainBook.program()){
					//Do file writing out stuff here 
					mainBook.fileWriter();
				}



				System.out.println("\n \n \n");
				System.out.println(" \t \t |--------------|");
				System.out.println(" \t \t |  Thank You   |");
				System.out.println(" \t \t | For Using Our|");
				System.out.println(" \t \t |   Program!   |");
				System.out.println(" \t \t |--------------|");
				System.out.println("\n \n \n");
			}



			// ====================================== + Special Admin Log In + =================================== \\
			else if(option.equals("adlog")){
				Scanner admin = new Scanner(System.in);
				String optionALog = "";
				System.out.print("Admin Login Successful: ");

				boolean adminLog = true;
				while(adminLog){
					System.out.print(":");
					optionALog = admin.next();
					if(optionALog.equals("s")){
						mainBook.showAccounts();
					}

					else if(optionALog.equals("del")){
						System.out.print("email:");
						String emailDel = admin.next();
						System.out.print("Do you wish to proceed? This process is irreversible. (y/n)");
						String answer = admin.next();
						answer = answer.toLowerCase();
						if(answer.equals("y")){
							mainBook.returnTable().deleteAccount(emailDel);
						}
						else if(answer.equals("n")){
							System.out.print("returning...");
						}
					}

					else if(optionALog.equals("q")){
						adminLog = false;
					}
				}
			}

			// ====================================== + End Special Admin Log In + =================================== \\

		}
	}
}




class ConnBookTest{
	public static void main(String[] args){
		ConnBook book = new ConnBook();
		book.addPerson("Justin", "T", "jturbevil@conncoll.edu", 2022, "password");
		book.addPerson("Evan", "Goldsmith", "egoldsmi@conncoll.edu", 2022, "myPassword");
		book.addPerson("Zack", "Beucler", "zbeucler@conncoll.edu", 2022, "zacksPass");
		book.addPerson("William", "Tarimo", "wtarimo@conncoll.edu", 2005, "tarimoPass");
		boolean testbool = true;
		// Person justin;
		// if(testbool == true){
			Person justin = book.login("jturbevil@conncoll.edu", "password");
			System.out.println(justin.getLastName()+" "+justin.getFirstName());
			// justin.addFriend("egoldsmi@conncoll.edu");
		// justin.addFriend("zbeucler@conncoll.edu");
		// justin.addFriend("wtarimo@conncoll.edu");
		// justin.showFriends();
		// System.out.println("Removing...");
		// justin.removeFriend("wtarimo@conncoll.edu");
		// justin.showFriends();
		// }
		// Person evan = book.login("egoldsmi@conncoll.edu", "myPassword");
		// // System.out.println(evan.getFirstName());
		// System.out.println(justin.getLastName()+" "+justin.getFirstName());
		justin.addTimelinePost("This finally works!", "Justin");
		// // justin.addTimelinePost("I'm hungry!", "Justin");
		// // justin.addTimelinePost("Testing...", "Justin!");
		justin.displayTimeline(5);
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

		
	
