import java.util.Calendar;
import java.util.Scanner;
import java.io.*;

/*creates a person object every time someone new makes an account */
public class Person {

    private String firstName;
    private String lastName;
    private Events events;
    private int gradYear;
    private String email;
    private String pass;
    private Timeline timeline;
    private BSTFriends friends;

    /**creats a person object based on user inputs
    
    @param f
            String of the user's first name
    @param l
            String of the user's last name
    @param e
            String of the user's email
    @param y
            Integer of the user's graduation year
    @param p
            String of the user's password
    */
    public Person(String f, String l, String e, int y, String p) {
        firstName = f;
        lastName = l;
        email = e;
        events = new Events(); //initializes events
        gradYear = y;
        pass = p;
        timeline = new Timeline(); //initializes timeline
        friends = new BSTFriends(); //initializes friends
    }

    
    /**combines first and last names into one string*/
    public String getFullName() {
         return firstName + " " + lastName;
    }
    
    /**returns first name*/
    public String getFirstName() {
        return firstName;
    }
    
    /**returns last name*/
    public String getLastName() {
        return lastName;
    }
    
    /**returns user's password*/
    public String getPassword(){
        return pass;
    }

    /**returns user's email*/
    public String getEmail(){
        return email;
    }

    // ======= Used for File Writing ONLY ======== \\
    /**returns timeline*/
    public Timeline getTimeline() {
        return timeline;
    }
    
    /**returns events*/
    public Events getPersonEvent(){
        return events;
    }
    
    /**returns friends*/
    public BSTFriends getPersonFriends(){
        return friends;
    }

  

    // ============================================\\

    /**returns grad year*/
    public int getYear(){
        return gradYear;
    }


    /**creates and returns an email key*/
    public String getEmailKey() {
        int i = 0;
        String emailKey = "";
        while(email.charAt(i) != '@'){ //loops through user's email until it finds the "@"
            emailKey += email.charAt(i); //adds the character to the emailKey string
            i++;  //increments i
        }
        return emailKey;
    }

    /**creates and returns an email key*/
    private String returnEmailKey(String inputEmail){
        int i = 0;
        String emailKey = "";
        while(inputEmail.charAt(i) != '@'){ //loops through user's email until it finds the "@"
            // System.out.println(inputEmail.charAt(i));
            emailKey += inputEmail.charAt(i); //adds the character to the emailKey string
            i++; //increments i
        }
        return emailKey;
    }

    // ================= Timeline manipulation ================== \\

    /**takes two strings and combines then into one "post"
    
    @param v
            String that is the text
    @param c
            String that is the creator's name
    */
    public void addTimelinePost(String v, String c) {
        timeline.addPost(v, c);
    }

    /**takes n as a value of how many timeline posts to display*/
    public void displayTimeline(int n) {
        timeline.displayTimeline(n);
    }
    
    /**recursive call on the timeline*/
    public void loginTimeline() {
        timeline.loginTimeline();
    }
    // ======================== BSTFriends manipulation ============== \\
    
    /**adds a friend based on their email
    
    @param email
            String that is an email
    */
    public void addFriend(String email) { 
        String emailKey = returnEmailKey(email); //makes emailKey a string without the "@"
        friends.addFriend(emailKey); //add's friend to friends
    }

    /**finds friend in "friends" based on the key
    
    @param key
            String that is the friends full email
    */
    public String findFriend(String key) {
        String friend = friends.findEmail(key, friends.root());
        //finds the friend based on their email
        return friend;
    }

    /**removes friend based on email*/
    public void removeFriend(String email) {
        String emailKey = returnEmailKey(email); //uses email to get the key
        // System.out.println("key"+" "+emailKey);
        String tester = friends.findEmail(emailKey, friends.root()); 
        //finds friend based on emailKey and if it isn't null (found the friend)
        if(tester != null){
            friends.remove(emailKey); //removes that friend
        }
        else{  //otherwise, it couldn't find the account and prints out...
            System.out.println("|--------------------|");
            System.out.println("| Account Not Found. |");
            System.out.println("|--------------------|");
        }
    }

    /**prints out friends using inOrder traversal*/
    public void printFriends(){
        if(friends.size() > 0){ //if more than one friend
            friends.setCounter(); //sets counter to zero
            friends.inOrder(friends.root()); //prints out friends
        }
        else{ //if no friends then prints...
            System.out.println("|------------|");
            System.out.println("| No Friends |");
            System.out.println("|------------|");
        }
    }
    
    /**uses the HashTable to display the friends and their latest timeline posts/events */
    public void showFriends(HashTable table){
        if(friends.size() > 0){  //if more than one friend...
            friends.setCounter();
            friends.returnInOrder(friends.root()); //gives list of friends in inOrder traversal
            String[] listFriends = friends.returnList(); //takes friends and puts them into an array

            for(int i = 0; i < listFriends.length; i++){ //loops through all friends
                if(listFriends[i] != null){
                    Person friend = table.forEmail(listFriends[i]); //returns Person object of current friend

                    

                    System.out.println("\n\n"+friend.getFullName()+":"); //displays person's name
                    System.out.println("Latest Timeline Post:");
                    friend.displayTimeline(1); //displays their latest timeline post

                    System.out.println("Next Event:"); 
                    friend.displayEvents(); //displays person's next planned event
                    boolean post = true; 
                    while(post){ //while post is true
                        Scanner question = new Scanner(System.in);
                        String answer = "";
                        System.out.print("Do you wish to post to your friends timeline? (y/n)");
                        answer = question.next();
                        answer = answer.toLowerCase();

                        if(answer.equals("y")){ //if they want to add to their friends timeline
                            String details = "";
                            System.out.print("What do you wish to post?");
                            details = question.next(); //what the user wants to post

                            friend.addTimelinePost(details, getFullName()); //adds the post to the timeline
                            post = false; //ends while loop
                        }
                        else if(answer.equals("n")){ //if they don't want to post on their friends timeline
                            post = false;  //ends while loop
                        }
                    }
                }
            }
        }
        else{  //if no friends that prints...
            System.out.println("|------------|");
            System.out.println("| No Friends |");
            System.out.println("|------------|");
        }
    }

    // ---------------------------------------------------------------- \\
    // ===================== event manipulation ==================== \\
    /**adds an event with the time and the name of the event
    @param date
            Takes the actual date
    @param event
            String with the name of the event
    @param month
            integer of the month of the event
    @param day
            integer of the day of the month
    @param year
            integer of the year
    @param hour
            integer of the hour of the day
    @param minutes
            integer of the minute of the hour
    */
    public void insertPersonalEvent(Calendar date, String event, int month, int day, int year, int hour, int minutes) {
        events.addEvent(date, event, month, day, year, hour, minutes);
    }

    /**displays the events*/
    public void displayEvents() {
        events.displayEvents();
    }

    
}

class PersonTest { //test class that is used for testing various methods of the Person class
    public static void main(String[] args){
        Person me = new Person("Justin", "T", "jturbevil", 2022, "password");
        Person me2 = new Person("Evan", "G", "eg@", 2022, "p'");
        Person me3 = new Person("Brian", "G", "bg@", 2021, "f");



        me.addFriend("egold@");
        me.addFriend("bgould@");
        // me.showFriends();
        System.out.print(me.findFriend("egold@"));
        // me.removeFriend("eg@");
        // me.showFriends();
    }
}
