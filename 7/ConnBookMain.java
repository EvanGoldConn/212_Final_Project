// public class ConnBookMain{
// 	public static void main(String[] args){
// 		ConnBook mainBook = new ConnBook();
// 		boolean quitProgram = false;
// 		Person currentUser;
// 		while(quitProgram == false){
// 			// ============== Main Menu ======================================================== \\
// 			System.out.println(" |--------------------------------------------------------------|");
// 			System.out.println(" |                  Conn's Social Network Program               |");
// 			System.out.println(" |--------------------------------------------------------------|");
// 			System.out.println(" |  Created by: Evan Goldsmith, Justin Turbeville, Brian Gould  |");
// 			System.out.println(" |--------------------------------------------------------------|");
// 			System.out.println(" |                         Main Menu                            |");
// 			System.out.println(" |--------------------------------------------------------------|");
// 			System.out.println(" |     Option A          |  Option B      |    Option C         |");
// 			System.out.println(" |--------------------------------------------------------------|");
// 			System.out.println(" |   Login to Existing   |  Create a New  |  Exit the           |");
// 			System.out.println(" |   User Account        |  Account       |  Program            |");
// 			System.out.println(" |--------------------------------------------------------------|");
// 			System.out.println("\n");
			
// 			// ============================ User Input for Main Menu ============================ \\
// 			System.out.println("\n \n \n \n");
// 				System.out.println("   |---------------------------------------------------");
// 			String option;
// 			Scanner scan = new Scanner(System.in); //user input for date

// 			System.out.print("   |Please enter an option (A/B/C): ");
// 			option = scan.next();
// 			option.toLowerCase();
// 			boolean incorrectOption;
// 			if(option.equals("a") || option.equals("b") || option.equals("c")){
// 					incorrectOption = true;
// 				}
// 			else{
// 				incorrectOption = false;
// 			}

// 			while(!incorrectOption){	
// 				Scanner rescan = new Scanner(System.in); //user input for date
// 				System.out.print("   |Invalid Option \n |\t Please enter an option (A/B/C): ");
// 				option = rescan.next();
// 				option.toLowerCase();
// 				if(option.equals("a") || option.equals("b") || option.equals("c")){
// 					incorrectOption = true;
// 				}
// 			}


// 			// ======================== Login to Existing Account =============================== \\

// 			if(option.equals("a")){
// 				mainBook.showAccounts();
// 				System.out.println("\n \n \n \n");
// 				System.out.println("   |---------------------------------------------------");
// 				String email, password;
// 				Scanner login = new Scanner(System.in); // user input for email and password
// 				System.out.print("   |Please enter your email: ");
// 				email = login.next();
// 				System.out.print("   |Please enter your password: ");
// 				password = login.next();
// 				// System.out.println(email+" "+password);
// 				currentUser = mainBook.login(email, password);
// 				// System.out.println(currentUser.getLastName());



// 				// ========== User Options ========== \\
// 				// System.out.println("\n \n \n \n");
// 				System.out.println(" |--------------------------------------------------------------------------------------------|");
// 				System.out.println(" | Option 1     | Option 2      | Option 3       | Option 4     | Option 5      | Option 6    |");
// 				System.out.println(" |--------------------------------------------------------------------------------------------|");
// 				System.out.println(" | Post to User | Add new event | Display current| Add / Remove | Log out of    |             |");
// 				System.out.println(" | Timeline     | to attend to  | Friends        | a Friend     | Account       |             |");
// 				System.out.println(" |--------------------------------------------------------------------------------------------|\n\n");
// 				System.out.println("\n \n \n \n");
// 				System.out.println("   |---------------------------------------------------");

// 				int userChoice;
// 				Scanner userScan = new Scanner(System.in); //user input for date

// 			}



// 		}


// 	}
// }