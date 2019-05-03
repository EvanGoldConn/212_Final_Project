import java.io.*;
import java.util.Calendar;
import java.util.Scanner;

public class FileIO{
    
    public HashTable getHash(String file){
		//this try-catch statement is needed around this file input code
		//because the FileInputStream may throw a FileNotFoundException
		try {
			Scanner lineScanner = new Scanner(new FileInputStream(file));
			HashTable hash = new HashTable();
			Person p;
			
			while (lineScanner.hasNext()) { //while more of the input file is still available for reading
											
				//System.out.println("+++++++++++++++++++++++++++");
				String name = lineScanner.nextLine();  //reads an entire line of input
				Scanner nameScanner = new Scanner(name);
				String first = nameScanner.next();
				String last = nameScanner.next();
				nameScanner.useDelimiter(" ");
				//System.out.println("Name is: " + name);
				
				//System.out.println("+++++++++++++++++++++++++++");
				String email = lineScanner.nextLine();
				//System.out.println("Email is: " + email);
				
				//System.out.println("+++++++++++++++++++++++++++");
				String pass = lineScanner.nextLine();
				//System.out.println("Password is: " + pass);
				
				//System.out.println("+++++++++++++++++++++++++++");
                String year = lineScanner.nextLine();
                int gradYear = Integer.parseInt(year);
				//System.out.println("Year is: " + year);
				
				//System.out.println("+++++++++++++++++++++++++++");
				String status = lineScanner.nextLine();
				//System.out.println("Status is: " + status);
				
				p = new Person(first, last, email, gradYear, pass);

				//System.out.println("+++++++++++++++++++++++++++");
				String events = lineScanner.nextLine(); //read the entire line of event data
				//now create a secondary scanner to actually scan through this list of events
				// to break them up into individual events
				Scanner eventsScanner = new Scanner(events);
				String[] eventsArray = new String[10]; //will store the individual events for now
				int i = 0; //array index counter
				//on this line of data, events are in quotes and delimited by commas, 
				// so we tell the scanner to look for a quotation mark followed by a comma (",)
				// to delimit each event
				eventsScanner.useDelimiter("\","); //need the backslash in front of special characters like "
				String e; //will hold each individual event
				//System.out.println("Events are: ");
				while (eventsScanner.hasNext()){
					//System.out.println("--------------------------");
                    e = eventsScanner.next();
					e = e.substring(1, e.length()); //cut off the leading quotation mark of each event
					//System.out.println(e);
					eventsArray[i] = e;
					i++;
					//here's some extra code to demonstrate how to further break down each event string
                    //System.out.println("(Now illustrating how to extract each piece of event info.)");
                    Calendar date = Calendar.getInstance();
                    Scanner eScanner = new Scanner(e); //yet another scanner just for this particular event
                    int month = eScanner.nextInt();
                    //System.out.println("Month: " + month);
                    int day = eScanner.nextInt();
                    //System.out.println("Day: " + day);
                    int eYear = eScanner.nextInt();
                    //System.out.println("Year: " + eYear);
                    int hour = eScanner.nextInt();
                    //System.out.println("Hour: " + hour);
                    int minutes = eScanner.nextInt();
                    //System.out.println("Minutes: " + minutes);
                    date.set(eYear, month-1, day, hour, minutes);
					String desc = ""; //to hold the description of the event
					while (eScanner.hasNext()){ //while there are words left...
						desc = desc + " " + eScanner.next(); //reads the description one word at a time
                    }
                    p.insertEvent(date, desc);
                    //System.out.println("Description: " + desc);
                    
				}				

				//System.out.println("+++++++++++++++++++++++++++");				
				/* reads in next line and then breaks it into separate wall messages
				 * code is analagous to events, so refer to above comments for explanation. */
				String wallMsgs = lineScanner.nextLine();
				Scanner wallMsgScanner = new Scanner(wallMsgs);
				String[] wallMsgArray = new String[5];
				i = 0;
				wallMsgScanner.useDelimiter("\",");
				String message; 
				//System.out.print("Wall messages are: ");
				while (wallMsgScanner.hasNext()) {
					message = wallMsgScanner.next();
					p.addTimelinePost(message, p.getFirstName());
					//System.out.print(message + "\",");
					message = message.substring(1, message.length());
					wallMsgArray[i] = message; //stores message into array of messages
					//i don't do anything with this array, but it is here just to demonstrate
					//(you may or may not be using an array to store the list of wall messages.)
					i++;
				}
				//System.out.println();
				/*	test code for printing contents of array
				for (int j = 0; j < wallMsgArray.length; j++){
					System.out.println(wallMsgArray[j]);
				}*/
				
				//System.out.println("+++++++++++++++++++++++++++");
				/* reads in next line and then breaks it into separate friends
				 * now the delimiter is just a comma because there are no quotes around
				 * each data item.  so this is a bit simpler than above procedure.*/
				String friends = lineScanner.nextLine();
				Scanner friendScanner = new Scanner(friends);
				String[] friendArray = new String[20]; 
				i = 0;
				friendScanner.useDelimiter(",");  
				String friend;
				//System.out.print("Friends are: ");
				while (friendScanner.hasNext()) {
					friend = friendScanner.next();
					p.addFriend(friend);
					//System.out.print(friend + ",");
					friendArray[i] = friend; //stores friend into array of friends
					//i don't do anything with this array, but it is here just to demonstrate
					//(you may or may not be using an array to store the list of friends.)
					i++;
				}
				
				hash.addPerson(p);
				//System.out.println();
				//System.out.println("+++++++++++++++++++++++++++");
				
			}
            return hash;
		} 
		catch(FileNotFoundException ex) {
			System.out.println("File not Found");
			System.exit(0);
		}
        
        return(null);
    }
	
}
